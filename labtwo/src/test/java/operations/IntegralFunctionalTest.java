package operations;

import functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class IntegralFunctionalTest {
    IntegralFunctional test1=new IntegralFunctional();
    IntegralFunctional test2=new IntegralFunctional(3);
    @Test
    void integrate_ExpectEqual_AllAvailableThreadsWithTabulatedFunction()throws ExecutionException,InterruptedException{
        var t_func=new LinkedListTabulatedFunction((double x)-> x*2.0,0,1000,1001);
        assertEquals(1000000.0,test1.integrate(t_func),6);
    }
    @Test
    void integrate_ExpectEqual_ThreeThreadsWithTabulatedFunction()throws ExecutionException,InterruptedException{
        var t_func=new LinkedListTabulatedFunction((double x)-> x*2.0,0,1000,1001);
        assertEquals(1000000.0,test2.integrate(t_func,0,1000),0.01);
    }
    @Test
    void constructor_ExpectException_NumOfThreadIsZeroOrLess(){
        assertThrows(IllegalArgumentException.class,()->new IntegralFunctional(0));
    }
}