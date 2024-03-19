package clase;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static javax.swing.UIManager.put;
import static org.junit.jupiter.api.Assertions.assertEquals;
import clase.Polynomial;
import clase.Monomial;

import java.util.TreeMap;

public class ApplicationTests {
    @Test
    public void test1Addition() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 3.0);
            put(1, 2.0);
            put(0, 1.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(1, 2.0);
            put(0, 1.0);
        }});
        Polynomial result = p1.add(p2);
        Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 3.0);
            put(1, 4.0);
            put(0, 2.0);
        }});
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void test2Addition() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(3, 5.0);
            put(2, 3.0);
            put(1, 2.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 1.0);
            put(1, 4.0);
            put(0, 1.0);
        }});
        Polynomial result = p1.add(p2);
        Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
            put(3, 5.0);
            put(2, 4.0);
            put(1, 6.0);
            put(0, 1.0);
        }});
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void test1Substract() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(3, 5.0); //5x^3+3x^2+2x - x^2 - 4x - 1
            put(2, 3.0);
            put(1, 2.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 1.0);
            put(1, 4.0);
            put(0, 1.0);
        }});
        Polynomial result = p1.subtract(p2);
        Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
            put(3, 5.0);
            put(2, 2.0);
            put(1, -2.0);
            put(0, -1.0);
        }});
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void test2Substract() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 3.0);
            put(1, 2.0);
            put(0, 1.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 1.0);
            put(1, 1.0);
        }});
        Polynomial result = p1.subtract(p2);
        Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 2.0);
            put(1, 1.0);
            put(0, 1.0);
        }});
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void testMultiplication1() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 3.0);
            put(1, 2.0);
            put(0, 1.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(1, 2.0);
            put(0, 1.0);
        }});
        Polynomial result = p1.multiply(p2);
        Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
            put(3, 6.0);
            put(2, 7.0);
            put(1, 4.0);
            put(0, 1.0);
        }});
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void testMultiplication2() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 1.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(1, 1.0);
            put(0, 1.0);
        }});
        Polynomial result = p1.multiply(p2);
        Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
            put(3, 1.0);
            put(2, 1.0);
        }});
    }
    @Test
public void testDivision1() {
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(2, 6.0);
            put(1, 3.0);
            put(0, 1.0);
        }});
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
            put(1, 2.0);
            put(0, 1.0);
        }});
        Pair<Polynomial, Polynomial> result = p1.divide(p2);
        Polynomial expectedQuotient = new Polynomial(new TreeMap<Integer, Double>() {{
            put(1, 3.0);
        }});
        Polynomial expectedRemainder = new Polynomial(new TreeMap<Integer, Double>() {{
            put(0, 1.0);
        }});
        assertEquals(expectedQuotient.toString(), result.getKey().toString());
        assertEquals(expectedRemainder.toString(), result.getValue().toString());
    }

@Test
public void testDivision2() {
    Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
        put(2, 6.0);
        put(1, 3.0);
        put(0, 1.0);
    }});
    Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
        put(1, 2.0);
        put(0, 1.0);
    }});
    Pair<Polynomial, Polynomial> result = p1.divide(p2);
    Polynomial expectedQuotient = new Polynomial(new TreeMap<Integer, Double>() {{
        put(1, 3.0);
    }});
    Polynomial expectedRemainder = new Polynomial(new TreeMap<Integer, Double>() {{
        put(0, 1.0);
    }});
    assertEquals(expectedQuotient.toString(), result.getKey().toString());
    assertEquals(expectedRemainder.toString(), result.getValue().toString());
}
@Test
public void testIntegration1() {
    Polynomial p1 = new Polynomial(new TreeMap<Integer, Double>() {{
        put(2, 3.0);
        put(1, 2.0);
        put(0, 1.0);
    }});
    Polynomial result = p1.integrate();
    Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
        put(3, 1.0);
        put(2, 1.0);
        put(1, 1.0);
        put(0, 0.0);
    }});
    assertEquals(expectedResult.toString(), result.toString());
}

@Test
public void testIntegration2() {
    Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
        put(3, 4.0);
        put(2, 3.0);
        put(1, 2.0);
        put(0, 1.0);
    }});
    Polynomial result = p2.integrate();
    Polynomial expectedResult = new Polynomial(new TreeMap<Integer, Double>() {{
        put(4, 1.0);
        put(3, 1.0);
        put(2, 1.0);
        put(1, 1.0);
        put(0, 0.0);
    }});
    assertEquals(expectedResult.toString(), result.toString());
}
@Test
public void testDifferentiation1() {
    Polynomial p = new Polynomial(new TreeMap<Integer, Double>() {{
        put(3, 2.0);
        put(2, -1.0);
        put(1, 3.0);
        put(0, -2.0);
    }});
    Polynomial result = p.differentiate();
    Polynomial expected = new Polynomial(new TreeMap<Integer, Double>() {{
        put(2, 6.0);
        put(1, -2.0);
        put(0, 3.0);
    }});
    assertEquals(expected.toString(), result.toString());
}

@Test
public void testDifferentiation2() {
    Polynomial p = new Polynomial(new TreeMap<Integer, Double>() {{
        put(5, 4.0);
        put(4, 0.0);
        put(3, -3.0);
        put(2, 0.0);
        put(1, 2.0);
        put(0, 1.0);
    }});
    Polynomial result = p.differentiate();
    Polynomial expected = new Polynomial(new TreeMap<Integer, Double>() {{
        put(4, 20.0);
        put(2, -9.0);
        put(0, 2.0);
    }});
    assertEquals(expected.toString(), result.toString());
}
@Test
    public void testRegex() {
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Double>() {{
        put(3, 4.0);
        put(2, 3.0);
        put(1, 2.0);
        put(0, 1.0);
        }});
        String result=p2.toString();
        String resultExpected="1 + 2x + 3x^2 + 4x^3";
        assertEquals(result,resultExpected);
    }


}