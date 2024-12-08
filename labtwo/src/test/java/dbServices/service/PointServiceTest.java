package dbServices.service;

import dbServices.DTO.PointDTO;
import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import dbServices.repository.FunctionRepository;
import dbServices.repository.PointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PointServiceTest {


    private PointRepository pointRepository;

    private FunctionRepository functionRepository;

    private PointService pointService;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {

        functionRepository = mock(FunctionRepository.class);
        pointService = new PointService();

        var field = PointService.class.getDeclaredField("functionRepository");
        field.setAccessible(true);
        field.set(pointService, functionRepository);

        pointRepository = mock(PointRepository.class);

        var field2 = PointService.class.getDeclaredField("pointRepository");
        field2.setAccessible(true);
        field2.set(pointService, pointRepository);
    }

    @Test
    void testCreate() {
        PointDTO pointDTO = new PointDTO();

        pointDTO.setFunctionId(2L);
        pointDTO.setPointId(1L);
        pointDTO.setX(10.0);
        pointDTO.setY(20.0);

        FunctionEntity function = new FunctionEntity();
        function.setFunctionId(2L);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setPointId(1L);
        pointEntity.setFunction(function);
        pointEntity.setX(10.0);
        pointEntity.setY(20.0);

        when(functionRepository.findById(2L)).thenReturn(Optional.of(function));
        when(pointRepository.save(any(PointEntity.class))).thenReturn(pointEntity);

        PointDTO result = pointService.create(pointDTO);

        assertNotNull(result);
        assertEquals(1L, result.getPointId());
        assertEquals(2L, result.getFunctionId());
        assertEquals(10.0, result.getX());
        assertEquals(20.0, result.getY());
    }

    @Test
    void testRead() {
        FunctionEntity function = new FunctionEntity();
        function.setFunctionId(2L);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setPointId(1L);
        pointEntity.setFunction(function);
        pointEntity.setX(10.0);
        pointEntity.setY(20.0);

        when(pointRepository.findById(1L)).thenReturn(Optional.of(pointEntity));

        PointDTO result = pointService.read(1L);

        assertNotNull(result);
        assertEquals(1L, result.getPointId());
        assertEquals(2L, result.getFunctionId());
        assertEquals(10.0, result.getX());
        assertEquals(20.0, result.getY());
    }

    @Test
    void testUpdate() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setFunctionId(2L);
        pointDTO.setPointId(1L);
        pointDTO.setX(15.0);
        pointDTO.setY(25.0);

        FunctionEntity function = new FunctionEntity();
        function.setFunctionId(2L);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setPointId(1L);
        pointEntity.setFunction(function);
        pointEntity.setX(15.0);
        pointEntity.setY(25.0);

        when(functionRepository.findById(2L)).thenReturn(Optional.of(function));
        when(pointRepository.save(any(PointEntity.class))).thenReturn(pointEntity);

        PointDTO result = pointService.update(pointDTO);

        assertNotNull(result);
        assertEquals(1L, result.getPointId());
        assertEquals(2L, result.getFunctionId());
        assertEquals(15.0, result.getX());
        assertEquals(25.0, result.getY());
    }

    @Test
    void testDelete() {
        pointService.delete(1L);

        verify(pointRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetByFunction() {

        FunctionEntity function = new FunctionEntity();
        function.setFunctionId(2L);

        PointEntity point1 = new PointEntity();
        point1.setPointId(1L);
        point1.setFunction(function);
        point1.setX(10.0);
        point1.setY(20.0);

        PointEntity point2 = new PointEntity();
        point2.setPointId(2L);
        point2.setFunction(function);
        point2.setX(15.0);
        point2.setY(25.0);

        when(functionRepository.findById(2L)).thenReturn(Optional.of(function));
        when(pointRepository.findByFunction(function)).thenReturn(List.of(point1, point2));


        List<PointDTO> results = pointService.getByFunction(2L);

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(1L, results.get(0).getPointId());
        assertEquals(10.0, results.get(0).getX());
        assertEquals(20.0, results.get(0).getY());
        assertEquals(2L, results.get(1).getPointId());
        assertEquals(15.0, results.get(1).getX());
        assertEquals(25.0, results.get(1).getY());
    }
}
