package functions;
public class PolinominalInterpolition implements MathFunction {
    private double[] xValues = new double[4];
    private double[] yValues = new double[4];
    private int count;
    public PolinominalInterpolition(double[] xValues, double[] yValues){
        this.xValues = xValues;
        this.yValues = yValues;
        count = xValues.length;
    }
        public double apply ( double x){

                    double S = 0;
                    for (int i = 0; i <= (count - 1); i++) {
                        double tn = 1;
                        double d = 1;
                        for (int j = 0; j <= (count - 1); j++) {
                            if (j == i) continue;
                            tn = tn * (x - xValues[j]);
                            d = d * (xValues[i] - xValues[j]);
                        }
                        double ir = tn / d;
                        S = S + yValues[i] * ir;
                    }
                    return S;
                }
    }


