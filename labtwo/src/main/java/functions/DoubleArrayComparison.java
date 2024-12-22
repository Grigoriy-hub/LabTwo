package functions;
import static java.lang.Math.abs;

public class DoubleArrayComparison {
    public static boolean areEqual(double[] x, double[] y) {
        for (int i = 0; i < x.length; i++) {
            if (abs(x[i] - y[i]) > 0.0000001) {
                return false;
            }
        }
        return true;
    }
}