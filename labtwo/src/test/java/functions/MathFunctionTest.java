package functions;

import org.junit.jupiter.api.Test;

class MathFunctionTest {

    @Test
    void andThen() {
        SqrFunction h1 = new SqrFunction();
        IdentityFunction g1 = new IdentityFunction();
        ConstantFunction f1 = new ConstantFunction(9);
        //double result1 = f1.andThen(g1).andThen(h1).apply(5);
        //assertEquals(result1, 9, 0.00001);
        SqrFunction h2 = new SqrFunction();
        IdentityFunction g2 = new IdentityFunction();
        ConstantFunction f2 = new ConstantFunction(4);
        //double result2 = g2.andThen(h2).andThen(f2).apply(5);
        //assertEquals(result2, 16, 0.00001);
        PolinominalInterpolition h3 = new PolinominalInterpolition(new double[]{-1, 0, 1, 2}, new double[]{-10, -1, 5, 15});
        IdentityFunction f3 = new IdentityFunction();
        SqrFunction g3 = new SqrFunction();
        //double result3 = g3.andThen(f3).andThen(h3).apply(-0.25);
        //assertEquals(result3, 7.264696, 0.0001);
        PolinominalInterpolition h4 = new PolinominalInterpolition(new double[]{0, 1, 2}, new double[]{1, 1, 3});
        IdentityFunction f4 = new IdentityFunction();
        SqrFunction g4 = new SqrFunction();
        //double result4 = g4.andThen(f4).andThen(h4).apply(0.5);
        //assertEquals(result4, 0.5625, 0.0001);
        PolinominalInterpolition h5 = new PolinominalInterpolition(new double[]{0, 1, 2}, new double[]{1, 1, 3});
        UnitFunction f5 = new UnitFunction();
        SqrFunction g5 = new SqrFunction();
        //double result5 = g5.andThen(f5).andThen(h5).apply(0.5);
        //assertEquals(result5, 1, 0.0001);


    }
}