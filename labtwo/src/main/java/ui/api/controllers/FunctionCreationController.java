package ui.api.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import ui.api.dto.SettingsDto;
import ui.api.enums.MathFunctionType;
import ui.api.enums.TabulatedFunctionFactoryType;
import functions.*;
import dbServices.repository.FunctionRepository;
import ui.api.services.MathFunctionService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/api/function-creation")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class FunctionCreationController {

    private FunctionRepository functionRepository;
    private SettingsController settingsController;
    private MathFunctionService mathFunctionService;


    @PostMapping("/create-from-points")
    public ResponseEntity<FunctionDTO> createFromPoints(
            @RequestParam double[] x, @RequestParam double[] y
    ) {
        // Проверяем корректность работы с settingsController
        ResponseEntity<SettingsDto> response = settingsController.getCurrentFactoryType();
        if (response == null || response.getBody() == null) {
            throw new IllegalStateException("SettingsDto is null");
        }

        TabulatedFunctionFactoryType factoryType = response.getBody().getFactoryType();

        TabulatedFunction function = mathFunctionService.createTabulatedFunction(
                x,
                y,
                factoryType
        );

        // Создаем сущность с использованием точек из созданной функции
        List<PointEntity> points = IntStream.range(0, function.getCount())
                .mapToObj(i -> {
                    PointEntity point = new PointEntity();
                    point.setX(function.getX(i));
                    point.setY(function.getY(i));
                    return point;
                })
                .collect(Collectors.toList());

        FunctionEntity entity = new FunctionEntity();
        entity.setName(function.getClass().getSimpleName());
        entity.setPoints(points);

        FunctionDTO savedDto = new FunctionDTO();
        savedDto.setFunctionId(functionRepository.save(entity).getFunctionId());
        savedDto.setName(functionRepository.save(entity).getName());
        savedDto.setXFrom(functionRepository.save(entity).getXFrom());
        savedDto.setXTo(functionRepository.save(entity).getXTo());
        savedDto.setCount(functionRepository.save(entity).getCount());

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }


    @PostMapping("/create-from-math-function")
    public ResponseEntity<FunctionDTO> createFromMathFunction(
            @RequestParam String name, @RequestParam Double from, @RequestParam Double to, @RequestParam int count
    ) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TabulatedFunctionFactoryType factoryType = settingsController.getCurrentFactoryType().getBody().getFactoryType();

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

        // Создаем сущность с использованием точек из созданной функции
        List<PointEntity> points = IntStream.range(0, function.getCount())
                .mapToObj(i -> {
                    PointEntity point = new PointEntity();
                    point.setX(function.getX(i));
                    point.setY(function.getY(i));
                    return point;
                })
                .collect(Collectors.toList());

        FunctionEntity entity = new FunctionEntity();
        entity.setPoints(points);
        entity.setName(function.getClass().getSimpleName());

        // Сохранение функции в базу данных
        Optional<FunctionEntity> entityFind = functionRepository.findById(entity.getFunctionId());

        FunctionDTO savedDto = new FunctionDTO();
        savedDto.setFunctionId(functionRepository.save(entity).getFunctionId());
        savedDto.setName(functionRepository.save(entity).getName());
        savedDto.setXFrom(functionRepository.save(entity).getXFrom());
        savedDto.setXTo(functionRepository.save(entity).getXTo());
        savedDto.setCount(functionRepository.save(entity).getCount());




        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/functions-to-create")
    public ResponseEntity<List<String>> getSimpleFunctions() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> ans = MathFunctionType.getFunctions();
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }


    @PostMapping("/create-composite")
    public ResponseEntity<FunctionDTO> createCompositeFunction(@RequestParam @NotNull Long hash1, @RequestParam @NotNull Long hash2, @RequestParam @NotNull String name) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TabulatedFunction function1 = mathFunctionService.convertToTabulatedFunction(hash1);
        TabulatedFunction function2 = mathFunctionService.convertToTabulatedFunction(hash2);
        CompositeFunction composite = new CompositeFunction(function1, function2);

        MathFunctionType.addFunctionMap(name, (MathFunction) composite);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
