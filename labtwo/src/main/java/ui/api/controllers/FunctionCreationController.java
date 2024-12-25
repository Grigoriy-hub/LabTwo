package ui.api.controllers;

import dbServices.DTO.buildDTO.FunctionDTOBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import ui.api.enums.MathFunctionType;
import ui.api.enums.TabulatedFunctionFactoryType;
import functions.*;
import dbServices.repository.FunctionRepository;
import ui.api.services.MathFunctionService;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.List;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/function-creation")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class FunctionCreationController {

    private final FunctionRepository functionRepository;
    private final SettingsController settingsController;
    private final MathFunctionService mathFunctionService;


    @PostMapping("/create-from-points")
    public ResponseEntity<FunctionDTO> createFromPoints(
            @RequestParam double[] x, @RequestParam double[] y
    ) {
        TabulatedFunctionFactoryType factoryType = Objects.requireNonNull(settingsController.getCurrentFactoryType().getBody()).getFactoryType();

        TabulatedFunction function = mathFunctionService.createTabulatedFunction(
                x,
                y,
                factoryType
        );

        List<PointEntity> points = IntStream.range(0, function.getCount())
                .mapToObj(i -> new PointEntity(function.getX(i), function.getY(i)))
                .collect(Collectors.toList());

        FunctionEntity entity = FunctionEntity.builder()
                .points(points)
                .name(function.getClass().getSimpleName())
                .hash(function.HashName())
                .build();


        Optional<FunctionEntity> entityFind = functionRepository.findByHash(entity.getHash());
        if (entityFind.isPresent()) {
            entity.setUpdateAt(Instant.now());
            entity.setCreatedAt(entityFind.get().getCreatedAt());
        }
        FunctionDTO savedDto = FunctionDTOBuilder.makeMathFunctionDto(
                functionRepository.save(entity)
        );

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PostMapping("/create-from-math-function")
    public ResponseEntity<FunctionDTO> createFromMathFunction(
            @RequestParam String name, @RequestParam Double from, @RequestParam Double to, @RequestParam int count
    ) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TabulatedFunctionFactoryType factoryType = Objects.requireNonNull(settingsController.getCurrentFactoryType().getBody()).getFactoryType();

        MathFunction mathFunction = MathFunctionType.getLocalizedFunctionMap()
                .get(name);
        System.out.println(mathFunction.apply(1));
        TabulatedFunction function = mathFunctionService.createTabulatedFunction(
                mathFunction,
                from,
                to,
                count,
                factoryType
        );

        List<PointEntity> points = IntStream.range(0, function.getCount())
                .mapToObj(i -> new PointEntity(function.getX(i), function.getY(i)))
                .collect(Collectors.toList());

        FunctionEntity entity = FunctionEntity.builder()
                .points(points)
                .name(function.getClass().getSimpleName())
                .hash(function.HashName())
                .build();

        // Сохранение функции в базу данных
        Optional<FunctionEntity> entityFind = functionRepository.findByHash(entity.getHash());
        if (entityFind.isPresent()) {
            entity.setUpdateAt(Instant.now());
            entity.setCreatedAt(entityFind.get().getCreatedAt());
        }
        FunctionDTO savedDto = FunctionDTOBuilder.makeMathFunctionDto(
                functionRepository.save(entity)
        );


        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/functions-to-create")
    public ResponseEntity<List<String>> getSimpleFunctions() {
        List<String> ans = MathFunctionType.getFunctions();
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }


    @PostMapping("/create-composite")
    public ResponseEntity<FunctionDTO> createCompositeFunction(@RequestParam @NotNull Long hash1, @RequestParam @NotNull Long hash2, @RequestParam @NotNull String name) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TabulatedFunction function1 = mathFunctionService.convertToTabulatedFunction(hash1);
        TabulatedFunction function2 = mathFunctionService.convertToTabulatedFunction(hash2);
        CompositeFunction composite = new CompositeFunction(function1, function2);

        MathFunctionType.addFunctionMap(name, composite);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
