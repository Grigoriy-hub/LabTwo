package functions;

import exceptions.InterpolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void getCount() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        int expected1 = test1.getCount();
        assertEquals(expected1, 4);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        int expected2 = test2.getCount();
        assertEquals(expected2, 7);

    }

    @Test
    void getX() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        double expected1 = test1.getX(0);
        assertEquals(expected1, -1, 0.000001);
        double expected2 = test1.getX(3);
        assertEquals(expected2, 97.3459, 0.000001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        double expected3 = test2.getX(6);
        assertEquals(expected3, 567778, 0.000001);

    }

    @Test
    void getY() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        double expected1 = test1.getY(0);
        assertEquals(expected1, -198, 0.000001);
        double expected2 = test1.getY(3);
        assertEquals(expected2, -200.22229, 0.000001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        double expected3 = test2.getY(6);
        assertEquals(expected3, -9, 0.000001);
    }

    @Test
    void setY() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test1.setY(2, -50);
        assertEquals(test1.yValues[2], -50, 0.000001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        test2.setY(6, 0);
        assertEquals(test2.yValues[6], 0, 0.000001);
    }

    @Test
    void leftBound() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test1.leftBound();
        assertEquals(test1.leftBound(), -1, 0.000001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        test2.leftBound();
        assertEquals(test2.leftBound(), -111.11, 0.000001);

    }

    @Test
    void rightBound() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test1.rightBound();
        assertEquals(test1.rightBound(), 97.3459, 0.000001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        test2.rightBound();
        assertEquals(test2.rightBound(), 567778, 0.000001);
    }

    @Test
    void indexOfX() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        int expected1 = test1.indexOfX(0.1111);
        assertEquals(expected1, 1);
        int expected2 = test1.indexOfX(90);
        assertEquals(expected2, -1);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        int expected3 = test2.indexOfX(23094);
        assertEquals(expected3, 5);
        int expected4 = test2.indexOfX(3);
        assertEquals(expected4, -1);

    }

    @Test
    void indexOfY() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        int expected1 = test1.indexOfY(-198);
        assertEquals(expected1, 0);
        int expected2 = test1.indexOfY(90);
        assertEquals(expected2, -1);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        int expected3 = test2.indexOfY(-9087);
        assertEquals(expected3, 3);
        int expected4 = test2.indexOfY(3);
        assertEquals(expected4, -1);
    }

    @Test
    void floorIndexOfX() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        int expected1 = test1.floorIndexOfX(-2);
        assertEquals(expected1, 0);
        int expected2 = test1.floorIndexOfX(100);
        assertEquals(expected2, 4);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        int expected3 = test2.floorIndexOfX(500);
        assertEquals(expected3, 2);
        int expected4 = test2.floorIndexOfX(40000);
        assertEquals(expected4, 5);
    }

    @Test
    void extrapolateRight() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        double expected1 = test1.extrapolateRight(-5);
        assertEquals(expected1, -188.40459012, 0.00001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        double expected2 = test2.extrapolateRight(-10.12984);
        assertEquals(expected2, -1.7030775, 0.00001);
    }

    @Test
    void extrapolateLeft() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        double expected1 = test1.extrapolateLeft(100);
        assertEquals(expected1, 13709.83907839, 0.00001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        double expected2 = test2.extrapolateLeft(600000);
        assertEquals(expected2, -7832133.2088208, 0.00001);
    }

    @Test
    void interpolate() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        int help1 = test1.floorIndexOfX(0.5);
        double expected1 = test1.interpolate(0.5, help1);
        assertEquals(expected1, -45.76295988, 0.00001);
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-111.11, -100, 456.345, 975.345, 1234, 23094, 567778}, new double[]{100, -45, -19, -9087, -1, -2, -9});
        int help2 = test2.floorIndexOfX(500);
        double expected2 = test2.interpolate(500, help2);
        assertEquals(expected2, -781.7428516, 0.00001);
        Assertions.assertThrows(InterpolationException.class, () -> {
            ArrayTabulatedFunction test3 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help3 = test3.floorIndexOfX(-6);
            test3.interpolate(-6, help3);
        });
            Assertions.assertThrows(InterpolationException.class, () -> {
            ArrayTabulatedFunction test4 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help4 = test4.floorIndexOfX(1);
            test4.interpolate(1, help4);
        });
        Assertions.assertThrows(InterpolationException.class, () -> {
            ArrayTabulatedFunction test5 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help5 = test5.floorIndexOfX(3);
            test5.interpolate(3, help5);
        });
        Assertions.assertThrows(InterpolationException.class, () -> {
            ArrayTabulatedFunction test6 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help6 = test6.floorIndexOfX(4);
            test6.interpolate(4, help6);
        });
        Assertions.assertThrows(InterpolationException.class, () -> {
            ArrayTabulatedFunction test7 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help7 = test7.floorIndexOfX(7);
            test7.interpolate(7, help7);
        });
    }

    @Test
    void iterator() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        Iterator<Point> iterator1 = test1.iterator();
        int j = 0;
        double[] array1 = new double[6];
        while (iterator1.hasNext()) {
            Point point = iterator1.next();
            array1[j] = point.x;
            j++;
        }
        assertEquals(array1[0], 1);
        assertEquals(array1[1], 2);
        assertEquals(array1[2], 3);
        assertEquals(array1[3], 4);
        assertEquals(array1[4], 0);
        assertEquals(array1[4], 0);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-10, -2, 35, 64, 87}, new double[]{1, 2, 3, 4, 0});
        Iterator<Point> iterator2 = test2.iterator();
        Point[] array2 = new Point[8];
        double[] array3 = new double[8];
        int k = 0;
        for (Point point : array2 ) {
            point = iterator2.next();
            array3[k] = point.x;
            k++;
        }
        assertEquals(array3[0], -10);
        assertEquals(array3[1], -2);
        assertEquals(array3[2], 35);
        assertEquals(array3[3], 64);
        assertEquals(array3[4], 87);
        assertEquals(array3[5], 0);
        assertEquals(array3[6], 0);
        assertEquals(array3[7], 0);
        });







    }
}