package dbServices.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionDTOTest {

    @Test
    void testGettersAndSetters() {

        FunctionDTO functionDTO = new FunctionDTO();


        functionDTO.setFunctionId(1L);
        functionDTO.setName("Test Function");
        functionDTO.setXFrom(0.0);
        functionDTO.setXTo(10.0);
        functionDTO.setCount(100);


        assertEquals(1L, functionDTO.getFunctionId());
        assertEquals("Test Function", functionDTO.getName());
        assertEquals(0.0, functionDTO.getXFrom());
        assertEquals(10.0, functionDTO.getXTo());
        assertEquals(100, functionDTO.getCount());
    }

    @Test
    void testJsonSerialization() throws Exception {

        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setFunctionId(1L);
        functionDTO.setName("Test Function");
        functionDTO.setXFrom(0.0);
        functionDTO.setXTo(10.0);
        functionDTO.setCount(100);

        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writeValueAsString(functionDTO);


        assertTrue(json.contains("\"functionId\":1"));
        assertTrue(json.contains("\"name\":\"Test Function\""));
        assertTrue(json.contains("\"xFrom\":0.0"));
        assertTrue(json.contains("\"xTo\":10.0"));
        assertTrue(json.contains("\"count\":100"));
    }

    @Test
    void testJsonDeserialization() throws Exception {

        String json = """
                {
                    "functionId": 1,
                    "name": "Test Function",
                    "xFrom": 0.0,
                    "xTo": 10.0,
                    "count": 100
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();

        FunctionDTO functionDTO = objectMapper.readValue(json, FunctionDTO.class);

        assertEquals(1L, functionDTO.getFunctionId());
        assertEquals("Test Function", functionDTO.getName());
        assertEquals(0.0, functionDTO.getXFrom());
        assertEquals(10.0, functionDTO.getXTo());
        assertEquals(100, functionDTO.getCount());
    }
}
