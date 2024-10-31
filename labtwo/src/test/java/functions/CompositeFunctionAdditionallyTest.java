package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionAdditionallyTest {

    @Test
    void apply() {
    ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new SqrFunction(), -2, 7, 11);
    ArrayTabulatedFunction test_1 = new ArrayTabulatedFunction(new ConstantFunction(9), 9, 21, 13);
    CompositeFunction test__1 = new CompositeFunction(test1, test_1);
    assertEquals(test__1.apply(9), 9, 0.000001);

    ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new SqrFunction(), 101, 789, 29);
    ArrayTabulatedFunction test_2 = new ArrayTabulatedFunction(new ConstantFunction(9), -53, -30, 17);
    CompositeFunction test__2 = new CompositeFunction(test_2, test2);
    assertEquals(test__2.apply(9), -10643.5714285, 0.000001);

    ArrayTabulatedFunction test3 = new ArrayTabulatedFunction(new SqrFunction(), -200, 500, 100);
    PolinominalInterpolition test_3 = new PolinominalInterpolition(test3.xValues, test3.yValues);
    CompositeFunction test__3 = new CompositeFunction(test3, test_3);
    assertEquals(test__3.apply(20), 164074.6460036,0.0001);

    ArrayTabulatedFunction test4 = new ArrayTabulatedFunction(new ZeroFunction(), 30, 40, 100);
    PolinominalInterpolition test_4 = new PolinominalInterpolition(test4.xValues, test4.yValues);
    CompositeFunction test__4 = new CompositeFunction(test4, test_4);
    assertEquals(test__4.apply(20), 0,0.0001);

    ArrayTabulatedFunction test5 = new ArrayTabulatedFunction(new UnitFunction(), 125, 200, 10);
    PolinominalInterpolition test_5 = new PolinominalInterpolition(test5.xValues, test5.yValues);
    CompositeFunction test__5 = new CompositeFunction(test5, test_5);
    assertEquals(test__5.apply(30), 1,0.0001);













    }
}