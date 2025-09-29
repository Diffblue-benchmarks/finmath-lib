package net.finmath.montecarlo.interestrate.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
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
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelAsGiven;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelHoLee;
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
public class HullWhiteModelWithDirectSimulationDiffblueTest {
  @Mock private ForwardCurve forwardCurve;

  @InjectMocks private HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation;

  @Mock private MonteCarloProcess monteCarloProcess;

  @Mock private ShortRateVolatilityModel shortRateVolatilityModel;

  @Mock private TimeDiscretization timeDiscretization;

  /**
   * Test {@link
   * HullWhiteModelWithDirectSimulation#HullWhiteModelWithDirectSimulation(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#HullWhiteModelWithDirectSimulation(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModelWithDirectSimulation.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModelWithDirectSimulation() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModelWithDirectSimulation actualHullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModelWithDirectSimulation.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModelWithDirectSimulation.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModelWithDirectSimulation.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModelWithDirectSimulation.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModelWithDirectSimulation.getNumberOfComponents());
    assertEquals(1, actualHullWhiteModelWithDirectSimulation.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModelWithDirectSimulation.getNumberOfLibors());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        hullWhiteModelWithDirectSimulation.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformResult);
  }

  /**
   * Test {@link
   * HullWhiteModelWithDirectSimulation#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        hullWhiteModelWithDirectSimulation.applyStateSpaceTransformInverse(
            process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformInverseResult);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Given {@link ForwardCurve}.
   *   <li>Then calls {@link MonteCarloProcess#getTimeDiscretization()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_givenForwardCurve_thenCallsGetTimeDiscretization() {
    // Arrange
    when(monteCarloProcess.getTimeDiscretization()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getInitialState(monteCarloProcess));
    verify(monteCarloProcess).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_givenLocalDateWith1970AndOneAndOne() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenThrow(new UnsupportedOperationException());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getInitialState(process));
    verify(timeDiscretization).getTimeStep(0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return first element Average is {@code -0.2302585092994045}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_givenTen_thenReturnFirstElementAverageIs02302585092994045() {
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
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable[] actualInitialState =
        hullWhiteModelWithDirectSimulation.getInitialState(process);

    // Assert
    verify(timeDiscretization).getTimeStep(0);
    RandomVariable randomVariable = actualInitialState[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.2302585092994045d, randomVariable.getAverage(), 0.0);
    assertEquals(-0.2302585092994045d, randomVariable.getMax(), 0.0);
    assertEquals(-0.2302585092994045d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualInitialState.length);
    assertArrayEquals(new double[] {-0.2302585092994045d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -0.09208787700281369}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenReturnFirstElementAverageIs009208787700281369() {
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
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState =
        hullWhiteModelWithDirectSimulation.getInitialState(process);

    // Assert
    RandomVariable randomVariable = actualInitialState[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.09208787700281369d, randomVariable.getAverage(), 0.0);
    assertEquals(-0.09208787700281369d, randomVariable.getMax(), 0.0);
    assertEquals(-0.09208787700281369d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualInitialState.length);
    assertArrayEquals(new double[] {-0.09208787700281369d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire =
        hullWhiteModelWithDirectSimulation.getNumeraire(process, 10.0d);

    // Assert
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
   * Test {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire2() throws CalculationException {
    // Arrange
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenThrow(new UnsupportedOperationException());
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    when(monteCarloProcess.getStochasticDriver())
        .thenReturn(new BrownianMotionWithControlVariate(brownianMotion));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getNumeraire(monteCarloProcess, 0.0d));
    verify(monteCarloProcess).getStochasticDriver();
    verify(monteCarloProcess, atLeast(1)).getProcessValue(0, 0);
    verify(monteCarloProcess, atLeast(1)).getTime(0);
    verify(monteCarloProcess).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTimeIndex(0.0d);
    verify(timeDiscretization, atLeast(1)).getTimeStep(0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnNull() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            null,
            volatilityModel,
            new HashMap<>());

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization2, forwardRateCurve2, covarianceModel2);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(0.5d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(0);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion), Scheme.EULER);

    // Act
    RandomVariable actualNumeraire =
        hullWhiteModelWithDirectSimulation.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertNull(actualNumeraire);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>When {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} return ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_whenTimeDiscretizationGetTimeReturnTen_thenAbsReturnScalar()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire =
        hullWhiteModelWithDirectSimulation.getNumeraire(process, 10.0d);

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
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
   * Test {@link HullWhiteModelWithDirectSimulation#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link ForwardCurve} {@link ForwardCurve#getForward(AnalyticModel, double)} return
   *       ten.
   *   <li>Then calls {@link ForwardCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenForwardCurveGetForwardReturnTen_thenCallsGetForward() {
    // Arrange
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable[] actualDrift =
        hullWhiteModelWithDirectSimulation.getDrift(
            monteCarloProcess,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(forwardCurve, atLeast(1)).getForward(isNull(), eq(0.0d));
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel, atLeast(1)).getVolatility(0);
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(monteCarloProcess).getTimeDiscretization();
    assertTrue(actualDrift[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenCallsGetNumberOfTimes() {
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
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable[] actualDrift =
        hullWhiteModelWithDirectSimulation.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertTrue(actualDrift[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithDirectSimulation.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithDirectSimulation.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading3() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithDirectSimulation.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading4() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hullWhiteModelWithDirectSimulation.getFactorLoading(
                monteCarloProcess,
                1,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_givenUnsupportedOperationException() {
    // Arrange
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hullWhiteModelWithDirectSimulation.getFactorLoading(
                monteCarloProcess,
                1,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(monteCarloProcess, atLeast(1)).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link ShortRateVolatilityModel#getMeanReversion(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenCallsGetMeanReversion() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithDirectSimulation.getFactorLoading(
            monteCarloProcess,
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link ShortRateVolatilityModel#getMeanReversion(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithDirectSimulation.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenCallsGetMeanReversion2() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithDirectSimulation.getFactorLoading(
            monteCarloProcess,
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(shortRateVolatilityModel).getMeanReversion(1);
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(1);
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act
    RandomVariable actualRandomVariableForConstant =
        hullWhiteModelWithDirectSimulation.getRandomVariableForConstant(10.0d);

    // Assert
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
   * Test {@link HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double,
   * double, double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    RandomVariableAAD start = mock(RandomVariableAAD.class);
    when(start.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(start.isDeterministic()).thenReturn(true);
    when(start.getFiltrationTime()).thenReturn(10.0d);
    when(start.getTypePriority()).thenReturn(1);
    when(start.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(
            new BrownianBridge(
                timeDiscretization, 10, 42, start, new RandomVariableFromDoubleArray(10.0d)));
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(1.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel2 = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve2 =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel2 = new ShortRateVolatilityModelHoLee(1.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization2,
            analyticModel2,
            forwardRateCurve2,
            discountCurve2,
            volatilityModel2,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_givenTrue_whenScalarWithValueIsOne_thenCallsDoubleValue()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    RandomVariableAAD start = mock(RandomVariableAAD.class);
    when(start.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(start.isDeterministic()).thenReturn(true);
    when(start.getFiltrationTime()).thenReturn(10.0d);
    when(start.getTypePriority()).thenReturn(1);
    when(start.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(
            new BrownianBridge(timeDiscretization, 10, 42, start, Scalar.of(1.0d)));
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimeSteps()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenCallsGetNumberOfTimeSteps() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel2 = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve2 =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel2 = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation model =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization2,
            analyticModel2,
            forwardRateCurve2,
            discountCurve2,
            volatilityModel2,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getTimeIndex(10.0d);
    verify(timeDiscretization).getTimeStep(0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualForwardRate =
        hullWhiteModelWithDirectSimulation.getForwardRate(monteCarloProcess, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(forwardCurve, atLeast(1)).getForward(isNull(), anyDouble());
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel, atLeast(1)).getVolatility(0);
    verify(monteCarloProcess, atLeast(1)).getProcessValue(1, 0);
    verify(monteCarloProcess, atLeast(1)).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTimeIndex(10.0d);
    assertTrue(actualForwardRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualForwardRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardRate.getVariance(), 0.0);
    assertEquals(1, actualForwardRate.getTypePriority());
    assertEquals(1, actualForwardRate.size());
    assertTrue(actualForwardRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardRate.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getAverage(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getMax(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualForwardRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_whenRandomVariableDifferentiableAADPathwiseWithValueIsOne()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    RandomVariableAAD start = mock(RandomVariableAAD.class);
    when(start.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(start.isDeterministic()).thenReturn(true);
    when(start.getFiltrationTime()).thenReturn(10.0d);
    when(start.getTypePriority()).thenReturn(1);
    when(start.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(
            new BrownianBridge(
                timeDiscretization,
                10,
                42,
                start,
                RandomVariableDifferentiableAADPathwise.of(1.0d)));
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getForwardRate(MonteCarloProcess, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_whenRandomVariableFromDoubleArrayWithValueIsTen()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    RandomVariableAAD start = mock(RandomVariableAAD.class);
    when(start.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(start.isDeterministic()).thenReturn(true);
    when(start.getFiltrationTime()).thenReturn(10.0d);
    when(start.getTypePriority()).thenReturn(1);
    when(start.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(
            new BrownianBridge(
                timeDiscretization, 10, 42, start, new RandomVariableFromDoubleArray(10.0d)));
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLIBOR(MonteCarloProcess, int, int)} with
   * {@code process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLIBOR(MonteCarloProcess,
   * int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithDirectSimulation.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex() throws CalculationException {
    // Arrange
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualLIBOR =
        hullWhiteModelWithDirectSimulation.getLIBOR(monteCarloProcess, 1, 1);

    // Assert
    verify(forwardCurve, atLeast(1)).getForward(isNull(), anyDouble());
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel, atLeast(1)).getVolatility(0);
    verify(monteCarloProcess, atLeast(1)).getProcessValue(1, 0);
    verify(monteCarloProcess, atLeast(1)).getTime(1);
    verify(monteCarloProcess, atLeast(1)).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTimeIndex(10.0d);
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization).getTimeStep(1);
    assertTrue(actualLIBOR instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualLIBOR.getAverage(), 0.0);
    assertEquals(0.0d, actualLIBOR.getMax(), 0.0);
    assertEquals(0.0d, actualLIBOR.getMin(), 0.0);
    assertEquals(0.0d, actualLIBOR.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardError(), 0.0);
    assertEquals(0.0d, actualLIBOR.getVariance(), 0.0);
    assertEquals(1, actualLIBOR.getTypePriority());
    assertEquals(1, actualLIBOR.size());
    assertTrue(actualLIBOR.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLIBOR.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualLIBOR.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getNumberOfLibors()}.
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithDirectSimulation.getNumberOfLibors()"})
  public void testGetNumberOfLibors() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(10, hullWhiteModelWithDirectSimulation.getNumberOfLibors());
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getNumberOfTimeSteps()} return
   *       ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithDirectSimulation.getNumberOfLibors()"})
  public void testGetNumberOfLibors_givenTimeDiscretizationGetNumberOfTimeStepsReturnTen() {
    // Arrange
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    // Act
    int actualNumberOfLibors = hullWhiteModelWithDirectSimulation.getNumberOfLibors();

    // Assert
    verify(timeDiscretization).getNumberOfTimeSteps();
    assertEquals(10, actualNumberOfLibors);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithDirectSimulation.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getNumberOfTimeSteps()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getNumberOfLibors());
    verify(timeDiscretization).getNumberOfTimeSteps();
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} return ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithDirectSimulation.getLiborPeriod(int)"})
  public void testGetLiborPeriod_givenTimeDiscretizationGetTimeReturnTen_thenReturnTen() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);

    // Act
    double actualLiborPeriod = hullWhiteModelWithDirectSimulation.getLiborPeriod(1);

    // Assert
    verify(timeDiscretization).getTime(1);
    assertEquals(10.0d, actualLiborPeriod, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithDirectSimulation.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(10.5d, hullWhiteModelWithDirectSimulation.getLiborPeriod(1), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithDirectSimulation.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getLiborPeriod(1));
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithDirectSimulation.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnOne() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    // Act
    int actualLiborPeriodIndex = hullWhiteModelWithDirectSimulation.getLiborPeriodIndex(10.0d);

    // Assert
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertEquals(1, actualLiborPeriodIndex);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithDirectSimulation.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(0, hullWhiteModelWithDirectSimulation.getLiborPeriodIndex(10.0d));
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithDirectSimulation.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getLiborPeriodIndex(10.0d));
    verify(timeDiscretization).getTimeIndex(10.0d);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HullWhiteModelWithDirectSimulation#getAnalyticModel()}
   *   <li>{@link HullWhiteModelWithDirectSimulation#getDiscountCurve()}
   *   <li>{@link HullWhiteModelWithDirectSimulation#getForwardRateCurve()}
   *   <li>{@link HullWhiteModelWithDirectSimulation#getLiborPeriodDiscretization()}
   *   <li>{@link HullWhiteModelWithDirectSimulation#getNumberOfComponents()}
   *   <li>{@link HullWhiteModelWithDirectSimulation#getNumberOfFactors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel HullWhiteModelWithDirectSimulation.getAnalyticModel()",
    "DiscountCurve HullWhiteModelWithDirectSimulation.getDiscountCurve()",
    "ForwardCurve HullWhiteModelWithDirectSimulation.getForwardRateCurve()",
    "TimeDiscretization HullWhiteModelWithDirectSimulation.getLiborPeriodDiscretization()",
    "int HullWhiteModelWithDirectSimulation.getNumberOfComponents()",
    "int HullWhiteModelWithDirectSimulation.getNumberOfFactors()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act
    AnalyticModel actualAnalyticModel = hullWhiteModelWithDirectSimulation.getAnalyticModel();
    DiscountCurve actualDiscountCurve = hullWhiteModelWithDirectSimulation.getDiscountCurve();
    ForwardCurve actualForwardRateCurve = hullWhiteModelWithDirectSimulation.getForwardRateCurve();
    TimeDiscretization actualLiborPeriodDiscretization =
        hullWhiteModelWithDirectSimulation.getLiborPeriodDiscretization();
    int actualNumberOfComponents = hullWhiteModelWithDirectSimulation.getNumberOfComponents();

    // Assert
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, hullWhiteModelWithDirectSimulation.getNumberOfFactors());
    assertSame(analyticModel, actualAnalyticModel);
    assertSame(discountCurve, actualDiscountCurve);
    assertSame(forwardRateCurve, actualForwardRateCurve);
    assertSame(liborPeriodDiscretization, actualLiborPeriodDiscretization);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.interestrate.LIBORMarketModel HullWhiteModelWithDirectSimulation.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getCloneWithModifiedData(new HashMap<>()));
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d,
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance2() {
    // Arrange
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance3() {
    // Arrange
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance4() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance5() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenCallsGetTime() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(Integer.MIN_VALUE);
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTime(-2147483648);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 7.016735912097631E20}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturn7016735912097631e20() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            timeDiscretization,
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        7.016735912097631E20d,
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturnZero() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    double actualShortRateConditionalVariance =
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d);

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
    assertEquals(0.0d, actualShortRateConditionalVariance, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturnZero2() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act
    double actualShortRateConditionalVariance =
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(10.0d, 10.0d);

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(1);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(1);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
    assertEquals(0.0d, actualShortRateConditionalVariance, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>When minus two.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenMinusTwo_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenTwo_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenZero_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getShortRateConditionalVariance(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility3() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            timeDiscretization,
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility4() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(-2.0d, 10, 0.5d),
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {
              10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            });

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility5() {
    // Arrange
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility6() {
    // Arrange
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility7() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility8() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTimeIndex(0.0d);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenCallsGetTime() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(Integer.MIN_VALUE);
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTime(-2147483648);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(anyDouble());
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link ShortRateVolatilityModel#getVolatility(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenCallsGetVolatility() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    double actualIntegratedBondSquaredVolatility =
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d);

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
    assertEquals(0.0d, actualIntegratedBondSquaredVolatility, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link ShortRateVolatilityModel#getVolatility(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenCallsGetVolatility2() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act
    double actualIntegratedBondSquaredVolatility =
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(10.0d, 10.0d);

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(1);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(1);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(anyDouble());
    assertEquals(0.0d, actualIntegratedBondSquaredVolatility, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When minus two.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenMinusTwo() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenTwo() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithDirectSimulation#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithDirectSimulation.getIntegratedBondSquaredVolatility(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithDirectSimulation#getModelParameters()}.
   *
   * <p>Method under test: {@link HullWhiteModelWithDirectSimulation#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map HullWhiteModelWithDirectSimulation.getModelParameters()"})
  public void testGetModelParameters() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithDirectSimulation hullWhiteModelWithDirectSimulation =
        new HullWhiteModelWithDirectSimulation(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithDirectSimulation.getModelParameters());
  }
}
