package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

    @Test
    void apply() {
        ConstantFunction test1 = new ConstantFunction(1.234);
        double expected1 = test1.apply(0.5);
        assertEquals(expected1, 1.234, 0.00001);
        ConstantFunction test2 = new ConstantFunction(-3.4539);
        double expected2 = test2.apply(-89);
        assertEquals(expected2, -3.4539, 0.00001);
        ZeroFunction test3 = new ZeroFunction();
        double expected3 = test3.apply(8.97709);
        assertEquals(expected3, 0, 0.00001);
        ZeroFunction test4 = new ZeroFunction();
        double expected4 = test4.apply(-5.686059);
        assertEquals(expected4, 0, 0.00001);
        UnitFunction test5 = new UnitFunction();
        double expected5 = test5.apply(6950.685909);
        assertEquals(expected5, 1, 0.00001);
        UnitFunction test6 = new UnitFunction();
        double expected6 = test6.apply(-9687.0486849);
        assertEquals(expected6, 1, 0.00001);
    }

    @Test
    void getC() {
        ConstantFunction test1 = new ConstantFunction(2.8795);
        double expected1 = test1.getC();
        assertEquals(2.8795, 2.8795, 0.0001);
        ConstantFunction test2 = new ConstantFunction(-89.023769);
        double expected2 = test2.getC();
        assertEquals(-89.023769, -89.023769, 0.000001);
    }
}