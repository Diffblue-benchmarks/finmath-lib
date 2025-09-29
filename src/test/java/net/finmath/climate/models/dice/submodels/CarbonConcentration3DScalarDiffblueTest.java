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

public class CarbonConcentration3DScalarDiffblueTest {
  /**
   * Test {@link CarbonConcentration3DScalar#CarbonConcentration3DScalar()}.
   *
   * <p>Method under test: {@link CarbonConcentration3DScalar#CarbonConcentration3DScalar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CarbonConcentration3DScalar.<init>()"})
  public void testNewCarbonConcentration3DScalar() {
    // Arrange and Act
    CarbonConcentration3DScalar actualCarbonConcentration3DScalar =
        new CarbonConcentration3DScalar();

    // Assert
    assertTrue(
        actualCarbonConcentration3DScalar.getCarbonConcentrationInAtmosphere() instanceof Scalar);
    assertEquals(
        1740.0d, actualCarbonConcentration3DScalar.getCarbonConcentrationInLowerOcean(), 0.0);
    assertEquals(
        460.0d, actualCarbonConcentration3DScalar.getCarbonConcentrationInShallowOcean(), 0.0);
    assertEquals(
        851.0d,
        actualCarbonConcentration3DScalar
            .getExpectedCarbonConcentrationInAtmosphere()
            .doubleValue(),
        0.0);
    assertArrayEquals(
        new double[] {851.0d, 460.0d, 1740.0d},
        actualCarbonConcentration3DScalar.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link CarbonConcentration3DScalar#CarbonConcentration3DScalar(double, double, double)}.
   *
   * <p>Method under test: {@link CarbonConcentration3DScalar#CarbonConcentration3DScalar(double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CarbonConcentration3DScalar.<init>(double, double, double)"})
  public void testNewCarbonConcentration3DScalar2() {
    // Arrange and Act
    CarbonConcentration3DScalar actualCarbonConcentration3DScalar =
        new CarbonConcentration3DScalar(10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(
        actualCarbonConcentration3DScalar.getCarbonConcentrationInAtmosphere() instanceof Scalar);
    assertEquals(
        10.0d,
        actualCarbonConcentration3DScalar
            .getExpectedCarbonConcentrationInAtmosphere()
            .doubleValue(),
        0.0);
    assertEquals(
        10.0d, actualCarbonConcentration3DScalar.getCarbonConcentrationInLowerOcean(), 0.0);
    assertEquals(
        10.0d, actualCarbonConcentration3DScalar.getCarbonConcentrationInShallowOcean(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCarbonConcentration3DScalar.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link CarbonConcentration3DScalar#CarbonConcentration3DScalar(double[])}.
   *
   * <p>Method under test: {@link CarbonConcentration3DScalar#CarbonConcentration3DScalar(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CarbonConcentration3DScalar.<init>(double[])"})
  public void testNewCarbonConcentration3DScalar3() {
    // Arrange and Act
    CarbonConcentration3DScalar actualCarbonConcentration3DScalar =
        new CarbonConcentration3DScalar(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertTrue(
        actualCarbonConcentration3DScalar.getCarbonConcentrationInAtmosphere() instanceof Scalar);
    assertEquals(
        1.0d, actualCarbonConcentration3DScalar.getCarbonConcentrationInShallowOcean(), 0.0);
    assertEquals(
        10.0d,
        actualCarbonConcentration3DScalar
            .getExpectedCarbonConcentrationInAtmosphere()
            .doubleValue(),
        0.0);
    assertEquals(
        10.0d, actualCarbonConcentration3DScalar.getCarbonConcentrationInLowerOcean(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d},
        actualCarbonConcentration3DScalar.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link CarbonConcentration3DScalar#getExpectedCarbonConcentrationInAtmosphere()}.
   *
   * <p>Method under test: {@link
   * CarbonConcentration3DScalar#getExpectedCarbonConcentrationInAtmosphere()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Double CarbonConcentration3DScalar.getExpectedCarbonConcentrationInAtmosphere()"
  })
  public void testGetExpectedCarbonConcentrationInAtmosphere() {
    // Arrange, Act and Assert
    assertEquals(
        851.0d,
        new CarbonConcentration3DScalar()
            .getExpectedCarbonConcentrationInAtmosphere()
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link CarbonConcentration3DScalar#getCarbonConcentrationInAtmosphere()}.
   *
   * <p>Method under test: {@link CarbonConcentration3DScalar#getCarbonConcentrationInAtmosphere()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CarbonConcentration3DScalar.getCarbonConcentrationInAtmosphere()"
  })
  public void testGetCarbonConcentrationInAtmosphere() {
    // Arrange and Act
    RandomVariable actualCarbonConcentrationInAtmosphere =
        new CarbonConcentration3DScalar().getCarbonConcentrationInAtmosphere();

    // Assert
    assertTrue(actualCarbonConcentrationInAtmosphere instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.abs() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.cos() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.exp() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.expm1() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.invert() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.isNaN() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.sin() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.sqrt() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.squared() instanceof Scalar);
    assertTrue(actualCarbonConcentrationInAtmosphere.variance() instanceof Scalar);
    assertNull(actualCarbonConcentrationInAtmosphere.getRealizations());
    assertNull(actualCarbonConcentrationInAtmosphere.getOperator());
    assertNull(actualCarbonConcentrationInAtmosphere.getRealizationsStream());
    assertEquals(0, actualCarbonConcentrationInAtmosphere.getTypePriority());
    assertEquals(0.0d, actualCarbonConcentrationInAtmosphere.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCarbonConcentrationInAtmosphere.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCarbonConcentrationInAtmosphere.getStandardError(), 0.0);
    assertEquals(0.0d, actualCarbonConcentrationInAtmosphere.getVariance(), 0.0);
    assertEquals(1, actualCarbonConcentrationInAtmosphere.size());
    assertEquals(851.0d, actualCarbonConcentrationInAtmosphere.getAverage(), 0.0);
    assertEquals(851.0d, actualCarbonConcentrationInAtmosphere.getMax(), 0.0);
    assertEquals(851.0d, actualCarbonConcentrationInAtmosphere.getMin(), 0.0);
    assertTrue(actualCarbonConcentrationInAtmosphere.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualCarbonConcentrationInAtmosphere.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCarbonConcentrationInAtmosphere.expectation();
    assertSame(actualCarbonConcentrationInAtmosphere, actualExpectationResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CarbonConcentration3DScalar#getCarbonConcentrationInLowerOcean()}
   *   <li>{@link CarbonConcentration3DScalar#getCarbonConcentrationInShallowOcean()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CarbonConcentration3DScalar.getCarbonConcentrationInLowerOcean()",
    "double CarbonConcentration3DScalar.getCarbonConcentrationInShallowOcean()"
  })
  public void testGettersAndSetters() {
    // Arrange
    CarbonConcentration3DScalar carbonConcentration3DScalar = new CarbonConcentration3DScalar();

    // Act
    double actualCarbonConcentrationInLowerOcean =
        carbonConcentration3DScalar.getCarbonConcentrationInLowerOcean();

    // Assert
    assertEquals(1740.0d, actualCarbonConcentrationInLowerOcean, 0.0);
    assertEquals(460.0d, carbonConcentration3DScalar.getCarbonConcentrationInShallowOcean(), 0.0);
  }

  /**
   * Test {@link CarbonConcentration3DScalar#getAsDoubleArray()}.
   *
   * <p>Method under test: {@link CarbonConcentration3DScalar#getAsDoubleArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] CarbonConcentration3DScalar.getAsDoubleArray()"})
  public void testGetAsDoubleArray() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {851.0d, 460.0d, 1740.0d},
        new CarbonConcentration3DScalar().getAsDoubleArray(),
        0.0);
  }
}
