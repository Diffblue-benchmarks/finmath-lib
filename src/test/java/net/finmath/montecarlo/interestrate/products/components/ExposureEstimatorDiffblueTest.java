package net.finmath.montecarlo.interestrate.products.components;

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
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.AbstractTermStructureMonteCarloProduct;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.montecarlo.interestrate.products.MoneyMarketAccount;
import net.finmath.montecarlo.interestrate.products.indices.AnalyticModelIndex;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExposureEstimatorDiffblueTest {
  /**
   * Test {@link ExposureEstimator#ExposureEstimator(AbstractTermStructureMonteCarloProduct)}.
   *
   * <p>Method under test: {@link
   * ExposureEstimator#ExposureEstimator(AbstractTermStructureMonteCarloProduct)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExposureEstimator.<init>(AbstractTermStructureMonteCarloProduct)"})
  public void testNewExposureEstimator() {
    // Arrange, Act and Assert
    assertNull(
        new ExposureEstimator(new ForwardRateVolatilitySurfaceCurvature(10.0d)).getCurrency());
  }

  /**
   * Test {@link ExposureEstimator#getCurrency()}.
   *
   * <ul>
   *   <li>Given {@link ExposureEstimator#ExposureEstimator(AbstractTermStructureMonteCarloProduct)}
   *       with underlying is {@link
   *       ExposureEstimator#ExposureEstimator(AbstractTermStructureMonteCarloProduct)}.
   * </ul>
   *
   * <p>Method under test: {@link ExposureEstimator#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ExposureEstimator.getCurrency()"})
  public void testGetCurrency_givenExposureEstimatorWithUnderlyingIsExposureEstimator() {
    // Arrange
    ExposureEstimator underlying =
        new ExposureEstimator(new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Act and Assert
    assertNull(new ExposureEstimator(underlying).getCurrency());
  }

  /**
   * Test {@link ExposureEstimator#getCurrency()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ExposureEstimator#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ExposureEstimator.getCurrency()"})
  public void testGetCurrency_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new ExposureEstimator(new ForwardRateVolatilitySurfaceCurvature(10.0d)).getCurrency());
  }

  /**
   * Test {@link ExposureEstimator#queryUnderlyings()}.
   *
   * <p>Method under test: {@link ExposureEstimator#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set ExposureEstimator.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    AnalyticModelIndex pastFixings =
        new AnalyticModelIndex(
            "Underlying cannot be queried for underlyings.",
            "Underlying cannot be queried for underlyings.",
            10.0d);
    AccrualAccount underlying =
        new AccrualAccount("GBP", pastFixings, new FixedCoupon(10.0d), 10.0d);

    // Act and Assert
    assertNull(new ExposureEstimator(new ExposureEstimator(underlying)).queryUnderlyings());
  }

  /**
   * Test {@link ExposureEstimator#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link ExposureEstimator#ExposureEstimator(AbstractTermStructureMonteCarloProduct)}
   *       with underlying is {@link Numeraire} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ExposureEstimator#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set ExposureEstimator.queryUnderlyings()"})
  public void testQueryUnderlyings_givenExposureEstimatorWithUnderlyingIsNumeraire() {
    // Arrange, Act and Assert
    assertNull(new ExposureEstimator(new Numeraire()).queryUnderlyings());
  }

  /**
   * Test {@link ExposureEstimator#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ExposureEstimator#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set ExposureEstimator.queryUnderlyings()"})
  public void testQueryUnderlyings_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ExposureEstimator(new ForwardRateVolatilitySurfaceCurvature(10.0d))
                .queryUnderlyings());
  }

  /**
   * Test {@link ExposureEstimator#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ExposureEstimator#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set ExposureEstimator.queryUnderlyings()"})
  public void testQueryUnderlyings_thenThrowIllegalArgumentException2() {
    // Arrange
    ExposureEstimator underlying =
        new ExposureEstimator(new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new ExposureEstimator(underlying).queryUnderlyings());
  }

  /**
   * Test {@link ExposureEstimator#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ExposureEstimator#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ExposureEstimator.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    ExposureEstimator exposureEstimator = new ExposureEstimator(new MoneyMarketAccount());

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = exposureEstimator.getValue(10.0d, model);

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
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.MAX_VALUE, actualValue.getAverage(), 0.0);
    assertEquals(Double.MAX_VALUE, actualValue.getMax(), 0.0);
    assertEquals(Double.MAX_VALUE, actualValue.getMin(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {Double.MAX_VALUE}, actualValue.getRealizations(), 0.0);
  }
}
