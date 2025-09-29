package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.LIBORMarketModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwaprateCovarianceAnalyticApproximationDiffblueTest {
  /**
   * Test {@link
   * SwaprateCovarianceAnalyticApproximation#SwaprateCovarianceAnalyticApproximation(double[],
   * double[])}.
   *
   * <p>Method under test: {@link
   * SwaprateCovarianceAnalyticApproximation#SwaprateCovarianceAnalyticApproximation(double[],
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwaprateCovarianceAnalyticApproximation.<init>(double[], double[])"})
  public void testNewSwaprateCovarianceAnalyticApproximation() {
    // Arrange and Act
    SwaprateCovarianceAnalyticApproximation actualSwaprateCovarianceAnalyticApproximation =
        new SwaprateCovarianceAnalyticApproximation(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertNull(actualSwaprateCovarianceAnalyticApproximation.getCurrency());
  }

  /**
   * Test {@link SwaprateCovarianceAnalyticApproximation#getValue(double,
   * MonteCarloSimulationModel)} with {@code double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaprateCovarianceAnalyticApproximation#getValue(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaprateCovarianceAnalyticApproximation.getValue(double, MonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleMonteCarloSimulationModel() throws CalculationException {
    // Arrange
    SwaprateCovarianceAnalyticApproximation swaprateCovarianceAnalyticApproximation =
        new SwaprateCovarianceAnalyticApproximation(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            swaprateCovarianceAnalyticApproximation.getValue(
                10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link SwaprateCovarianceAnalyticApproximation#getValues(double, TimeDiscretization,
   * LIBORMarketModel)} with {@code double}, {@code TimeDiscretization}, {@code LIBORMarketModel}.
   *
   * <p>Method under test: {@link SwaprateCovarianceAnalyticApproximation#getValues(double,
   * TimeDiscretization, LIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaprateCovarianceAnalyticApproximation.getValues(double, TimeDiscretization, LIBORMarketModel)"
  })
  public void testGetValuesWithDoubleTimeDiscretizationLIBORMarketModel()
      throws CalculationException {
    // Arrange
    SwaprateCovarianceAnalyticApproximation swaprateCovarianceAnalyticApproximation =
        new SwaprateCovarianceAnalyticApproximation(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(Double.NaN, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    RandomVariable actualValues =
        swaprateCovarianceAnalyticApproximation.getValues(10.0d, timeDiscretization, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValues instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValues.getAverage(), 0.0);
    assertEquals(0.0d, actualValues.getMax(), 0.0);
    assertEquals(0.0d, actualValues.getMin(), 0.0);
    assertEquals(0.0d, actualValues.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValues.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValues.getStandardError(), 0.0);
    assertEquals(0.0d, actualValues.getVariance(), 0.0);
    assertEquals(1, actualValues.getTypePriority());
    assertEquals(1, actualValues.size());
    assertEquals(10.0d, actualValues.getFiltrationTime(), 0.0);
    assertTrue(actualValues.isDeterministic());
    assertArrayEquals(new double[] {0.0d}, actualValues.getRealizations(), 0.0);
  }
}
