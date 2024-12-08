package dbServices.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointDTOTest {

    @Test
    void testGettersAndSetters() {
        PointDTO pointDTO = new PointDTO();

        pointDTO.setPointId(1L);
        pointDTO.setFunctionId(2L);
        pointDTO.setX(10.5);
        pointDTO.setY(20.5);

        assertEquals(1L, pointDTO.getPointId());
        assertEquals(2L, pointDTO.getFunctionId());
        assertEquals(10.5, pointDTO.getX());
        assertEquals(20.5, pointDTO.getY());
    }

    @Test
    void testJsonSerialization() throws Exception {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setPointId(1L);
        pointDTO.setFunctionId(2L);
        pointDTO.setX(10.5);
        pointDTO.setY(20.5);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(pointDTO);

        assertTrue(json.contains("\"pointId\":1"));
        assertTrue(json.contains("\"functionId\":2"));
        assertTrue(json.contains("\"x\":10.5"));
        assertTrue(json.contains("\"y\":20.5"));
    }

    @Test
    void testJsonDeserialization() throws Exception {

        String json = """
                {
                    "pointId": 1,
                    "functionId": 2,
                    "x": 10.5,
                    "y": 20.5
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();

        PointDTO pointDTO = objectMapper.readValue(json, PointDTO.class);

        assertEquals(1L, pointDTO.getPointId());
        assertEquals(2L, pointDTO.getFunctionId());
        assertEquals(10.5, pointDTO.getX());
        assertEquals(20.5, pointDTO.getY());
    }
}
