package dbServices.controller;

import dbServices.DTO.PointDTO;
import dbServices.service.PointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class PointControllerUnitTest {

    private PointController pointController;
    private PointService pointService;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        pointService = mock(PointService.class);
        pointController = new PointController();


        var field = PointController.class.getDeclaredField("pointService");
        field.setAccessible(true);
        field.set(pointController, pointService);
    }

    @Test
    void createPoint_ShouldReturnCreatedPoint() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setPointId(1L);
        pointDTO.setX(5.0);
        pointDTO.setY(10.0);

        Mockito.when(pointService.create(any(PointDTO.class))).thenReturn(pointDTO);

        ResponseEntity<PointDTO> response = pointController.createPoint(pointDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getPointId());
        assertEquals(5.0, response.getBody().getX());
        assertEquals(10.0, response.getBody().getY());
    }

    @Test
    void getPoint_ShouldReturnPointWhenFound() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setPointId(1L);
        pointDTO.setX(5.0);
        pointDTO.setY(10.0);

        Mockito.when(pointService.read(1L)).thenReturn(pointDTO);

        ResponseEntity<PointDTO> response = pointController.getPoint(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getPointId());
        assertEquals(5.0, response.getBody().getX());
        assertEquals(10.0, response.getBody().getY());
    }

    @Test
    void getPoint_ShouldReturnNotFoundWhenPointNotFound() {
        Mockito.when(pointService.read(1L)).thenReturn(null);

        ResponseEntity<PointDTO> response = pointController.getPoint(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void updatePoint_ShouldReturnUpdatedPoint() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setPointId(1L);
        pointDTO.setX(15.0);
        pointDTO.setY(20.0);

        Mockito.when(pointService.read(1L)).thenReturn(pointDTO);
        Mockito.when(pointService.update(any(PointDTO.class))).thenReturn(pointDTO);

        ResponseEntity<PointDTO> response = pointController.updatePoint(1L, pointDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getPointId());
        assertEquals(15.0, response.getBody().getX());
        assertEquals(20.0, response.getBody().getY());
    }

    @Test
    void updatePoint_ShouldReturnNotFoundWhenPointNotFound() {
        Mockito.when(pointService.read(1L)).thenReturn(null);

        ResponseEntity<PointDTO> response = pointController.updatePoint(1L, new PointDTO());

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deletePoint_ShouldReturnNoContent() {
        Mockito.when(pointService.read(1L)).thenReturn(new PointDTO());

        ResponseEntity<Void> response = pointController.deletePoint(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deletePoint_ShouldReturnNotFoundWhenPointNotFound() {
        Mockito.when(pointService.read(1L)).thenReturn(null);

        ResponseEntity<Void> response = pointController.deletePoint(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void getPointsByFunction_ShouldReturnPointList() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setPointId(1L);
        pointDTO.setX(5.0);
        pointDTO.setY(10.0);

        Mockito.when(pointService.getByFunction(1L))
                .thenReturn(Collections.singletonList(pointDTO));

        ResponseEntity<List<PointDTO>> response = pointController.getPointsByFunction(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(5.0, response.getBody().get(0).getX());
        assertEquals(10.0, response.getBody().get(0).getY());
    }

    @Test
    void getPointsByFunction_ShouldReturnNotFoundWhenNoPointsFound() {
        Mockito.when(pointService.getByFunction(1L)).thenReturn(Collections.emptyList());

        ResponseEntity<List<PointDTO>> response = pointController.getPointsByFunction(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
