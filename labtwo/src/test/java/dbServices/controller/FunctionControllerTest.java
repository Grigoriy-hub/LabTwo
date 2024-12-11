package dbServices.controller;

import dbServices.DTO.FunctionDTO;
import dbServices.service.FunctionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class FunctionControllerUnitTest {

    private FunctionController functionController;
    private FunctionService functionService;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        functionService = mock(FunctionService.class);
        functionController = new FunctionController();

        var field = FunctionController.class.getDeclaredField("functionService");
        field.setAccessible(true);
        field.set(functionController, functionService);
    }

    @Test
    void createFunction_ShouldReturnCreatedFunction() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setFunctionId(1L);
        functionDTO.setName("Test Function");

        Mockito.when(functionService.create(any(FunctionDTO.class))).thenReturn(functionDTO);

        ResponseEntity<FunctionDTO> response = functionController.createFunction(functionDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getFunctionId());
        assertEquals("Test Function", response.getBody().getName());
    }

    @Test
    void getFunctionById_ShouldReturnFunctionWhenFound() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setFunctionId(1L);
        functionDTO.setName("Test Function");

        Mockito.when(functionService.read(1L)).thenReturn(functionDTO);

        ResponseEntity<FunctionDTO> response = functionController.getFunctionById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getFunctionId());
        assertEquals("Test Function", response.getBody().getName());
    }

    @Test
    void getFunctionById_ShouldReturnNotFoundWhenFunctionNotFound() {
        Mockito.when(functionService.read(1L)).thenReturn(null);

        ResponseEntity<FunctionDTO> response = functionController.getFunctionById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void updateFunction_ShouldReturnUpdatedFunction() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setFunctionId(1L);
        functionDTO.setName("Updated Function");

        Mockito.when(functionService.read(1L)).thenReturn(functionDTO);
        Mockito.when(functionService.update(any(FunctionDTO.class))).thenReturn(functionDTO);

        ResponseEntity<FunctionDTO> response = functionController.updateFunction(1L, functionDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getFunctionId());
        assertEquals("Updated Function", response.getBody().getName());
    }

    @Test
    void updateFunction_ShouldReturnNotFoundWhenFunctionNotFound() {
        Mockito.when(functionService.read(1L)).thenReturn(null);

        ResponseEntity<FunctionDTO> response = functionController.updateFunction(1L, new FunctionDTO());

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deleteFunction_ShouldReturnNoContent() {
        Mockito.when(functionService.read(1L)).thenReturn(new FunctionDTO());

        ResponseEntity<Void> response = functionController.deleteFunction(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deleteFunction_ShouldReturnNotFoundWhenFunctionNotFound() {
        Mockito.when(functionService.read(1L)).thenReturn(null);

        ResponseEntity<Void> response = functionController.deleteFunction(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void getFunctionsByName_ShouldReturnFunctionList() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setFunctionId(1L);
        functionDTO.setName("Test Function");

        Mockito.when(functionService.getByName("Test Function"))
                .thenReturn(Collections.singletonList(functionDTO));

        ResponseEntity<List<FunctionDTO>> response = functionController.getFunctionsByName("Test Function");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Function", response.getBody().get(0).getName());
    }
}
