package clase;

public class Monomial {
    private final double coefficient;
    private final int exponent;

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }
    public double getCoefficient() {
        return coefficient;
    }
    public int getExponent() {
        return exponent;
    }
}
