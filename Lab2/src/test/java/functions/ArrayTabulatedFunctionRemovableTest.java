package functions;
import static functions.DoubleArrayComparison.areEqual;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArrayTabulatedFunctionRemovableTest {

    @Test
    void remove() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test1.remove(0);
        assertTrue(areEqual(test1.xValues, new double[]{0.1111, 78.1, 97.3459}));
        assertTrue(areEqual(test1.yValues, new double[]{-45, -198, -200.22229}));

        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test2.remove(2);
        assertTrue(areEqual(test2.xValues, new double[]{-1, 0.1111, 97.3459}));
        assertTrue(areEqual(test2.yValues, new double[]{-198, -45, -200.22229}));

        ArrayTabulatedFunction test3 = new ArrayTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test3.remove(3);
        assertTrue(areEqual(test3.xValues, new double[]{-1, 0.1111, 78.1}));
        assertTrue(areEqual(test3.yValues, new double[]{-198, -45, -198}));
    }
    }
