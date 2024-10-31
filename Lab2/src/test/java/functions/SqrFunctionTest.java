package functions;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.Test;
import static functions.DoubleArrayComparison.areEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SqrFunctionTest {
    SqrFunction c = new SqrFunction();
    @Test
    public void apply() {

        double[] actual = new double[]{6.60117372, 87.6977934, 6241, 14641};

        double[] expected = new double[4];
        expected[0] = c.apply(2.569274945);
        expected[1] = c.apply(-9.36471);
        expected[2] = c.apply(-79);
        expected[3] = c.apply(121);

        assertTrue(areEqual(actual, expected));

    }
}