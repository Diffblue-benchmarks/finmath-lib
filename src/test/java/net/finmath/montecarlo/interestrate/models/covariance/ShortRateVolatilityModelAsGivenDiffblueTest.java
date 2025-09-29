package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ShortRateVolatilityModelAsGivenDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       ShortRateVolatilityModelAsGiven#ShortRateVolatilityModelAsGiven(TimeDiscretization,
   *       double[], double[])}
   *   <li>{@link ShortRateVolatilityModelAsGiven#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelAsGiven.<init>(TimeDiscretization, double[], double[])",
    "TimeDiscretization ShortRateVolatilityModelAsGiven.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    ShortRateVolatilityModelAsGiven actualShortRateVolatilityModelAsGiven =
        new ShortRateVolatilityModelAsGiven(
            timeDiscretization,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertSame(timeDiscretization, actualShortRateVolatilityModelAsGiven.getTimeDiscretization());
  }

  /**
   * Test {@link ShortRateVolatilityModelAsGiven#getVolatility(int)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ShortRateVolatilityModelAsGiven#getVolatility(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable ShortRateVolatilityModelAsGiven.getVolatility(int)"})
  public void testGetVolatility_thenReturnScalar() {
    // Arrange
    ShortRateVolatilityModelAsGiven shortRateVolatilityModelAsGiven =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable actualVolatility = shortRateVolatilityModelAsGiven.getVolatility(1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertTrue(actualVolatility.abs() instanceof Scalar);
    assertTrue(actualVolatility.cos() instanceof Scalar);
    assertTrue(actualVolatility.exp() instanceof Scalar);
    assertTrue(actualVolatility.expm1() instanceof Scalar);
    assertTrue(actualVolatility.invert() instanceof Scalar);
    assertTrue(actualVolatility.isNaN() instanceof Scalar);
    assertTrue(actualVolatility.sin() instanceof Scalar);
    assertTrue(actualVolatility.sqrt() instanceof Scalar);
    assertTrue(actualVolatility.squared() instanceof Scalar);
    assertTrue(actualVolatility.variance() instanceof Scalar);
    assertNull(actualVolatility.getRealizations());
    assertNull(actualVolatility.getOperator());
    assertNull(actualVolatility.getRealizationsStream());
    assertEquals(0, actualVolatility.getTypePriority());
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(0.5d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.5d, actualVolatility.getMax(), 0.0);
    assertEquals(0.5d, actualVolatility.getMin(), 0.0);
    assertEquals(1, actualVolatility.size());
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link ShortRateVolatilityModelAsGiven#getMeanReversion(int)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ShortRateVolatilityModelAsGiven#getMeanReversion(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable ShortRateVolatilityModelAsGiven.getMeanReversion(int)"})
  public void testGetMeanReversion_thenReturnScalar() {
    // Arrange
    ShortRateVolatilityModelAsGiven shortRateVolatilityModelAsGiven =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable actualMeanReversion = shortRateVolatilityModelAsGiven.getMeanReversion(1);

    // Assert
    assertTrue(actualMeanReversion instanceof Scalar);
    assertTrue(actualMeanReversion.abs() instanceof Scalar);
    assertTrue(actualMeanReversion.cos() instanceof Scalar);
    assertTrue(actualMeanReversion.exp() instanceof Scalar);
    assertTrue(actualMeanReversion.expm1() instanceof Scalar);
    assertTrue(actualMeanReversion.invert() instanceof Scalar);
    assertTrue(actualMeanReversion.isNaN() instanceof Scalar);
    assertTrue(actualMeanReversion.sin() instanceof Scalar);
    assertTrue(actualMeanReversion.sqrt() instanceof Scalar);
    assertTrue(actualMeanReversion.squared() instanceof Scalar);
    assertTrue(actualMeanReversion.variance() instanceof Scalar);
    assertNull(actualMeanReversion.getRealizations());
    assertNull(actualMeanReversion.getOperator());
    assertNull(actualMeanReversion.getRealizationsStream());
    assertEquals(0, actualMeanReversion.getTypePriority());
    assertEquals(0.0d, actualMeanReversion.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getStandardError(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getVariance(), 0.0);
    assertEquals(0.5d, actualMeanReversion.getAverage(), 0.0);
    assertEquals(0.5d, actualMeanReversion.getMax(), 0.0);
    assertEquals(0.5d, actualMeanReversion.getMin(), 0.0);
    assertEquals(1, actualMeanReversion.size());
    assertTrue(actualMeanReversion.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMeanReversion.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualMeanReversion.expectation();
    assertSame(actualMeanReversion, actualExpectationResult);
  }
}
