package ui.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import dbServices.DTO.FunctionDTO;
import exceptions.InconsistentFunctionsException;
import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;
import operations.TabulatedFunctionOperationService;
import operations.IntegralFunctional;
import dbServices.repository.FunctionRepository;

import jakarta.validation.constraints.NotNull;
import ui.api.dto.ApplyResultDto;
import ui.api.enums.TabulatedFunctionFactoryType;
import ui.api.services.MathFunctionService;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/tabulated-function-operations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@Validated
public class FunctionOperationsController {
    private final TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
    private FunctionRepository functionRepository;
    private SettingsController settingsController;
    private FunctionCreationController functionCreationController;
    private MathFunctionService mathFunctionService;

    @PostMapping("/add")
    public ResponseEntity<FunctionDTO> addFunctions(
            @RequestParam @NotNull Long functionId1,
            @RequestParam @NotNull Long functionId2
    ) {
        try {
            TabulatedFunction function1 = mathFunctionService.convertToTabulatedFunction(functionId1);
            TabulatedFunction function2 = mathFunctionService.convertToTabulatedFunction(functionId2);

            TabulatedFunction resultFunction = operationService.PlusFunction(function1, function2);


            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity(resultFunction).getBody(), HttpStatus.CREATED);
        } catch (InconsistentFunctionsException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/subtract")
    public ResponseEntity<FunctionDTO> subtractFunctions(
            @RequestParam @NotNull Long functionId1,
            @RequestParam @NotNull Long functionId2
    ) {
        try {
            TabulatedFunction function1 = mathFunctionService.convertToTabulatedFunction(functionId1);
            TabulatedFunction function2 = mathFunctionService.convertToTabulatedFunction(functionId2);

            TabulatedFunction resultFunction = operationService.MinusFunction(function1, function2);


            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity(resultFunction).getBody(), HttpStatus.CREATED);
        } catch (InconsistentFunctionsException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/multiply")
    public ResponseEntity<FunctionDTO> multiplyFunctions(
            @RequestParam @NotNull Long functionId1,
            @RequestParam @NotNull Long functionId2
    ) {
        try {
            TabulatedFunction function1 = mathFunctionService.convertToTabulatedFunction(functionId1);
            TabulatedFunction function2 = mathFunctionService.convertToTabulatedFunction(functionId2);

            TabulatedFunction resultFunction = operationService.multiply(function1, function2);

            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity(resultFunction).getBody(), HttpStatus.CREATED);
        } catch (InconsistentFunctionsException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/divide")
    public ResponseEntity<FunctionDTO> divideFunctions(
            @RequestParam @NotNull Long functionId1,
            @RequestParam @NotNull Long functionId2
    ) {
        try {
            TabulatedFunction function1 = mathFunctionService.convertToTabulatedFunction(functionId1);
            TabulatedFunction function2 = mathFunctionService.convertToTabulatedFunction(functionId2);

            TabulatedFunction resultFunction = operationService.divide(function1, function2);


            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity(resultFunction).getBody(), HttpStatus.CREATED);
        } catch (InconsistentFunctionsException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/derive")
    public ResponseEntity<FunctionDTO> deriveFunctions(
            @RequestParam @NotNull Long functionId
    ) {
        try {
            TabulatedFunction function = mathFunctionService.convertToTabulatedFunction(functionId);
            TabulatedFunctionFactoryType type = settingsController.getCurrentFactoryType().getBody().getFactoryType();

            TabulatedFunction resultFunction = new TabulatedDifferentialOperator(switch (type) {
                case ARRAY_FACTORY -> new ArrayTabulatedFunctionFactory();
                case LINKED_LIST_FACTORY -> new LinkedListTabulatedFunctionFactory();
            }).derive(function);


            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity(resultFunction).getBody(), HttpStatus.CREATED);
        } catch (InconsistentFunctionsException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/integral")
    public ResponseEntity<Double> integralFunctions(
            @RequestParam @NotNull Long functionId, @RequestParam @NotNull Integer threads
    ) throws ExecutionException, InterruptedException {

        TabulatedFunction function = mathFunctionService.convertToTabulatedFunction(functionId);
        Double result = new IntegralFunctional(threads).integrate(function);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/apply")
    public ResponseEntity<ApplyResultDto> applyFunctions(
            @RequestParam @NotNull Long functionId, @RequestParam Double x
    ) {
        TabulatedFunction function = mathFunctionService.convertToTabulatedFunction(functionId);
        double ans;
        ApplyResultDto result;

        if (function.indexOfX(x) == -1) {
            ans = function.apply(x);
            if (function instanceof Insertable) {
                Insertable insertableObject = (Insertable) function;
                insertableObject.insert(x, ans);
                functionRepository.deleteById(functionId);
                FunctionDTO savedFunction = mathFunctionService.createAndSaveMathFunctionEntity((TabulatedFunction) insertableObject).getBody();
                assert savedFunction != null;
                result = new ApplyResultDto(ans, savedFunction.getFunctionId());
            } else {
                result = new ApplyResultDto(ans);
            }
        } else {
            ans = function.getY(function.indexOfX(x));
            result = new ApplyResultDto(ans);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/is-insert")
    public ResponseEntity<Boolean> isInsertFunction(@RequestParam @NotNull Long functionId) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        if (myObject instanceof Insertable) {
            // Класс реализует интерфейс Insertable
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<FunctionDTO> insertFunction(@RequestParam @NotNull Long functionId, @RequestParam Double x, @RequestParam Double y) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        if (myObject instanceof Insertable) {
            Insertable insertableObject = (Insertable) myObject;
            insertableObject.insert(x, y);
            functionRepository.deleteById(functionId);
            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity((TabulatedFunction) insertableObject).getBody(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/is-remove")
    public ResponseEntity<Boolean> isRemoveFunction(@RequestParam @NotNull Long functionId) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        if (myObject instanceof Removable) {
            // Класс реализует интерфейс Insertable
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<FunctionDTO> removeFunction(@RequestParam @NotNull Long functionId, @RequestParam Double x) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        int index = myObject.indexOfX(x);
        if (myObject instanceof Removable) {
            Removable removableObject = (Removable) myObject;
            removableObject.remove(index);
            functionRepository.deleteById(functionId);
            return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity((TabulatedFunction) removableObject).getBody(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/getX")
    public ResponseEntity<Double> getXFunction(@RequestParam Long functionId, @RequestParam int index) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        return new ResponseEntity<>(myObject.getX(index), HttpStatus.OK);
    }

    @PostMapping("/getY")
    public ResponseEntity<Double> getYFunction(@RequestParam Long functionId, @RequestParam int index) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        return new ResponseEntity<>(myObject.getY(index), HttpStatus.OK);
    }

    @PostMapping("/setY")
    public ResponseEntity<FunctionDTO> setYFunction(@RequestParam Long functionId, @RequestParam int index, @RequestParam Double y) {
        TabulatedFunction myObject = mathFunctionService.convertToTabulatedFunction(functionId);
        myObject.setY(index, y);
        functionRepository.deleteById(functionId);
        return new ResponseEntity<>(mathFunctionService.createAndSaveMathFunctionEntity(myObject).getBody(), HttpStatus.OK);
    }
}