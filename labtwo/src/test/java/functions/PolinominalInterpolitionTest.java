package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolinominalInterpolitionTest {

    @Test
    void apply() {
        PolinominalInterpolition test1 = new PolinominalInterpolition(new double[]{-1, 0, 1, 2}, new double[]{-10, -1, 5, 15});
        double expected1 = test1.apply(-0.25);
        assertEquals(expected1, -2.69531, 0.00001 );
        PolinominalInterpolition test2 = new PolinominalInterpolition(new double[]{0, 1, 2}, new double[]{1, 1, 3});
        double expected2 = test2.apply( 0.5);
        assertEquals(expected2, 0.75, 0.000001);


    }
}