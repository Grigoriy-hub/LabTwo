package dbServices.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointEntityTest {

    @Test
    void testSetAndGetPointId() {
        PointEntity point = new PointEntity();
        Long pointId = 1L;
        point.setPointId(pointId);
        assertEquals(pointId, point.getPointId());
    }

    @Test
    void testSetAndGetFunction() {
        PointEntity point = new PointEntity();
        FunctionEntity function = new FunctionEntity();
        function.setFunctionId(10L);
        function.setName("Test Function");

        point.setFunction(function);
        assertEquals(function, point.getFunction());
        assertEquals("Test Function", point.getFunction().getName());
    }

    @Test
    void testSetAndGetX() {
        PointEntity point = new PointEntity();
        Double xValue = 2.5;
        point.setX(xValue);
        assertEquals(xValue, point.getX());
    }

    @Test
    void testSetAndGetY() {
        PointEntity point = new PointEntity();
        Double yValue = 3.7;
        point.setY(yValue);
        assertEquals(yValue, point.getY());
    }

    @Test
    void testAssociationWithFunction() {
        PointEntity point = new PointEntity();
        FunctionEntity function = new FunctionEntity();
        function.setName("Sample Function");

        point.setFunction(function);
        assertNotNull(point.getFunction());
        assertEquals("Sample Function", point.getFunction().getName());
    }
}
