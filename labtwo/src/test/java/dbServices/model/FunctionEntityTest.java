package dbServices.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FunctionEntityTest {

    @Test
    public void testSetAndGetFunctionId() {
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setFunctionId(1L);
        assertEquals(1L, functionEntity.getFunctionId());
    }

    @Test
    public void testSetAndGetName() {
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setName("Test Function");
        assertEquals("Test Function", functionEntity.getName());
    }

    @Test
    public void testSetAndGetXFrom() {
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setXFrom(1.0);
        assertEquals(1.0, functionEntity.getXFrom());
    }

    @Test
    public void testSetAndGetXTo() {
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setXTo(5.0);
        assertEquals(5.0, functionEntity.getXTo());
    }

    @Test
    public void testSetAndGetCount() {
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setCount(10);
        assertEquals(10, functionEntity.getCount());
    }

    @Test
    public void testSetAndGetPoints() {
        FunctionEntity functionEntity = new FunctionEntity();

        PointEntity point1 = new PointEntity();
        point1.setX(1.0);
        point1.setY(1.0);

        PointEntity point2 = new PointEntity();
        point2.setX(2.0);
        point2.setY(2.0);

        List<PointEntity> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);

        functionEntity.setPoints(points);

        assertNotNull(functionEntity.getPoints());
        assertEquals(2, functionEntity.getPoints().size());
        assertEquals(1.0, functionEntity.getPoints().get(0).getX());
        assertEquals(2.0, functionEntity.getPoints().get(1).getX());
    }

    @Test
    public void testAddAndRemovePoint() {
        FunctionEntity functionEntity = new FunctionEntity();
        PointEntity point = new PointEntity();
        point.setX(1.0);
        point.setY(1.0);

        functionEntity.getPoints().add(point);
        assertEquals(1, functionEntity.getPoints().size());
        assertEquals(1.0, functionEntity.getPoints().get(0).getX());

        functionEntity.getPoints().remove(point);
        assertEquals(0, functionEntity.getPoints().size());
    }
}
