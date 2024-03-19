package clase;
import javafx.util.Pair;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private final TreeMap<Integer, Double> monomials;
    private static final DecimalFormat decFor = new DecimalFormat("0.00");
    private static final Pattern REGEX_EXPR = Pattern.compile("([+-]?(?:(?:\\d*x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
    public Polynomial() {
        monomials = new TreeMap<>();
    }
    public Polynomial(TreeMap<Integer, Double> monomials) {
        this.monomials = new TreeMap<>(monomials);
    }
    public Polynomial(String expr) throws Exception {
        expr = expr.toLowerCase().replaceAll("\\*", "").replaceAll("\\s", ""); //facem totul low case si eliminam chestiile inutile precum spatii,etc
        Matcher matcher = REGEX_EXPR.matcher(expr);
        // Utilizăm un TreeMap temporar pentru a păstra termenii în ordine descrescătoare a exponentului
        TreeMap<Integer, Double> tempMonomials = new TreeMap<>(Comparator.reverseOrder());
        while (matcher.find()) {
            String group = matcher.group(1);
            String coeffString = "1";
            String expString = "1";
            try {
                if (group.contains("x")) {
                    int xIndex = group.indexOf('x');
                    if (xIndex > 0) {
                        coeffString = group.substring(0, xIndex);
                    }
                    int pIndex = group.indexOf("^");
                    if (pIndex != -1) {
                        expString = group.substring(pIndex + 1);
                    }
                } else {
                    coeffString = group;
                    expString = "0";
                }
                Double coeff = Double.parseDouble(coeffString.equals("-") ? "-1" : coeffString.equals("+") ? "1" : coeffString);
                Integer power = Integer.parseInt(expString);
                tempMonomials.put(power, tempMonomials.getOrDefault(power, 0.0) + coeff);
                if (Math.abs(tempMonomials.get(power)) < 0.001) {
                    tempMonomials.remove(power);
                }
            } catch (Exception ex) {
                throw new Exception("Failed to parse group: " + group);
            }
        }
        monomials = new TreeMap<>(Comparator.reverseOrder());// Inversăm ordinea termenilor în map
        monomials.putAll(tempMonomials);
    }
    public void addMonomial(int exponent, double coefficient) {
        monomials.put(exponent, coefficient);
    }
    public Polynomial add(Polynomial polyToAdd) {
        TreeMap<Integer, Double> result = new TreeMap<>(monomials);
        for (Map.Entry<Integer, Double> entry : polyToAdd.monomials.entrySet()) {
            int exponent = entry.getKey();
            Double coefficient = entry.getValue();
            Double currentCoefficient = result.getOrDefault(exponent, 0.0);
            result.put(exponent, currentCoefficient + coefficient);
        }
        return new Polynomial(result);
    }
    public Polynomial subtract(Polynomial polyToSubstract) {
        TreeMap<Integer, Double> coeff = new TreeMap<>(monomials);
        for (Map.Entry<Integer, Double> entry : polyToSubstract.monomials.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            double newVal = coeff.getOrDefault(key, 0.0) - value;
            coeff.put(key, newVal);
            if (Math.abs(newVal) < 0.001) {
                coeff.remove(key);
            }
        }
        return new Polynomial(coeff);
    }
    public Polynomial multiply(Polynomial polyToMultiply) {
        TreeMap<Integer, Double> result = new TreeMap<>();
        for (Map.Entry<Integer, Double> entry1 : monomials.entrySet()) {
            int exponent1 = entry1.getKey();
            double coefficient1 = entry1.getValue();

            for (Map.Entry<Integer, Double> entry2 : polyToMultiply.monomials.entrySet()) {
                int exponent2 = entry2.getKey();
                double coefficient2 = entry2.getValue();

                int newExponent = exponent1 + exponent2;// Calculăm noul exponent și noul coeficient
                double newCoefficient = coefficient1 * coefficient2;

                result.put(newExponent, result.getOrDefault(newExponent, 0.0) + newCoefficient);// Adăugăm noul termen în rezultat
            }
        }
        return new Polynomial(result);
    }
    public int highestDegree() {
        int highestDegree = 0;
        for (int degree : monomials.keySet()) {
            highestDegree = Math.max(highestDegree, degree);
        }
        return highestDegree;
    }
    public Pair<Polynomial, Polynomial> divide(Polynomial divisor) {
        Polynomial dividendCopy = new Polynomial(monomials);
        Polynomial divisorCopy = new Polynomial(divisor.monomials);

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        while (!dividendCopy.monomials.isEmpty() && dividendCopy.highestDegree() >= divisorCopy.highestDegree()) {
            int dividendDegree = dividendCopy.highestDegree();
            double dividendCoefficient = dividendCopy.monomials.getOrDefault(dividendDegree, 0.0);
            int divisorDegree = divisorCopy.highestDegree();
            double divisorCoefficient = divisorCopy.monomials.getOrDefault(divisorDegree, 0.0);
            double quotientCoefficient = dividendCoefficient / divisorCoefficient;
            int quotientDegree = dividendDegree - divisorDegree;

            quotient.addMonomial(quotientDegree, quotientCoefficient);
            TreeMap<Integer, Double> termCoefficients = new TreeMap<>();
            termCoefficients.put(quotientDegree, quotientCoefficient);

            Polynomial term = new Polynomial(termCoefficients);
            Polynomial product = divisorCopy.multiply(term);

            dividendCopy = dividendCopy.subtract(product);
            dividendCopy.monomials.entrySet().removeIf(entry -> Math.abs(entry.getValue()) < 0.001);
        }
        remainder = dividendCopy;
        return new Pair<>(quotient, remainder);
    }
    public Polynomial integrate() {
        TreeMap<Integer, Double> integratedMonomials = new TreeMap<>();
        for (Map.Entry<Integer, Double> entry : monomials.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            double integratedCoefficient = coefficient / (exponent + 1);
            int integratedExponent = exponent + 1;
            integratedMonomials.put(integratedExponent, integratedCoefficient);
        }
        integratedMonomials.put(0, 0.0);
        return new Polynomial(integratedMonomials);
    }
    public Polynomial differentiate() {
        TreeMap<Integer, Double> differentiatedMonomials = new TreeMap<>();
        for (Map.Entry<Integer, Double> entry : monomials.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (exponent > 0) {
                double differentiatedCoefficient = coefficient * exponent;
                int differentiatedExponent = exponent - 1;
                differentiatedMonomials.put(differentiatedExponent, differentiatedCoefficient);
            }
        }
        return new Polynomial(differentiatedMonomials);
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        boolean firstTerm = true;
        for (Map.Entry<Integer, Double> entry : monomials.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (coefficient == 0) { continue; }// Skip this term if coefficient is zero
            if (firstTerm) {
            firstTerm = false;
            if (coefficient < 0) {
                output.append("-");
                coefficient = -coefficient; // Make coefficient positive for display
            }
        } else {
                if (coefficient >= 0) {
                    output.append(" + ");
                } else {
                    output.append(" - ");
                    coefficient = -coefficient; // Make coefficient positive for display
                }
            }
            if (Math.abs(coefficient) != 1 || exponent == 0) {
                if (Math.floor(coefficient) == coefficient) {
                    output.append((int) Math.abs(coefficient));
                } else { output.append(decFor.format(Math.abs(coefficient))); }
            }
            if (exponent > 0) {
                output.append("x");
                if (exponent > 1) { output.append("^").append(exponent); }
            }
        }
        if (output.isEmpty()) {
            output.append("0");
        }
        return output.toString();
    }
}
