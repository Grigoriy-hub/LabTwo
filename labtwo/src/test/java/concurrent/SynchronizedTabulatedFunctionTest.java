package concurrent;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;
import operations.LeftSteppingDifferentialOperator;
import operations.RightSteppingDifferentialOperator;
import operations.TabulatedDifferentialOperator;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


class SynchronizedTabulatedFunctionTest {

    @Test
    void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<TabulatedFunction> test1 = new SynchronizedTabulatedFunction.Operation<>() {
            TabulatedDifferentialOperator operation1 = new TabulatedDifferentialOperator();
            @Override
            public TabulatedFunction apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction) {
                return operation1.derive(synchronizedTabulatedFunction);
            }
        };
        ArrayTabulatedFunction test1A = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        SynchronizedTabulatedFunction test1S = new SynchronizedTabulatedFunction(test1A);
        TabulatedFunction test1D = test1S.doSynchronously(test1);
        double[] test1D_YValues = new double[test1D.getCount()];
        for (int i = 0; i < test1D.getCount(); ++i)
            test1D_YValues[i] = test1D.getY(i);
        Assert.assertEquals(test1D_YValues, new double[]{3, 5, 7, 7}, 0.001);

        SynchronizedTabulatedFunction.Operation<Void> test2 = new SynchronizedTabulatedFunction.Operation<>() {
            @Override
            public Void apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction) {
                return null;
            }
        };
        ArrayTabulatedFunction test2A = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 34, 56.5}, new double[]{0, 4, 9.67, -100.4, -0.001, 1000});
        SynchronizedTabulatedFunction test2S = new SynchronizedTabulatedFunction(test2A);
        Void test2D = test2S.doSynchronously(test2);
        Assert.assertTrue((test2D == null));

        SynchronizedTabulatedFunction.Operation<MathFunction> test3 = new SynchronizedTabulatedFunction.Operation<>() {
            LeftSteppingDifferentialOperator operation3 = new LeftSteppingDifferentialOperator(1);
            @Override
            public MathFunction apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction) {
                return operation3.derive(synchronizedTabulatedFunction);
            }
        };
        LinkedListTabulatedFunction test3A = new LinkedListTabulatedFunction(new double[]{-50, 0, 35, 400, 540}, new double[]{-89, 9, 9, 190, 200});
        SynchronizedTabulatedFunction test3S = new SynchronizedTabulatedFunction(test3A);
        MathFunction test3D = test3S.doSynchronously(test3);
        Assert.assertEquals(test3D.apply(-50), 1.96, 0.1);
        Assert.assertEquals(test3D.apply(0), 1.96, 0.1);
        Assert.assertEquals(test3D.apply(35), 0, 0.1);
        Assert.assertEquals(test3D.apply(400), 0.5, 0.1);
        Assert.assertEquals(test3D.apply(540), 0.07, 0.1);

        SynchronizedTabulatedFunction.Operation<MathFunction> test4 = new SynchronizedTabulatedFunction.Operation<>() {
            RightSteppingDifferentialOperator operation4 = new RightSteppingDifferentialOperator(1);
            @Override
            public MathFunction apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction) {
                return operation4.derive(synchronizedTabulatedFunction);
            }
        };
        LinkedListTabulatedFunction test4A = new LinkedListTabulatedFunction(new double[]{-100, 10, 40, 600, 700, 1000}, new double[]{-100, 0, -10, 200, 0, 100});
        SynchronizedTabulatedFunction test4S = new SynchronizedTabulatedFunction(test4A);
        MathFunction test4D = test4S.doSynchronously(test4);
        Assert.assertEquals(test4D.apply(-100), 0.9, 0.1);
        Assert.assertEquals(test4D.apply(10), -0.3, 0.1);
        Assert.assertEquals(test4D.apply(40), 0.375, 0.1);
        Assert.assertEquals(test4D.apply(600), -2, 0.1);
        Assert.assertEquals(test4D.apply(700), 0.3, 0.1);
        Assert.assertEquals(test4D.apply(1000), 0.3, 0.1);







    }
}