package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.modelling.products.Swaption;
import net.finmath.modelling.products.Swaption.ValueUnit;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.interestrate.CalibrationProduct;
import net.finmath.montecarlo.interestrate.LIBORMarketModel;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelBH;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SwaptionAnalyticApproximationRebonatoDiffblueTest {
  /**
   * Test {@link SwaptionAnalyticApproximationRebonato#SwaptionAnalyticApproximationRebonato(double,
   * TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * SwaptionAnalyticApproximationRebonato#SwaptionAnalyticApproximationRebonato(double,
   * TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionAnalyticApproximationRebonato.<init>(double, TimeDiscretization)"
  })
  public void testNewSwaptionAnalyticApproximationRebonato() {
    // Arrange and Act
    SwaptionAnalyticApproximationRebonato actualSwaptionAnalyticApproximationRebonato =
        new SwaptionAnalyticApproximationRebonato(10.0d, new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertNull(actualSwaptionAnalyticApproximationRebonato.getCurrency());
  }

  /**
   * Test {@link SwaptionAnalyticApproximationRebonato#SwaptionAnalyticApproximationRebonato(double,
   * double[], ValueUnit)}.
   *
   * <p>Method under test: {@link
   * SwaptionAnalyticApproximationRebonato#SwaptionAnalyticApproximationRebonato(double, double[],
   * Swaption.ValueUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionAnalyticApproximationRebonato.<init>(double, double[], Swaption.ValueUnit)"
  })
  public void testNewSwaptionAnalyticApproximationRebonato2() {
    // Arrange and Act
    SwaptionAnalyticApproximationRebonato actualSwaptionAnalyticApproximationRebonato =
        new SwaptionAnalyticApproximationRebonato(
            10.0d, new double[] {10.0d, 1.0d, 10.0d, 1.0d}, ValueUnit.VALUE);

    // Assert
    assertNull(actualSwaptionAnalyticApproximationRebonato.getCurrency());
  }

  /**
   * Test {@link SwaptionAnalyticApproximationRebonato#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionAnalyticApproximationRebonato#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable SwaptionAnalyticApproximationRebonato.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel() {
    // Arrange
    SwaptionAnalyticApproximationRebonato swaptionAnalyticApproximationRebonato =
        new SwaptionAnalyticApproximationRebonato(10.0d, new TenorFromArray(10.0d, 10, 0.5d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            swaptionAnalyticApproximationRebonato.getValue(
                10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link SwaptionAnalyticApproximationRebonato#getValues(double, TimeDiscretization,
   * LIBORMarketModel)} with {@code double}, {@code TimeDiscretization}, {@code LIBORMarketModel}.
   *
   * <p>Method under test: {@link SwaptionAnalyticApproximationRebonato#getValues(double,
   * TimeDiscretization, LIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable SwaptionAnalyticApproximationRebonato.getValues(double, TimeDiscretization, LIBORMarketModel)"
  })
  public void testGetValuesWithDoubleTimeDiscretizationLIBORMarketModel()
      throws CalculationException {
    // Arrange
    SwaptionAnalyticApproximationRebonato swaptionAnalyticApproximationRebonato =
        new SwaptionAnalyticApproximationRebonato(10.0d, new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3);
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
        () -> swaptionAnalyticApproximationRebonato.getValues(10.0d, timeDiscretization, model));
  }

  /**
   * Test {@link SwaptionAnalyticApproximationRebonato#getLogSwaprateDerivative(TimeDiscretization,
   * DiscountCurve, ForwardCurve, double[])}.
   *
   * <ul>
   *   <li>Then return {@code values} is array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link
   * SwaptionAnalyticApproximationRebonato#getLogSwaprateDerivative(TimeDiscretization,
   * DiscountCurve, ForwardCurve, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SwaptionAnalyticApproximationRebonato.getLogSwaprateDerivative(TimeDiscretization, DiscountCurve, ForwardCurve, double[])"
  })
  public void testGetLogSwaprateDerivative_thenReturnValuesIsArrayOfDoubleWithOne() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(0.5d, 10, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act
    Map<String, double[]> actualLogSwaprateDerivative =
        SwaptionAnalyticApproximationRebonato.getLogSwaprateDerivative(
            liborPeriodDiscretization,
            discountCurve,
            forwardCurve,
            new double[] {0.5d, 1.0d, 1.0d, 1.0d});

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertEquals(3, actualLogSwaprateDerivative.size());
    assertArrayEquals(new double[] {1.0d}, actualLogSwaprateDerivative.get("values"), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualLogSwaprateDerivative.get("discountFactors"), 0.0);
    assertArrayEquals(
        new double[] {5.0d, 0.0d, 0.0d}, actualLogSwaprateDerivative.get("swapAnnuities"), 0.0);
  }

  /**
   * Test {@link SwaptionAnalyticApproximationRebonato#getLogSwaprateDerivative(TimeDiscretization,
   * DiscountCurve, ForwardCurve, double[])}.
   *
   * <ul>
   *   <li>Then return {@code values} is empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SwaptionAnalyticApproximationRebonato#getLogSwaprateDerivative(TimeDiscretization,
   * DiscountCurve, ForwardCurve, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SwaptionAnalyticApproximationRebonato.getLogSwaprateDerivative(TimeDiscretization, DiscountCurve, ForwardCurve, double[])"
  })
  public void testGetLogSwaprateDerivative_thenReturnValuesIsEmptyArrayOfDouble() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, -1, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    Map<String, double[]> actualLogSwaprateDerivative =
        SwaptionAnalyticApproximationRebonato.getLogSwaprateDerivative(
            liborPeriodDiscretization,
            discountCurve,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    verify(discountCurve).getDiscountFactor(isNull(), eq(10.0d));
    assertEquals(3, actualLogSwaprateDerivative.size());
    assertArrayEquals(new double[] {}, actualLogSwaprateDerivative.get("values"), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualLogSwaprateDerivative.get("discountFactors"), 0.0);
    assertArrayEquals(
        new double[] {-90.0d, 0.0d, -90.0d}, actualLogSwaprateDerivative.get("swapAnnuities"), 0.0);
  }
}
