package dbServices.service;

import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;
import dbServices.repository.FunctionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FunctionServiceTest {

    private FunctionService functionService;
    private FunctionRepository functionRepository;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        functionRepository = mock(FunctionRepository.class);
        functionService = new FunctionService();

        var field = FunctionService.class.getDeclaredField("functionRepository");
        field.setAccessible(true);
        field.set(functionService, functionRepository);
    }

    @Test
    void testCreate() {
        FunctionDTO dto = new FunctionDTO();
        dto.setName("Test Function");
        dto.setXFrom(1.0);
        dto.setXTo(5.0);
        dto.setCount(10);

        FunctionEntity savedEntity = new FunctionEntity();
        savedEntity.setFunctionId(1L);
        savedEntity.setName("Test Function");
        savedEntity.setXFrom(1.0);
        savedEntity.setXTo(5.0);
        savedEntity.setCount(10);

        when(functionRepository.save(any(FunctionEntity.class))).thenReturn(savedEntity);

        FunctionDTO result = functionService.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.getFunctionId());
        assertEquals("Test Function", result.getName());

        ArgumentCaptor<FunctionEntity> captor = ArgumentCaptor.forClass(FunctionEntity.class);
        verify(functionRepository).save(captor.capture());

        FunctionEntity capturedEntity = captor.getValue();
        assertEquals("Test Function", capturedEntity.getName());
        assertEquals(1.0, capturedEntity.getXFrom());
        assertEquals(5.0, capturedEntity.getXTo());
        assertEquals(10, capturedEntity.getCount());
    }

    @Test
    void testRead() {
        FunctionEntity entity = new FunctionEntity();
        entity.setFunctionId(1L);
        entity.setName("Read Function");
        entity.setXFrom(0.0);
        entity.setXTo(10.0);
        entity.setCount(20);

        when(functionRepository.findById(1L)).thenReturn(Optional.of(entity));

        FunctionDTO result = functionService.read(1L);

        assertNotNull(result);
        assertEquals(1L, result.getFunctionId());
        assertEquals("Read Function", result.getName());
        assertEquals(0.0, result.getXFrom());
        assertEquals(10.0, result.getXTo());
        assertEquals(20, result.getCount());
    }

    @Test
    void testUpdate() {
        FunctionDTO dto = new FunctionDTO();
        dto.setFunctionId(1L);
        dto.setName("Updated Function");
        dto.setXFrom(2.0);
        dto.setXTo(8.0);
        dto.setCount(15);

        FunctionEntity updatedEntity = new FunctionEntity();
        updatedEntity.setFunctionId(1L);
        updatedEntity.setName("Updated Function");
        updatedEntity.setXFrom(2.0);
        updatedEntity.setXTo(8.0);
        updatedEntity.setCount(15);

        when(functionRepository.save(any(FunctionEntity.class))).thenReturn(updatedEntity);

        FunctionDTO result = functionService.update(dto);

        assertNotNull(result);
        assertEquals(1L, result.getFunctionId());
        assertEquals("Updated Function", result.getName());
        assertEquals(2.0, result.getXFrom());
        assertEquals(8.0, result.getXTo());
        assertEquals(15, result.getCount());
    }

    @Test
    void testDelete() {
        functionService.delete(1L);
        verify(functionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetByName() {
        FunctionEntity entity1 = new FunctionEntity();
        entity1.setFunctionId(1L);
        entity1.setName("Test Function");
        entity1.setXFrom(1.0);
        entity1.setXTo(5.0);
        entity1.setCount(10);

        FunctionEntity entity2 = new FunctionEntity();
        entity2.setFunctionId(2L);
        entity2.setName("Test Function");
        entity2.setXFrom(2.0);
        entity2.setXTo(6.0);
        entity2.setCount(12);

        when(functionRepository.findByName("Test Function")).thenReturn(Arrays.asList(entity1, entity2));

        List<FunctionDTO> results = functionService.getByName("Test Function");

        assertNotNull(results);
        assertEquals(2, results.size());

        FunctionDTO dto1 = results.get(0);
        assertEquals(1L, dto1.getFunctionId());
        assertEquals("Test Function", dto1.getName());

        FunctionDTO dto2 = results.get(1);
        assertEquals(2L, dto2.getFunctionId());
        assertEquals("Test Function", dto2.getName());
    }
}
