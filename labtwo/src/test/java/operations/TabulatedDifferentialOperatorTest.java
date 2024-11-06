package operations;

import concurrent.SynchronizedTabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {
    private TabulatedDifferentialOperator t_diffOp;
    @BeforeEach
    void t_constructor(){
        t_diffOp=new TabulatedDifferentialOperator();
    }
    @Test
    void getFactory_ExpectArrayTabulatedFactory(){
        assertInstanceOf(ArrayTabulatedFunctionFactory.class,t_diffOp.getFactory());
    }
    @Test
    void setFactory_ExpectLinkedListTabulatedFactory(){
        t_diffOp.setFactory(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class,t_diffOp.getFactory());
    }
    @Test
    void derive_expectLinkedListWithZero_Constant(){
        TabulatedDifferentialOperator t_diffOp2=new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        var t_func=t_diffOp2.derive(new ArrayTabulatedFunction(new ConstantFunction(5),0,6,5));
        assertInstanceOf(LinkedListTabulatedFunction.class,t_func);
        assertEquals(5,t_func.getCount());
        for(int i=0;i<t_func.getCount();i++)
            assertEquals(0,t_func.getY(i));
    }
    @Test
    void derive_expectArrayWithOne_Linear(){
        TabulatedDifferentialOperator t_diffOp2=new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        var t_func=t_diffOp2.derive(new LinkedListTabulatedFunction((x)-> x,0,6,5));
        assertInstanceOf(ArrayTabulatedFunction.class,t_func);
        assertEquals(5,t_func.getCount());
        for(int i=0;i<t_func.getCount();i++)
            assertEquals(1,t_func.getY(i));
    }

    @Test
    void deriveSynchronously() {
        TabulatedDifferentialOperator test1 = new TabulatedDifferentialOperator();
        TabulatedFunction test1A = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedFunction test1DeriveS = test1.deriveSynchronously(test1A);
        double[] test1DeriveS_YValues = new double[test1DeriveS.getCount()];
        double[] test1DeriveS_XValues = new double[test1DeriveS.getCount()];
        for(int i = 0; i < test1DeriveS.getCount(); ++i){
            test1DeriveS_YValues[i] = test1DeriveS.getY(i);
            test1DeriveS_XValues[i] = test1DeriveS.getX(i);
        }
        assertArrayEquals(test1DeriveS_YValues, new double[]{3, 5, 7, 7}, 0.1);
        assertArrayEquals(test1DeriveS_XValues, new double[]{1, 2, 3, 4});

        TabulatedDifferentialOperator test2 = new TabulatedDifferentialOperator( new LinkedListTabulatedFunctionFactory());
        TabulatedFunction test2A = new LinkedListTabulatedFunction(new double[]{-10, 0, 10, 100, 200}, new double[]{-100, 0, 0, 100, 1000});
        TabulatedFunction test2DeriveS = test2.deriveSynchronously(test2A);
        double[] test2DeriveS_YValues = new double[test2DeriveS.getCount()];
        double[] test2DeriveS_XValues = new double[test2DeriveS.getCount()];
        for(int i = 0; i < test2DeriveS.getCount(); ++i){
            test2DeriveS_YValues[i] = test2DeriveS.getY(i);
            test2DeriveS_XValues[i] = test2DeriveS.getX(i);
        }
        assertArrayEquals(test2DeriveS_YValues, new double[]{10, 0, 1.1, 9, 9}, 0.1);
        assertArrayEquals(test2DeriveS_XValues, new double[]{-10, 0, 10, 100, 200});

        TabulatedDifferentialOperator test3 = new TabulatedDifferentialOperator( new ArrayTabulatedFunctionFactory());
        TabulatedFunction test3A = new LinkedListTabulatedFunction(new double[]{-500, -400, 100, 150, 160, 250 }, new double[]{-100, -200, 0, 30, 90, 1});
        TabulatedFunction test3DeriveS = test3.deriveSynchronously(test3A);
        double[] test3DeriveS_YValues = new double[test3DeriveS.getCount()];
        double[] test3DeriveS_XValues = new double[test3DeriveS.getCount()];
        for(int i = 0; i < test3DeriveS.getCount(); ++i){
            test3DeriveS_YValues[i] = test3DeriveS.getY(i);
            test3DeriveS_XValues[i] = test3DeriveS.getX(i);
        }
        assertArrayEquals(test3DeriveS_YValues, new double[]{-1, 0.4, 0.6, 6, -1, -1}, 0.1);
        assertArrayEquals(test3DeriveS_XValues, new double[]{-500, -400, 100, 150, 160, 250});

        TabulatedDifferentialOperator test4 = new TabulatedDifferentialOperator();
        TabulatedFunction test4A = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        SynchronizedTabulatedFunction test4Sync = new SynchronizedTabulatedFunction(test4A);
        TabulatedFunction test4DeriveS = test4.deriveSynchronously(test4Sync);
        double[] test4DeriveS_YValues = new double[test4DeriveS.getCount()];
        double[] test4DeriveS_XValues = new double[test4DeriveS.getCount()];
        for(int i = 0; i < test4DeriveS.getCount(); ++i){
            test4DeriveS_YValues[i] = test4DeriveS.getY(i);
            test4DeriveS_XValues[i] = test4DeriveS.getX(i);
        }
        assertArrayEquals(test4DeriveS_YValues, new double[]{3, 5, 7, 7}, 0.1);
        assertArrayEquals(test4DeriveS_XValues, new double[]{1, 2, 3, 4});






    }
}