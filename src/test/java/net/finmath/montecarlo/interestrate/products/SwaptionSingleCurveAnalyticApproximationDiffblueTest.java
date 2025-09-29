package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
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
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
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

public class SwaptionSingleCurveAnalyticApproximationDiffblueTest {
  /**
   * Test {@link
   * SwaptionSingleCurveAnalyticApproximation#SwaptionSingleCurveAnalyticApproximation(double,
   * TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * SwaptionSingleCurveAnalyticApproximation#SwaptionSingleCurveAnalyticApproximation(double,
   * TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionSingleCurveAnalyticApproximation.<init>(double, TimeDiscretization)"
  })
  public void testNewSwaptionSingleCurveAnalyticApproximation() {
    // Arrange and Act
    SwaptionSingleCurveAnalyticApproximation actualSwaptionSingleCurveAnalyticApproximation =
        new SwaptionSingleCurveAnalyticApproximation(10.0d, new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertNull(actualSwaptionSingleCurveAnalyticApproximation.getCurrency());
  }

  /**
   * Test {@link
   * SwaptionSingleCurveAnalyticApproximation#SwaptionSingleCurveAnalyticApproximation(double,
   * double[], ValueUnit)}.
   *
   * <p>Method under test: {@link
   * SwaptionSingleCurveAnalyticApproximation#SwaptionSingleCurveAnalyticApproximation(double,
   * double[], Swaption.ValueUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionSingleCurveAnalyticApproximation.<init>(double, double[], Swaption.ValueUnit)"
  })
  public void testNewSwaptionSingleCurveAnalyticApproximation2() {
    // Arrange and Act
    SwaptionSingleCurveAnalyticApproximation actualSwaptionSingleCurveAnalyticApproximation =
        new SwaptionSingleCurveAnalyticApproximation(
            10.0d, new double[] {10.0d, 1.0d, 10.0d, 1.0d}, ValueUnit.VALUE);

    // Assert
    assertNull(actualSwaptionSingleCurveAnalyticApproximation.getCurrency());
  }

  /**
   * Test {@link SwaptionSingleCurveAnalyticApproximation#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionSingleCurveAnalyticApproximation#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable SwaptionSingleCurveAnalyticApproximation.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel() {
    // Arrange
    SwaptionSingleCurveAnalyticApproximation swaptionSingleCurveAnalyticApproximation =
        new SwaptionSingleCurveAnalyticApproximation(10.0d, new TenorFromArray(10.0d, 10, 0.5d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            swaptionSingleCurveAnalyticApproximation.getValue(
                10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link SwaptionSingleCurveAnalyticApproximation#getValues(double, TimeDiscretization,
   * LIBORMarketModel)} with {@code double}, {@code TimeDiscretization}, {@code LIBORMarketModel}.
   *
   * <p>Method under test: {@link SwaptionSingleCurveAnalyticApproximation#getValues(double,
   * TimeDiscretization, LIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable SwaptionSingleCurveAnalyticApproximation.getValues(double, TimeDiscretization, LIBORMarketModel)"
  })
  public void testGetValuesWithDoubleTimeDiscretizationLIBORMarketModel()
      throws CalculationException {
    // Arrange
    SwaptionSingleCurveAnalyticApproximation swaptionSingleCurveAnalyticApproximation =
        new SwaptionSingleCurveAnalyticApproximation(10.0d, new TenorFromArray(10.0d, 10, 0.5d));
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
        () -> swaptionSingleCurveAnalyticApproximation.getValues(10.0d, timeDiscretization, model));
  }

  /**
   * Test {@link
   * SwaptionSingleCurveAnalyticApproximation#getLogSwaprateDerivative(TimeDiscretization,
   * ForwardCurve, double[])}.
   *
   * <p>Method under test: {@link
   * SwaptionSingleCurveAnalyticApproximation#getLogSwaprateDerivative(TimeDiscretization,
   * ForwardCurve, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SwaptionSingleCurveAnalyticApproximation.getLogSwaprateDerivative(TimeDiscretization, ForwardCurve, double[])"
  })
  public void testGetLogSwaprateDerivative() {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 365.0d, 1.0d, 365.0d},
            new double[] {1.0d, 365.0d, 1.0d, 365.0d},
            1.0d);

    // Act
    Map<String, double[]> actualLogSwaprateDerivative =
        SwaptionSingleCurveAnalyticApproximation.getLogSwaprateDerivative(
            liborPeriodDiscretization,
            forwardCurve,
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d});

    // Assert
    assertEquals(3, actualLogSwaprateDerivative.size());
    assertArrayEquals(new double[] {1.0d}, actualLogSwaprateDerivative.get("values"), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0252808988764046d},
        actualLogSwaprateDerivative.get("discountFactors"),
        0.0);
    assertArrayEquals(
        new double[] {
          9.910112359550567d,
          0.6825842696629252d,
          9.682584269662925d,
          0.45505617977528345d,
          9.455056179775283d,
          0.22752808988764173d,
          9.227528089887642d
        },
        actualLogSwaprateDerivative.get("swapAnnuities"),
        0.0);
  }

  /**
   * Test {@link
   * SwaptionSingleCurveAnalyticApproximation#getLogSwaprateDerivative(TimeDiscretization,
   * ForwardCurve, double[])}.
   *
   * <p>Method under test: {@link
   * SwaptionSingleCurveAnalyticApproximation#getLogSwaprateDerivative(TimeDiscretization,
   * ForwardCurve, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SwaptionSingleCurveAnalyticApproximation.getLogSwaprateDerivative(TimeDiscretization, ForwardCurve, double[])"
  })
  public void testGetLogSwaprateDerivative2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 365.0d, 1.0d, 365.0d},
            new double[] {1.0d, 365.0d, 1.0d, 365.0d},
            1.0d);

    // Act
    Map<String, double[]> actualLogSwaprateDerivative =
        SwaptionSingleCurveAnalyticApproximation.getLogSwaprateDerivative(
            liborPeriodDiscretization,
            forwardCurve,
            new double[] {1.0d, 1.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d});

    // Assert
    assertEquals(3, actualLogSwaprateDerivative.size());
    assertArrayEquals(new double[] {1.0d}, actualLogSwaprateDerivative.get("values"), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0252808988764046d},
        actualLogSwaprateDerivative.get("discountFactors"),
        0.0);
    assertArrayEquals(
        new double[] {
          9.682584269662925d,
          9.682584269662925d,
          9.682584269662925d,
          0.45505617977528345d,
          9.455056179775283d,
          0.22752808988764173d,
          9.227528089887642d
        },
        actualLogSwaprateDerivative.get("swapAnnuities"),
        0.0);
  }

  /**
   * Test {@link
   * SwaptionSingleCurveAnalyticApproximation#getLogSwaprateDerivative(TimeDiscretization,
   * ForwardCurve, double[])}.
   *
   * <ul>
   *   <li>Then return {@code values} is array of {@code double} with {@code 0.999999999999998}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SwaptionSingleCurveAnalyticApproximation#getLogSwaprateDerivative(TimeDiscretization,
   * ForwardCurve, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SwaptionSingleCurveAnalyticApproximation.getLogSwaprateDerivative(TimeDiscretization, ForwardCurve, double[])"
  })
  public void testGetLogSwaprateDerivative_thenReturnValuesIsArrayOfDoubleWith0999999999999998() {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {0.5d, 10.0d, 1.0d, 10.0d});
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 365.0d, 1.0d, 365.0d},
            new double[] {1.0d, 365.0d, 1.0d, 365.0d},
            1.0d);

    // Act
    Map<String, double[]> actualLogSwaprateDerivative =
        SwaptionSingleCurveAnalyticApproximation.getLogSwaprateDerivative(
            liborPeriodDiscretization,
            forwardCurve,
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d});

    // Assert
    assertEquals(3, actualLogSwaprateDerivative.size());
    assertArrayEquals(
        new double[] {0.999999999999998d}, actualLogSwaprateDerivative.get("values"), 0.0);
    assertArrayEquals(
        new double[] {1.0006854009595614d, 1.0259836273883143d},
        actualLogSwaprateDerivative.get("discountFactors"),
        0.0);
    assertArrayEquals(
        new double[] {
          9.916904760071155d,
          0.683052113576327d,
          9.68922072221238d,
          0.45536807571755133d,
          9.461536684353604d,
          0.22768403785877567d,
          9.233852646494828d
        },
        actualLogSwaprateDerivative.get("swapAnnuities"),
        0.0);
  }
}
