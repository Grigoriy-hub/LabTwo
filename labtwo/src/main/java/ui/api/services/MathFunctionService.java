package ui.api.services;

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

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class MathFunctionService {
    @Autowired
    private FunctionRepository functionRepository;
    private static final SettingsController settingsController = new SettingsController();

    public ResponseEntity<FunctionDTO> saveAndUpdateMathFunction(FunctionEntity entity) {
        Optional<FunctionEntity> entityFind = functionRepository.findById(entity.getFunctionId());

        FunctionDTO savedDto = new FunctionDTO();
        savedDto.setFunctionId(functionRepository.save(entity).getFunctionId());
        savedDto.setName(functionRepository.save(entity).getName());
        savedDto.setXFrom(functionRepository.save(entity).getXFrom());
        savedDto.setXTo(functionRepository.save(entity).getXTo());
        savedDto.setCount(functionRepository.save(entity).getCount());

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

        TabulatedFunctionFactoryType factoryType = settingsController.getCurrentFactoryType().getBody().getFactoryType();

        double[] xValues = functionEntity.getPoints().stream()
                .mapToDouble(PointEntity::getX)
                .toArray();

        double[] yValues = functionEntity.getPoints().stream()
                .mapToDouble(PointEntity::getY)
                .toArray();

        return createTabulatedFunction(xValues, yValues, factoryType);
    }
    public ResponseEntity<FunctionDTO> createAndSaveMathFunctionEntity(TabulatedFunction function) {
        FunctionEntity entity = new FunctionEntity();
        entity.setPoints(
                IntStream.range(0, function.getCount())
                        .mapToObj(i -> {
                            PointEntity point = new PointEntity();
                            point.setX(function.getX(i));
                            point.setY(function.getY(i));
                            return point;
                        })
                        .collect(Collectors.toList())
        );

        entity.setName(function.getClass().getSimpleName());

        return saveAndUpdateMathFunction(entity);
    }

}