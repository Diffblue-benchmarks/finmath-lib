package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class Temperature2DScalarDiffblueTest {
  /**
   * Test {@link Temperature2DScalar#Temperature2DScalar()}.
   *
   * <p>Method under test: {@link Temperature2DScalar#Temperature2DScalar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Temperature2DScalar.<init>()"})
  public void testNewTemperature2DScalar() {
    // Arrange and Act
    Temperature2DScalar actualTemperature2DScalar = new Temperature2DScalar();

    // Assert
    assertTrue(actualTemperature2DScalar.getTemperatureOfAtmosphere() instanceof Scalar);
    assertEquals(0.0068d, actualTemperature2DScalar.getTemperatureOfLandAndOcean(), 0.0);
    assertEquals(
        0.85d, actualTemperature2DScalar.getExpectedTemperatureOfAtmosphere().doubleValue(), 0.0);
    assertArrayEquals(
        new double[] {0.85d, 0.0068d}, actualTemperature2DScalar.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link Temperature2DScalar#Temperature2DScalar(double, double)}.
   *
   * <p>Method under test: {@link Temperature2DScalar#Temperature2DScalar(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Temperature2DScalar.<init>(double, double)"})
  public void testNewTemperature2DScalar2() {
    // Arrange and Act
    Temperature2DScalar actualTemperature2DScalar = new Temperature2DScalar(10.0d, 10.0d);

    // Assert
    assertTrue(actualTemperature2DScalar.getTemperatureOfAtmosphere() instanceof Scalar);
    assertEquals(
        10.0d, actualTemperature2DScalar.getExpectedTemperatureOfAtmosphere().doubleValue(), 0.0);
    assertEquals(10.0d, actualTemperature2DScalar.getTemperatureOfLandAndOcean(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualTemperature2DScalar.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link Temperature2DScalar#Temperature2DScalar(double[])}.
   *
   * <ul>
   *   <li>Then TemperatureOfAtmosphere return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Temperature2DScalar#Temperature2DScalar(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Temperature2DScalar.<init>(double[])"})
  public void testNewTemperature2DScalar_thenTemperatureOfAtmosphereReturnScalar() {
    // Arrange and Act
    Temperature2DScalar actualTemperature2DScalar =
        new Temperature2DScalar(new double[] {10.0d, 0.0068d, 10.0d, 0.0068d});

    // Assert
    assertTrue(actualTemperature2DScalar.getTemperatureOfAtmosphere() instanceof Scalar);
    assertEquals(0.0068d, actualTemperature2DScalar.getTemperatureOfLandAndOcean(), 0.0);
    assertEquals(
        10.0d, actualTemperature2DScalar.getExpectedTemperatureOfAtmosphere().doubleValue(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.0068d}, actualTemperature2DScalar.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link Temperature2DScalar#getExpectedTemperatureOfAtmosphere()}.
   *
   * <p>Method under test: {@link Temperature2DScalar#getExpectedTemperatureOfAtmosphere()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double Temperature2DScalar.getExpectedTemperatureOfAtmosphere()"})
  public void testGetExpectedTemperatureOfAtmosphere() {
    // Arrange, Act and Assert
    assertEquals(
        0.85d, new Temperature2DScalar().getExpectedTemperatureOfAtmosphere().doubleValue(), 0.0);
  }

  /**
   * Test {@link Temperature2DScalar#getTemperatureOfAtmosphere()}.
   *
   * <p>Method under test: {@link Temperature2DScalar#getTemperatureOfAtmosphere()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Temperature2DScalar.getTemperatureOfAtmosphere()"})
  public void testGetTemperatureOfAtmosphere() {
    // Arrange and Act
    RandomVariable actualTemperatureOfAtmosphere =
        new Temperature2DScalar().getTemperatureOfAtmosphere();

    // Assert
    assertTrue(actualTemperatureOfAtmosphere instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.abs() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.cos() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.exp() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.expm1() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.invert() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.isNaN() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.sin() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.sqrt() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.squared() instanceof Scalar);
    assertTrue(actualTemperatureOfAtmosphere.variance() instanceof Scalar);
    assertNull(actualTemperatureOfAtmosphere.getRealizations());
    assertNull(actualTemperatureOfAtmosphere.getOperator());
    assertNull(actualTemperatureOfAtmosphere.getRealizationsStream());
    assertEquals(0, actualTemperatureOfAtmosphere.getTypePriority());
    assertEquals(0.0d, actualTemperatureOfAtmosphere.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualTemperatureOfAtmosphere.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualTemperatureOfAtmosphere.getStandardError(), 0.0);
    assertEquals(0.0d, actualTemperatureOfAtmosphere.getVariance(), 0.0);
    assertEquals(0.85d, actualTemperatureOfAtmosphere.getAverage(), 0.0);
    assertEquals(0.85d, actualTemperatureOfAtmosphere.getMax(), 0.0);
    assertEquals(0.85d, actualTemperatureOfAtmosphere.getMin(), 0.0);
    assertEquals(1, actualTemperatureOfAtmosphere.size());
    assertTrue(actualTemperatureOfAtmosphere.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualTemperatureOfAtmosphere.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualTemperatureOfAtmosphere.expectation();
    assertSame(actualTemperatureOfAtmosphere, actualExpectationResult);
  }

  /**
   * Test {@link Temperature2DScalar#getTemperatureOfLandAndOcean()}.
   *
   * <p>Method under test: {@link Temperature2DScalar#getTemperatureOfLandAndOcean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Temperature2DScalar.getTemperatureOfLandAndOcean()"})
  public void testGetTemperatureOfLandAndOcean() {
    // Arrange, Act and Assert
    assertEquals(0.0068d, new Temperature2DScalar().getTemperatureOfLandAndOcean(), 0.0);
  }

  /**
   * Test {@link Temperature2DScalar#getAsDoubleArray()}.
   *
   * <p>Method under test: {@link Temperature2DScalar#getAsDoubleArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] Temperature2DScalar.getAsDoubleArray()"})
  public void testGetAsDoubleArray() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.85d, 0.0068d}, new Temperature2DScalar().getAsDoubleArray(), 0.0);
  }
}
