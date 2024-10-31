package operations;

import functions.IdentityFunction;
import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        RightSteppingDifferentialOperator test1 = new RightSteppingDifferentialOperator(1);
        SqrFunction test1S = new SqrFunction();
        MathFunction test1Function = test1.derive(test1S);
        assertEquals(test1Function.apply(1), 3);
        assertEquals(test1Function.apply(5), 11);

        RightSteppingDifferentialOperator test2 = new RightSteppingDifferentialOperator(0.00000001);
        SqrFunction test2S = new SqrFunction();
        MathFunction test2Function = test2.derive(test2S);
        assertEquals(test2Function.apply(1), 2, 0.0001);
        assertEquals(test2Function.apply(5), 10, 0.0001);

        RightSteppingDifferentialOperator test3 = new RightSteppingDifferentialOperator(1);
        IdentityFunction test3S = new IdentityFunction();
        MathFunction test3Function = test3.derive(test3S);
        assertEquals(test3Function.apply(1), 1);
        assertEquals(test3Function.apply(5), 1);

        RightSteppingDifferentialOperator test4 = new RightSteppingDifferentialOperator(0.00000001);
        IdentityFunction test4S = new IdentityFunction();
        MathFunction test4Function = test4.derive(test4S);
        assertEquals(test4Function.apply(1), 1, 0.0001);
        assertEquals(test4Function.apply(5), 1, 0.0001);
    }
}