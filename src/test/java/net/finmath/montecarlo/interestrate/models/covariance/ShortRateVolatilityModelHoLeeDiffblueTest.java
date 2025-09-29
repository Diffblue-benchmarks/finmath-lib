package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ShortRateVolatilityModelHoLeeDiffblueTest {
  /**
   * Test {@link ShortRateVolatilityModelHoLee#ShortRateVolatilityModelHoLee(double)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelHoLee#ShortRateVolatilityModelHoLee(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ShortRateVolatilityModelHoLee.<init>(double)"})
  public void testNewShortRateVolatilityModelHoLee() {
    // Arrange, Act and Assert
    TimeDiscretization timeDiscretization =
        new ShortRateVolatilityModelHoLee(10.0d).getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, timeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = timeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = timeDiscretization.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, timeDiscretization.getFirstTime(), 0.0);
    assertEquals(0.0d, timeDiscretization.getLastTime(), 0.0);
    assertEquals(1, timeDiscretization.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, timeDiscretization.getTickSize(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link ShortRateVolatilityModelHoLee#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link ShortRateVolatilityModelHoLee#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization ShortRateVolatilityModelHoLee.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange, Act and Assert
    assertTrue(
        new ShortRateVolatilityModelHoLee(10.0d).getTimeDiscretization()
            instanceof TimeDiscretizationFromArray);
  }

  /**
   * Test {@link ShortRateVolatilityModelHoLee#getVolatility(int)}.
   *
   * <p>Method under test: {@link ShortRateVolatilityModelHoLee#getVolatility(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable ShortRateVolatilityModelHoLee.getVolatility(int)"})
  public void testGetVolatility() {
    // Arrange and Act
    RandomVariable actualVolatility = new ShortRateVolatilityModelHoLee(10.0d).getVolatility(1);

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
    assertEquals(1, actualVolatility.size());
    assertEquals(10.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.0d, actualVolatility.getMax(), 0.0);
    assertEquals(10.0d, actualVolatility.getMin(), 0.0);
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link ShortRateVolatilityModelHoLee#getMeanReversion(int)}.
   *
   * <p>Method under test: {@link ShortRateVolatilityModelHoLee#getMeanReversion(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable ShortRateVolatilityModelHoLee.getMeanReversion(int)"})
  public void testGetMeanReversion() {
    // Arrange and Act
    RandomVariable actualMeanReversion =
        new ShortRateVolatilityModelHoLee(10.0d).getMeanReversion(1);

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
    assertEquals(0.0d, actualMeanReversion.getAverage(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getMax(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getMin(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getStandardError(), 0.0);
    assertEquals(0.0d, actualMeanReversion.getVariance(), 0.0);
    assertEquals(1, actualMeanReversion.size());
    assertTrue(actualMeanReversion.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMeanReversion.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualMeanReversion.expectation();
    assertSame(actualMeanReversion, actualExpectationResult);
  }
}
