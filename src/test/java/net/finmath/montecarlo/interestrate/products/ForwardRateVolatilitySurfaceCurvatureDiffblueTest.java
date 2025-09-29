package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
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
import java.util.HashMap;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.CalibrationProduct;
import net.finmath.montecarlo.interestrate.LIBORMarketModel;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelBH;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm5Param;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardRateVolatilitySurfaceCurvatureDiffblueTest {
  /**
   * Test {@link ForwardRateVolatilitySurfaceCurvature#ForwardRateVolatilitySurfaceCurvature()}.
   *
   * <p>Method under test: {@link
   * ForwardRateVolatilitySurfaceCurvature#ForwardRateVolatilitySurfaceCurvature()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardRateVolatilitySurfaceCurvature.<init>()",
    "void ForwardRateVolatilitySurfaceCurvature.<init>(double)"
  })
  public void testNewForwardRateVolatilitySurfaceCurvature() {
    // Arrange, Act and Assert
    assertNull(new ForwardRateVolatilitySurfaceCurvature().getCurrency());
  }

  /**
   * Test {@link
   * ForwardRateVolatilitySurfaceCurvature#ForwardRateVolatilitySurfaceCurvature(double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * ForwardRateVolatilitySurfaceCurvature#ForwardRateVolatilitySurfaceCurvature(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardRateVolatilitySurfaceCurvature.<init>()",
    "void ForwardRateVolatilitySurfaceCurvature.<init>(double)"
  })
  public void testNewForwardRateVolatilitySurfaceCurvature_whenTen() {
    // Arrange, Act and Assert
    assertNull(new ForwardRateVolatilitySurfaceCurvature(10.0d).getCurrency());
  }

  /**
   * Test {@link ForwardRateVolatilitySurfaceCurvature#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ForwardRateVolatilitySurfaceCurvature#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateVolatilitySurfaceCurvature.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            forwardRateVolatilitySurfaceCurvature.getValue(
                10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link ForwardRateVolatilitySurfaceCurvature#getValues(double, LIBORMarketModel)} with
   * {@code double}, {@code LIBORMarketModel}.
   *
   * <p>Method under test: {@link ForwardRateVolatilitySurfaceCurvature#getValues(double,
   * LIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateVolatilitySurfaceCurvature.getValues(double, LIBORMarketModel)"
  })
  public void testGetValuesWithDoubleLIBORMarketModel() throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    LIBORMarketModelFromCovarianceModel model =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act
    RandomVariable actualValues = forwardRateVolatilitySurfaceCurvature.getValues(0.0d, model);

    // Assert
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
    assertArrayEquals(new double[] {0.0d}, actualValues.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateVolatilitySurfaceCurvature#getValues(double, LIBORMarketModel)} with
   * {@code double}, {@code LIBORMarketModel}.
   *
   * <p>Method under test: {@link ForwardRateVolatilitySurfaceCurvature#getValues(double,
   * LIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateVolatilitySurfaceCurvature.getValues(double, LIBORMarketModel)"
  })
  public void testGetValuesWithDoubleLIBORMarketModel2() throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            0.5d,
            ShortPeriodLocation.SHORT_PERIOD_AT_START);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    LIBORMarketModelFromCovarianceModel model =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act
    RandomVariable actualValues = forwardRateVolatilitySurfaceCurvature.getValues(0.0d, model);

    // Assert
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
    assertArrayEquals(new double[] {0.0d}, actualValues.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateVolatilitySurfaceCurvature#getValues(double, LIBORMarketModel)} with
   * {@code double}, {@code LIBORMarketModel}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateVolatilitySurfaceCurvature#getValues(double,
   * LIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateVolatilitySurfaceCurvature.getValues(double, LIBORMarketModel)"
  })
  public void testGetValuesWithDoubleLIBORMarketModel_whenTen_thenThrowRuntimeException()
      throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    LIBORMarketModelFromCovarianceModel model =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> forwardRateVolatilitySurfaceCurvature.getValues(10.0d, model));
  }
}
