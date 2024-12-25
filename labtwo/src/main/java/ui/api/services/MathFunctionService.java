package ui.api.services;

import dbServices.DTO.buildDTO.FunctionDTOBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;
import dbServices.repository.FunctionRepository;
import ui.api.controllers.SettingsController;
import ui.api.enums.TabulatedFunctionFactoryType;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class MathFunctionService {
    @Autowired
    private final FunctionRepository functionRepository;
    private static final SettingsController settingsController = new SettingsController();

    public ResponseEntity<FunctionDTO> saveAndUpdateMathFunction(FunctionEntity entity) {
        Optional<FunctionEntity> entityFind = functionRepository.findByHash(entity.getHash());
        if (entityFind.isPresent()) {
            entity.setUpdateAt(Instant.now());
            entity.setCreatedAt(entityFind.get().getCreatedAt());
        }
        FunctionDTO savedDto = FunctionDTOBuilder.makeMathFunctionDto(
                functionRepository.save(entity)
        );
        return new ResponseEntity<>(savedDto, HttpStatus.OK);
    }

    public TabulatedFunction createTabulatedFunction(
            double[] xValues,
            double[] yValues,
            TabulatedFunctionFactoryType factoryType
    ) {
        return switch (factoryType) {
            case ARRAY_FACTORY -> new ArrayTabulatedFunction(xValues, yValues);
            case LINKED_LIST_FACTORY -> new LinkedListTabulatedFunction(xValues, yValues);
        };
    }

    public TabulatedFunction createTabulatedFunction(
            MathFunction source,
            double xFrom,
            double xTo,
            int count,
            TabulatedFunctionFactoryType factoryType
    ) {
        return switch (factoryType) {
            case ARRAY_FACTORY -> new ArrayTabulatedFunction(source, xFrom, xTo, count);
            case LINKED_LIST_FACTORY -> new LinkedListTabulatedFunction(source, xFrom, xTo, count);
        };
    }

    public TabulatedFunction convertToTabulatedFunction(Long functionId) {

        FunctionEntity functionEntity = functionRepository.findById(functionId)
                .orElseThrow(() -> new RuntimeException("Function not found"));

        TabulatedFunctionFactoryType factoryType = Objects.requireNonNull(settingsController.getCurrentFactoryType().getBody()).getFactoryType();

        double[] xValues = functionEntity.getPoints().stream()
                .mapToDouble(PointEntity::getX)
                .toArray();

        double[] yValues = functionEntity.getPoints().stream()
                .mapToDouble(PointEntity::getY)
                .toArray();

        return createTabulatedFunction(xValues, yValues, factoryType);
    }

    public ResponseEntity<FunctionDTO> createAndSaveMathFunctionEntity(TabulatedFunction function) {
        FunctionEntity entity = FunctionEntity.builder()
                .points(
                        IntStream.range(0, function.getCount())
                                .mapToObj(i -> new PointEntity(function.getX(i), function.getY(i)))
                                .collect(Collectors.toList())
                )
                .name(function.getClass().getSimpleName())
                .hash(function.HashName())
                .build();

        return saveAndUpdateMathFunction(entity);
    }
}