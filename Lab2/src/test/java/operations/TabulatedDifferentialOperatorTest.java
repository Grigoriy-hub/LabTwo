package operations;

import functions.ArrayTabulatedFunction;
import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;
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
}