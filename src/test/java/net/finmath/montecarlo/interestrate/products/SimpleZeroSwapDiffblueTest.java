package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.indices.AbstractIndex;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleZeroSwapDiffblueTest {
  /**
   * Test {@link SimpleZeroSwap#SimpleZeroSwap(double[], double[], double[])}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleZeroSwap#SimpleZeroSwap(double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleZeroSwap.<init>(double[], double[], double[])",
    "void SimpleZeroSwap.<init>(double[], double[], double[], AbstractIndex, boolean)",
    "void SimpleZeroSwap.<init>(double[], double[], double[], boolean)"
  })
  public void testNewSimpleZeroSwap_whenArrayOfDoubleWithTenAnd05() {
    // Arrange and Act
    SimpleZeroSwap actualSimpleZeroSwap =
        new SimpleZeroSwap(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertNull(actualSimpleZeroSwap.getCurrency());
  }

  /**
   * Test {@link SimpleZeroSwap#SimpleZeroSwap(double[], double[], double[], AbstractIndex,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link FixedCoupon#FixedCoupon(double)} with coupon is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpleZeroSwap#SimpleZeroSwap(double[], double[], double[],
   * AbstractIndex, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleZeroSwap.<init>(double[], double[], double[])",
    "void SimpleZeroSwap.<init>(double[], double[], double[], AbstractIndex, boolean)",
    "void SimpleZeroSwap.<init>(double[], double[], double[], boolean)"
  })
  public void testNewSimpleZeroSwap_whenFixedCouponWithCouponIsTen() {
    // Arrange and Act
    SimpleZeroSwap actualSimpleZeroSwap =
        new SimpleZeroSwap(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new FixedCoupon(10.0d),
            true);

    // Assert
    assertNull(actualSimpleZeroSwap.getCurrency());
  }

  /**
   * Test {@link SimpleZeroSwap#SimpleZeroSwap(double[], double[], double[], boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleZeroSwap#SimpleZeroSwap(double[], double[], double[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleZeroSwap.<init>(double[], double[], double[])",
    "void SimpleZeroSwap.<init>(double[], double[], double[], AbstractIndex, boolean)",
    "void SimpleZeroSwap.<init>(double[], double[], double[], boolean)"
  })
  public void testNewSimpleZeroSwap_whenTrue() {
    // Arrange and Act
    SimpleZeroSwap actualSimpleZeroSwap =
        new SimpleZeroSwap(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            true);

    // Assert
    assertNull(actualSimpleZeroSwap.getCurrency());
  }

  /**
   * Test {@link SimpleZeroSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleZeroSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleZeroSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    SimpleZeroSwap simpleZeroSwap =
        new SimpleZeroSwap(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = simpleZeroSwap.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertEquals(Double.NaN, actualValue.getAverage(), 0.0);
    assertEquals(Double.NaN, actualValue.getMax(), 0.0);
    assertEquals(Double.NaN, actualValue.getMin(), 0.0);
    assertEquals(Double.NaN, actualValue.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualValue.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualValue.getRealizations(),
        0.0);
  }
}
