package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewtsMethFunctionTest{
    private final  MathFunction sin=new MathFunction() {
        @Override
        public double apply(double arg) {
            return Math.sin(arg);
        }
    };
    private final NewtsMethFunction t_func1=new NewtsMethFunction(sin,1);
    @Test
    void applyNewtsMethToSin_ExpectTrue_LowPrecision(){
        assertEquals(Math.PI,t_func1.apply(2),1);
    }
    private final NewtsMethFunction t_func2=new NewtsMethFunction(sin,1e-6);
    @Test
    void applyNewtsMethToSin_ExpectTrue_HighPrecision(){
        assertEquals(Math.PI,t_func2.apply(2),1e-6);
    }
    @Test
    void applyNewtsMethToSin_NotConverge_DerivIsZero(){
        assertTrue(Double.isNaN(t_func2.apply(Math.PI/2)));
    }
    private final NewtsMethFunction t_func3=new NewtsMethFunction(new LinkedListTabulatedFunction(new double[] {1.0,2.0},new double[]{0,4.0}),1);
    @Test
    void applyNewtsMethToTabulated_ExpectTrue_LowPrecision(){
        assertEquals(1,t_func3.apply(2),1);
    }
    private final NewtsMethFunction t_func4=new NewtsMethFunction(new LinkedListTabulatedFunction(new double[] {1.0,2.0},new double[]{0,4.0}),1e-6);
    @Test
    void applyNewtsMethToTabulated_ExpectTrue_HighPrecision(){
        assertEquals(1,t_func3.apply(2),1e-6);
    }

}