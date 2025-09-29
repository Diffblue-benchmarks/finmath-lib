package net.finmath.montecarlo.interestrate.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.marketdata.model.volatilities.SwaptionATMMarketDataFromArray;
import net.finmath.marketdata.model.volatilities.SwaptionMarketData;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.interestrate.CalibrationProduct;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel.Driftapproximation;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel.InterpolationMethod;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel.Measure;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelBH;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm5Param;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm7Param;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LIBORMarketModelFromCovarianceModelDiffblueTest {
  @Mock private LIBORCovarianceModel lIBORCovarianceModel;

  @InjectMocks private LIBORMarketModelFromCovarianceModel lIBORMarketModelFromCovarianceModel;

  @Mock private MonteCarloProcess monteCarloProcess;

  @Mock private SwaptionMarketData swaptionMarketData;

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[],
   * Map)}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.of(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testOf() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualOfResult =
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

    // Assert
    ForwardCurve forwardRateCurve2 = actualOfResult.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 = actualOfResult.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization liborPeriodDiscretization2 = actualOfResult.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[],
   * Map)}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.of(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testOf2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualOfResult =
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

    // Assert
    ForwardCurve forwardRateCurve2 = actualOfResult.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 = actualOfResult.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization2 = actualOfResult.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(7, ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameter().length);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[],
   * Map)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>When {@code null}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.of(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testOf_givenThree_whenNull_thenReturnArrayLengthIsOne() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualOfResult =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            null,
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 = actualOfResult.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 = actualOfResult.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 = actualOfResult.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[],
   * Map)}.
   *
   * <ul>
   *   <li>Then CovarianceModel BaseCovarianceModel return {@link LIBORCovarianceModelBH}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.of(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testOf_thenCovarianceModelBaseCovarianceModelReturnLIBORCovarianceModelBH()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualOfResult =
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

    // Assert
    ForwardCurve forwardRateCurve2 = actualOfResult.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 = actualOfResult.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelBH);
    TimeDiscretization liborPeriodDiscretization2 = actualOfResult.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[],
   * Map)}.
   *
   * <ul>
   *   <li>When empty array of {@link CalibrationProduct}.
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#of(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.of(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testOf_whenEmptyArrayOfCalibrationProduct_thenReturnArrayLengthIsTwo()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualOfResult =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel4,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Assert
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getTimeDiscretization();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 = actualOfResult.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel5 = actualOfResult.getCovarianceModel();
    assertTrue(covarianceModel5 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 = actualOfResult.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel5.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel5.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationProducts =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            calibrationProducts,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelBH);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationProducts =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            calibrationProducts,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel3() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationProducts =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            calibrationProducts,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(7, ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameter().length);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel4() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationItems =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            calibrationItems,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelBH);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel3.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = covarianceModel3.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization2);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel5() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationItems =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            calibrationItems,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel3.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = covarianceModel3.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization2);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel6() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    CalibrationProduct[] calibrationItems =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            covarianceModel,
            calibrationItems,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        7, ((LIBORCovarianceModelExponentialForm7Param) covarianceModel2).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization2);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        ((LIBORCovarianceModelExponentialForm7Param) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel7() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, discountCurve, covarianceModel2);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    DiscountCurve discountCurve2 = actualLiborMarketModelFromCovarianceModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualLiborMarketModelFromCovarianceModel.getAnalyticModel());
    assertNull(actualLiborMarketModelFromCovarianceModel.getSwaptionMarketData());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertEquals(3, actualLiborMarketModelFromCovarianceModel.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        actualLiborMarketModelFromCovarianceModel.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualLiborMarketModelFromCovarianceModel.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualLiborMarketModelFromCovarianceModel.getMeasure());
    assertTrue(actualLiborMarketModelFromCovarianceModel.getNumeraireAdjustments().isEmpty());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel8() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionMarketData =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            swaptionMarketData);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 = covarianceModel3.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel3.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(1, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertEquals(1, ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameter().length);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertSame(
        liborPeriodDiscretization,
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel9() throws CalculationException {
    // Arrange
    when(swaptionMarketData.getSwapPeriodLength()).thenReturn(10.0d);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(swaptionMarketData.getOptionMaturities()).thenReturn(tenorFromArray);
    when(swaptionMarketData.getTenor()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            lIBORCovarianceModel,
            swaptionMarketData);

    // Assert
    verify(swaptionMarketData).getOptionMaturities();
    verify(swaptionMarketData).getSwapPeriodLength();
    verify(swaptionMarketData).getTenor();
    DiscountCurve discountCurve2 = actualLiborMarketModelFromCovarianceModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualLiborMarketModelFromCovarianceModel.getAnalyticModel());
    assertNull(actualLiborMarketModelFromCovarianceModel.getSwaptionMarketData());
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfFactors());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertEquals(
        Driftapproximation.EULER,
        actualLiborMarketModelFromCovarianceModel.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualLiborMarketModelFromCovarianceModel.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualLiborMarketModelFromCovarianceModel.getMeasure());
    assertTrue(actualLiborMarketModelFromCovarianceModel.getNumeraireAdjustments().isEmpty());
    assertEquals(tenorFromArray, liborPeriodDiscretization2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
    assertSame(
        lIBORCovarianceModel, actualLiborMarketModelFromCovarianceModel.getCovarianceModel());
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel10() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            (SwaptionMarketData) null,
            new HashMap<>());

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel3.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel3.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel11() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            (SwaptionMarketData) null,
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel12() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            lIBORCovarianceModel,
            (SwaptionMarketData) null,
            null);

    // Assert
    DiscountCurve discountCurve2 = actualLiborMarketModelFromCovarianceModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    assertNull(actualLiborMarketModelFromCovarianceModel.getAnalyticModel());
    assertNull(actualLiborMarketModelFromCovarianceModel.getSwaptionMarketData());
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfFactors());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertEquals(
        Driftapproximation.EULER,
        actualLiborMarketModelFromCovarianceModel.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualLiborMarketModelFromCovarianceModel.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualLiborMarketModelFromCovarianceModel.getMeasure());
    assertTrue(actualLiborMarketModelFromCovarianceModel.getNumeraireAdjustments().isEmpty());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, actualLiborMarketModelFromCovarianceModel.getForwardRateCurve());
    assertSame(
        liborPeriodDiscretization,
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization());
    assertSame(
        lIBORCovarianceModel, actualLiborMarketModelFromCovarianceModel.getCovarianceModel());
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel13() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationItems =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            calibrationItems,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelBH);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel14() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationItems =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            calibrationItems,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel15() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(100000.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(100000.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    CalibrationProduct[] calibrationItems =
        new CalibrationProduct[] {
          new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
        };

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            calibrationItems,
            new HashMap<>());

    // Assert
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertTrue(covarianceModel3.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(7, ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameter().length);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, LIBORCovarianceModel)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel16() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getDiscountCurve()
            instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualLiborMarketModelFromCovarianceModel.getAnalyticModel());
    assertNull(actualLiborMarketModelFromCovarianceModel.getSwaptionMarketData());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertEquals(3, actualLiborMarketModelFromCovarianceModel.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        actualLiborMarketModelFromCovarianceModel.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualLiborMarketModelFromCovarianceModel.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualLiborMarketModelFromCovarianceModel.getMeasure());
    assertTrue(actualLiborMarketModelFromCovarianceModel.getNumeraireAdjustments().isEmpty());
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel17() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, lIBORCovarianceModel, null);

    // Assert
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getDiscountCurve()
            instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnArrayLengthIsTwo()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel4,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Assert
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getTimeDiscretization();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel5 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel5 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel5.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel5.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnArrayLengthIsTwo2()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            covarianceModel4,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Assert
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getTimeDiscretization();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel5 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel5 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel5.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel5.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnArrayLengthIsTwo3()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel4,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Assert
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getTimeDiscretization();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel5 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel5 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel5.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel5.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel5).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnNumberOfComponentsIsOne()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionMarketData =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            swaptionMarketData,
            new HashMap<>());

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(covarianceModel3.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(1, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(1, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertSame(
        liborPeriodDiscretization,
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnNumberOfComponentsIsOne2()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionMarketData =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2, swaptionMarketData);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getDiscountCurve()
            instanceof DiscountCurveFromForwardCurve);
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(1, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnNumberOfComponentsIsTen()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getDiscountCurve()
            instanceof DiscountCurveFromForwardCurve);
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnNumberOfComponentsIsZero()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionMarketData =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            swaptionMarketData);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    Map<String, RandomVariable> modelParameters =
        actualLiborMarketModelFromCovarianceModel.getModelParameters();
    assertEquals(1, modelParameters.size());
    assertTrue(modelParameters.containsKey("COVARIANCEMODELPARAMETER(0)"));
    assertSame(
        liborPeriodDiscretization,
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnNumberOfComponentsIsZero2()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionMarketData =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            covarianceModel2,
            swaptionMarketData,
            new HashMap<>());

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    Map<String, RandomVariable> modelParameters =
        actualLiborMarketModelFromCovarianceModel.getModelParameters();
    assertEquals(1, modelParameters.size());
    assertTrue(modelParameters.containsKey("COVARIANCEMODELPARAMETER(0)"));
    assertSame(
        liborPeriodDiscretization,
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_thenReturnNumberOfComponentsIsZero3()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionMarketData =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2, swaptionMarketData);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(0, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    Map<String, RandomVariable> modelParameters =
        actualLiborMarketModelFromCovarianceModel.getModelParameters();
    assertEquals(1, modelParameters.size());
    assertTrue(modelParameters.containsKey("COVARIANCEMODELPARAMETER(0)"));
    assertSame(
        liborPeriodDiscretization,
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_whenHashMap() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel2,
            new HashMap<>());

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel3.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel3.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel3).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_whenNull() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, SwaptionMarketData)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_whenNull2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelFromCovarianceModel.getNumberOfLibors());
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, RandomVariableFactory, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_whenNull_thenReturnArrayLengthIsOne()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            null,
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_whenNull_thenReturnArrayLengthIsOne2()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            null,
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#LIBORMarketModelFromCovarianceModel(TimeDiscretization,
   * ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelFromCovarianceModel.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelFromCovarianceModel_whenNull_thenReturnArrayLengthIsOne3()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    LIBORMarketModelFromCovarianceModel actualLiborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization,
            forwardRateCurve,
            discountCurve,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            (CalibrationProduct[]) null,
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    ForwardCurve forwardRateCurve2 =
        actualLiborMarketModelFromCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel2 =
        actualLiborMarketModelFromCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelFromCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 = covarianceModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = covarianceModel2.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameter().length);
    assertEquals(liborPeriodDiscretization2, liborPeriodDiscretization3);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((HullWhiteLocalVolatilityModel) covarianceModel2).getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime LIBORMarketModelFromCovarianceModel.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnNull() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertNull(ofResult.getReferenceDate());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return toLocalTime toString is {@code 00:00}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime LIBORMarketModelFromCovarianceModel.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnToLocalTimeToStringIs0000()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    LocalDateTime actualReferenceDate = ofResult.getReferenceDate();

    // Assert
    assertEquals("00:00", actualReferenceDate.toLocalTime().toString());
    LocalDate toLocalDateResult = actualReferenceDate.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult.toString());
    assertSame(referenceDate, toLocalDateResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, null, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire =
        liborMarketModelFromCovarianceModel.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire2() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, null, covarianceModel2);

    // Act
    RandomVariable actualNumeraire = liborMarketModelFromCovarianceModel.getNumeraire(null, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire3() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, null, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire =
        liborMarketModelFromCovarianceModel.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire4() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, null, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualNumeraire =
        liborMarketModelFromCovarianceModel.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenThrowIllegalArgumentException() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(new double[] {});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, null, covarianceModel2);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization2, forwardRateCurve2, covarianceModel4);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion), Scheme.EULER);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> liborMarketModelFromCovarianceModel.getNumeraire(process, 10.0d));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel3).getTimeDiscretization();
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess,
   * double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumerairetUnAdjusted(MonteCarloProcess, double)"
  })
  public void testGetNumerairetUnAdjusted() throws CalculationException {
    // Arrange
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
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumerairetUnAdjusted =
        liborMarketModelFromCovarianceModel.getNumerairetUnAdjusted(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumerairetUnAdjusted instanceof Scalar);
    assertEquals(1.0d, actualNumerairetUnAdjusted.getAverage(), 0.0);
    assertEquals(1.0d, actualNumerairetUnAdjusted.getMax(), 0.0);
    assertEquals(1.0d, actualNumerairetUnAdjusted.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumerairetUnAdjusted.expectation();
    assertSame(actualNumerairetUnAdjusted, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess,
   * double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2092515.250506638}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumerairetUnAdjusted(MonteCarloProcess, double)"
  })
  public void testGetNumerairetUnAdjusted_thenReturnAverageIs2092515250506638()
      throws CalculationException {
    // Arrange
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
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumerairetUnAdjusted =
        liborMarketModelFromCovarianceModel.getNumerairetUnAdjusted(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumerairetUnAdjusted instanceof Scalar);
    assertEquals(2092515.250506638d, actualNumerairetUnAdjusted.getAverage(), 0.0);
    assertEquals(2092515.250506638d, actualNumerairetUnAdjusted.getMax(), 0.0);
    assertEquals(2092515.250506638d, actualNumerairetUnAdjusted.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumerairetUnAdjusted.expectation();
    assertSame(actualNumerairetUnAdjusted, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess,
   * double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 26.82367737036093}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumerairetUnAdjusted(MonteCarloProcess, double)"
  })
  public void testGetNumerairetUnAdjusted_thenReturnAverageIs2682367737036093()
      throws CalculationException {
    // Arrange
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
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualNumerairetUnAdjusted =
        liborMarketModelFromCovarianceModel.getNumerairetUnAdjusted(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumerairetUnAdjusted instanceof Scalar);
    assertEquals(26.82367737036093d, actualNumerairetUnAdjusted.getAverage(), 0.0);
    assertEquals(26.82367737036093d, actualNumerairetUnAdjusted.getMax(), 0.0);
    assertEquals(26.82367737036093d, actualNumerairetUnAdjusted.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumerairetUnAdjusted.expectation();
    assertSame(actualNumerairetUnAdjusted, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumerairetUnAdjusted(MonteCarloProcess, double)"
  })
  public void testGetNumerairetUnAdjusted_thenReturnRandomVariableFromFloatArray()
      throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumerairetUnAdjusted = ofResult.getNumerairetUnAdjusted(process, 10.0d);

    // Assert
    assertTrue(actualNumerairetUnAdjusted instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumerairetUnAdjusted.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualNumerairetUnAdjusted.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumerairetUnAdjusted.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumerairetUnAdjusted.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumerairetUnAdjusted.getVariance(), 0.0);
    assertEquals(1, actualNumerairetUnAdjusted.getTypePriority());
    assertEquals(1, actualNumerairetUnAdjusted.size());
    assertEquals(1.0d, actualNumerairetUnAdjusted.getAverage(), 0.0);
    assertEquals(1.0d, actualNumerairetUnAdjusted.getMax(), 0.0);
    assertEquals(1.0d, actualNumerairetUnAdjusted.getMin(), 0.0);
    assertTrue(actualNumerairetUnAdjusted.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumerairetUnAdjusted.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualNumerairetUnAdjusted.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess,
   * double)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getNumerairetUnAdjusted(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getNumerairetUnAdjusted(MonteCarloProcess, double)"
  })
  public void testGetNumerairetUnAdjusted_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(new double[] {});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization2, forwardRateCurve2, covarianceModel4);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion), Scheme.EULER);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> liborMarketModelFromCovarianceModel.getNumerairetUnAdjusted(process, 10.0d));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel3).getTimeDiscretization();
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumeraireAdjustments()}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getNumeraireAdjustments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map LIBORMarketModelFromCovarianceModel.getNumeraireAdjustments()"})
  public void testGetNumeraireAdjustments() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertTrue(ofResult.getNumeraireAdjustments().isEmpty());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = ofResult.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[1] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[2] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[3] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[4] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[5] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[6] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[7] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[8] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[9] instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualInitialState.length);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState2() throws CalculationException {
    // Arrange
    TimeDiscretization liborPeriodDiscretization = mock(TimeDiscretization.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = ofResult.getInitialState(process);

    // Assert
    verify(liborPeriodDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualInitialState[0] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[1] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[2] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[3] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[4] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[5] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[6] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[7] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[8] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[9] instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualInitialState.length);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState3() throws CalculationException {
    // Arrange
    TimeDiscretization liborPeriodDiscretization = mock(TimeDiscretization.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    AnalyticModel analyticModel = mock(AnalyticModel.class);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    when(analyticModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    when(analyticModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = ofResult.getInitialState(process);

    // Assert
    verify(analyticModel, atLeast(1)).getDiscountCurve("3");
    verify(analyticModel, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(liborPeriodDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualInitialState[0] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[1] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[2] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[3] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[4] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[5] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[6] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[7] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[8] instanceof RandomVariableFromFloatArray);
    assertTrue(actualInitialState[9] instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualInitialState.length);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenReturnArrayLengthIsZero() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
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
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = ofResult.getInitialState(process);

    // Assert
    assertEquals(0, actualInitialState.length);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenThrowIllegalArgumentException() throws CalculationException {
    // Arrange
    TimeDiscretization liborPeriodDiscretization = mock(TimeDiscretization.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModel analyticModel = mock(AnalyticModel.class);
    when(analyticModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    when(analyticModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> ofResult.getInitialState(process));
    verify(analyticModel).getDiscountCurve("3");
    verify(analyticModel).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(liborPeriodDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(liborPeriodDiscretization).getTime(0);
    verify(liborPeriodDiscretization).getTimeStep(0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenIllegalArgumentException_thenThrowIllegalArgumentException() {
    // Arrange
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelFromCovarianceModel.getDrift(
                monteCarloProcess,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(monteCarloProcess, atLeast(1)).getTime(1);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelFromCovarianceModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenThrowIllegalArgumentException() {
    // Arrange
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelFromCovarianceModel.getFactorLoading(
                monteCarloProcess,
                1,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(monteCarloProcess, atLeast(1)).getTime(1);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        ofResult.applyStateSpaceTransform(process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformResult.getTypePriority());
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {22026.465794806718d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariable.exp()).thenReturn(randomVariableDifferentiableAAD);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        lIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(
            monteCarloProcess, 1, 1, randomVariable);

    // Assert
    verify(randomVariable).exp();
    assertTrue(
        actualApplyStateSpaceTransformResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableDifferentiableAAD);
    assertSame(
        factory,
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult).getFactory());
    assertArrayEquals(
        new double[] {10.0d}, actualApplyStateSpaceTransformResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenValuesReturnScalar() {
    // Arrange
    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.exp()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        lIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(
            monteCarloProcess, 1, 1, randomVariable);

    // Assert
    verify(randomVariable).exp();
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualApplyStateSpaceTransformResult.getValues() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.isNaN() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformResult.getOperator());
    assertNull(actualApplyStateSpaceTransformResult.getRealizationsStream());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_whenScalarWithValueIsTen_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualApplyStateSpaceTransformResult =
        lIBORMarketModelFromCovarianceModel.applyStateSpaceTransform(
            monteCarloProcess, 1, 1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.abs() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.cos() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.exp() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.expm1() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.invert() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.isNaN() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.sin() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.sqrt() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.squared() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.variance() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformResult.getOperator());
    assertNull(actualApplyStateSpaceTransformResult.getRealizationsStream());
    assertEquals(0, actualApplyStateSpaceTransformResult.getTypePriority());
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualApplyStateSpaceTransformResult.expectation();
    assertSame(actualApplyStateSpaceTransformResult, actualExpectationResult);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        ofResult.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {2.302585092994046d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_whenScalarWithValueIsTen_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        lIBORMarketModelFromCovarianceModel.applyStateSpaceTransformInverse(
            monteCarloProcess, 1, 1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformInverseResult instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.abs() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.cos() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.exp() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.expm1() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.invert() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isNaN() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.sin() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.sqrt() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.squared() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.variance() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformInverseResult.getOperator());
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizationsStream());
    assertEquals(0, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    RandomVariable actualExpectationResult =
        actualApplyStateSpaceTransformInverseResult.expectation();
    assertSame(actualApplyStateSpaceTransformInverseResult, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnRandomVariableFromFloatArray()
      throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    RandomVariable actualRandomVariableForConstant = ofResult.getRandomVariableForConstant(10.0d);

    // Assert
    assertTrue(actualRandomVariableForConstant instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableForConstant.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.getTypePriority());
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualRandomVariableForConstant.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() throws CalculationException {
    // Arrange
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
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel liborMarketModelFromCovarianceModel =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    RandomVariable actualRandomVariableForConstant =
        liborMarketModelFromCovarianceModel.getRandomVariableForConstant(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualRandomVariableForConstant instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.abs() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.cos() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.exp() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.invert() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sin() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.squared() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.variance() instanceof Scalar);
    assertNull(actualRandomVariableForConstant.getRealizations());
    assertNull(actualRandomVariableForConstant.getOperator());
    assertNull(actualRandomVariableForConstant.getRealizationsStream());
    assertEquals(0, actualRandomVariableForConstant.getTypePriority());
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getLIBOR(MonteCarloProcess, int, int)} with
   * {@code process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getLIBOR(MonteCarloProcess,
   * int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex() throws CalculationException {
    // Arrange
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(randomVariableFromDoubleArray);

    // Act
    RandomVariable actualLIBOR =
        lIBORMarketModelFromCovarianceModel.getLIBOR(monteCarloProcess, 1, 1);

    // Assert
    verify(monteCarloProcess, atLeast(1)).getProcessValue(1, 1);
    assertSame(randomVariableFromDoubleArray, actualLIBOR);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getLIBOR(MonteCarloProcess, int, int)} with
   * {@code process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getLIBOR(MonteCarloProcess,
   * int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelFromCovarianceModel.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> lIBORMarketModelFromCovarianceModel.getLIBOR(monteCarloProcess, 1, 1));
    verify(monteCarloProcess, atLeast(1)).getProcessValue(1, 1);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumberOfComponents()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelFromCovarianceModel.getNumberOfComponents()"})
  public void testGetNumberOfComponents_thenReturnTen() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertEquals(10, ofResult.getNumberOfComponents());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelFromCovarianceModel.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenReturnTen() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertEquals(10, ofResult.getNumberOfLibors());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelFromCovarianceModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenReturnThree() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertEquals(3, ofResult.getNumberOfFactors());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelFromCovarianceModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenThrowIllegalArgumentException() {
    // Arrange
    when(lIBORCovarianceModel.getNumberOfFactors()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> lIBORMarketModelFromCovarianceModel.getNumberOfFactors());
    verify(lIBORCovarianceModel).getNumberOfFactors();
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORMarketModelFromCovarianceModel.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertEquals(10.5d, ofResult.getLiborPeriod(1), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelFromCovarianceModel.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    assertEquals(0, ofResult.getLiborPeriodIndex(10.0d));
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#getIntegratedLIBORCovariance(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getIntegratedLIBORCovariance(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][][] LIBORMarketModelFromCovarianceModel.getIntegratedLIBORCovariance(TimeDiscretization)"
  })
  public void testGetIntegratedLIBORCovariance() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    double[][][] actualIntegratedLIBORCovariance =
        ofResult.getIntegratedLIBORCovariance(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertEquals(10, actualIntegratedLIBORCovariance.length);
    assertEquals(10, actualIntegratedLIBORCovariance[0].length);
    assertEquals(10, actualIntegratedLIBORCovariance[1].length);
    assertEquals(10, actualIntegratedLIBORCovariance[2].length);
    assertEquals(10, actualIntegratedLIBORCovariance[3].length);
    assertEquals(10, actualIntegratedLIBORCovariance[4].length);
    assertEquals(10, actualIntegratedLIBORCovariance[5].length);
    assertEquals(10, actualIntegratedLIBORCovariance[6].length);
    assertEquals(10, actualIntegratedLIBORCovariance[7].length);
    assertEquals(10, actualIntegratedLIBORCovariance[8].length);
    assertEquals(10, actualIntegratedLIBORCovariance[9].length);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#getIntegratedLIBORCovariance(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getIntegratedLIBORCovariance(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][][] LIBORMarketModelFromCovarianceModel.getIntegratedLIBORCovariance(TimeDiscretization)"
  })
  public void testGetIntegratedLIBORCovariance2() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    double[][][] actualIntegratedLIBORCovariance =
        ofResult.getIntegratedLIBORCovariance(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertEquals(10, actualIntegratedLIBORCovariance.length);
    assertEquals(10, actualIntegratedLIBORCovariance[0].length);
    assertEquals(10, actualIntegratedLIBORCovariance[1].length);
    assertEquals(10, actualIntegratedLIBORCovariance[2].length);
    assertEquals(10, actualIntegratedLIBORCovariance[3].length);
    assertEquals(10, actualIntegratedLIBORCovariance[4].length);
    assertEquals(10, actualIntegratedLIBORCovariance[5].length);
    assertEquals(10, actualIntegratedLIBORCovariance[6].length);
    assertEquals(10, actualIntegratedLIBORCovariance[7].length);
    assertEquals(10, actualIntegratedLIBORCovariance[8].length);
    assertEquals(10, actualIntegratedLIBORCovariance[9].length);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#clone()}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORMarketModelFromCovarianceModel.clone()"})
  public void testClone() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    Object actualCloneResult = ofResult.clone();

    // Assert
    AnalyticModel analyticModel2 =
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 =
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 =
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneResult instanceof LIBORMarketModelFromCovarianceModel);
    assertTrue(
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(((LIBORMarketModelFromCovarianceModel) actualCloneResult).getSwaptionMarketData());
    assertEquals(
        10, ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getNumberOfComponents());
    assertEquals(10, ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getNumberOfLibors());
    assertEquals(3, ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getInterpolationMethod());
    assertEquals(
        Measure.SPOT, ((LIBORMarketModelFromCovarianceModel) actualCloneResult).getMeasure());
    assertTrue(
        ((LIBORMarketModelFromCovarianceModel) actualCloneResult)
            .getNumeraireAdjustments()
            .isEmpty());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * LIBORMarketModelFromCovarianceModel#getCloneWithModifiedCovarianceModel(LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelFromCovarianceModel#getCloneWithModifiedCovarianceModel(LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.getCloneWithModifiedCovarianceModel(LIBORCovarianceModel)"
  })
  public void testGetCloneWithModifiedCovarianceModel() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelFromCovarianceModel actualCloneWithModifiedCovarianceModel =
        ofResult.getCloneWithModifiedCovarianceModel(covarianceModel4);

    // Assert
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel3).getTimeDiscretization();
    AnalyticModel analyticModel2 = actualCloneWithModifiedCovarianceModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualCloneWithModifiedCovarianceModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel5 =
        actualCloneWithModifiedCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel5 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedCovarianceModel.getSwaptionMarketData());
    assertEquals(10, actualCloneWithModifiedCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualCloneWithModifiedCovarianceModel.getNumberOfLibors());
    assertEquals(3, actualCloneWithModifiedCovarianceModel.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        actualCloneWithModifiedCovarianceModel.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualCloneWithModifiedCovarianceModel.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualCloneWithModifiedCovarianceModel.getMeasure());
    assertTrue(actualCloneWithModifiedCovarianceModel.getNumeraireAdjustments().isEmpty());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel4, covarianceModel5);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() throws CalculationException {
    // Arrange
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    LIBORMarketModelFromCovarianceModel actualCloneWithModifiedData =
        ofResult.getCloneWithModifiedData(new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualCloneWithModifiedData.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualCloneWithModifiedData.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedData.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualCloneWithModifiedData.getCovarianceModel() instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedData.getSwaptionMarketData());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfLibors());
    assertEquals(3, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualCloneWithModifiedData.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualCloneWithModifiedData.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualCloneWithModifiedData.getMeasure());
    assertTrue(actualCloneWithModifiedData.getNumeraireAdjustments().isEmpty());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return AnalyticModel is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelFromCovarianceModel LIBORMarketModelFromCovarianceModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnAnalyticModelIsNull()
      throws CalculationException {
    // Arrange and Act
    LIBORMarketModelFromCovarianceModel actualCloneWithModifiedData =
        lIBORMarketModelFromCovarianceModel.getCloneWithModifiedData(null);

    // Assert
    assertNull(actualCloneWithModifiedData.getAnalyticModel());
    assertNull(actualCloneWithModifiedData.getDiscountCurve());
    assertNull(actualCloneWithModifiedData.getForwardRateCurve());
    assertNull(actualCloneWithModifiedData.getSwaptionMarketData());
    assertNull(actualCloneWithModifiedData.getLiborPeriodDiscretization());
    assertEquals(0, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualCloneWithModifiedData.getDriftApproximationMethod());
    assertEquals(
        InterpolationMethod.LOG_LINEAR_UNCORRECTED,
        actualCloneWithModifiedData.getInterpolationMethod());
    assertEquals(Measure.SPOT, actualCloneWithModifiedData.getMeasure());
    assertTrue(actualCloneWithModifiedData.getNumeraireAdjustments().isEmpty());
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getModelParameters()}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map LIBORMarketModelFromCovarianceModel.getModelParameters()"})
  public void testGetModelParameters() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    Map<String, RandomVariable> actualModelParameters = ofResult.getModelParameters();

    // Assert
    assertEquals(5, actualModelParameters.size());
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(0)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(1)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(2)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(3)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(4)") instanceof Scalar);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getModelParameters()}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map LIBORMarketModelFromCovarianceModel.getModelParameters()"})
  public void testGetModelParameters2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
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

    LIBORMarketModelFromCovarianceModel ofResult =
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
    Map<String, RandomVariable> actualModelParameters = ofResult.getModelParameters();

    // Assert
    assertEquals(5, actualModelParameters.size());
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(0)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(1)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(2)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(3)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(4)") instanceof Scalar);
  }

  /**
   * Test {@link LIBORMarketModelFromCovarianceModel#getModelParameters()}.
   *
   * <p>Method under test: {@link LIBORMarketModelFromCovarianceModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map LIBORMarketModelFromCovarianceModel.getModelParameters()"})
  public void testGetModelParameters3() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
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

    LIBORMarketModelFromCovarianceModel ofResult =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act
    Map<String, RandomVariable> actualModelParameters = ofResult.getModelParameters();

    // Assert
    assertEquals(5, actualModelParameters.size());
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(0)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(1)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(2)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(3)") instanceof Scalar);
    assertTrue(actualModelParameters.get("COVARIANCEMODELPARAMETER(4)") instanceof Scalar);
  }
}
