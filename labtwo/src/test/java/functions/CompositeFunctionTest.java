package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {
    private final  MathFunction sin=new MathFunction() {
        @Override
        public double apply(double arg) {
            return Math.sin(arg);
        }
    };
    IdentityFunction t_identity_func= new IdentityFunction();
    private final CompositeFunction t_func=new CompositeFunction(t_identity_func,sin);
    @Test
    void applyToX_ExpectSinX_IdentityInSin(){
        assertEquals(sin.apply(Math.PI),t_func.apply(Math.PI),0.00001);
    }

}