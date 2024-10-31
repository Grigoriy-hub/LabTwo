package functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockTabulatedFunctionTest {
    private  MockTabulatedFunction t_func;
    @BeforeEach
    void tFunConstructor(){
        t_func=new MockTabulatedFunction(1.0,2.0,3.0,4.0);
    }
    @Test
    void apply_ExpectEqual_ExtrapolateLeft(){
        assertEquals(2.0,t_func.apply(0));
    }
    @Test
    void apply_ExpectEqual_ExtrapolateRight(){
        assertEquals(7.0,t_func.apply(5.0));
    }
    @Test
    void apply_ExpectEqual_Interpolate(){
        assertEquals(3.5,t_func.apply(1.5));
    }
    @Test
    void applyInterpolate4Param_ExpectEqual_Interpolate(){
        double x0=t_func.getX(0);
        double x1=t_func.getX(1);
        double y0=t_func.getY(0);
        double y1=t_func.getY(1);
        assertEquals(t_func.interpolate(1.5,x0,x1,y0,y1),t_func.apply(1.5));
    }
    @Test
    void applyInterpolate2Param_ExpectEqual_Interpolate(){
        assertEquals(t_func.interpolate(1.5,0),t_func.apply(1.5));
    }

}