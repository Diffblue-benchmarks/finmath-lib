package net.finmath.montecarlo.interestrate.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelHoLee;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HullWhiteModelWithConstantCoeffDiffblueTest {
  /**
   * Test {@link HullWhiteModelWithConstantCoeff#HullWhiteModelWithConstantCoeff(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, double, double, Map)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#HullWhiteModelWithConstantCoeff(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, double, double, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModelWithConstantCoeff.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, double, double, Map)"
  })
  public void testNewHullWhiteModelWithConstantCoeff() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    HullWhiteModelWithConstantCoeff actualHullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModelWithConstantCoeff.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModelWithConstantCoeff.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModelWithConstantCoeff.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModelWithConstantCoeff.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModelWithConstantCoeff.getNumberOfComponents());
    assertEquals(1, actualHullWhiteModelWithConstantCoeff.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModelWithConstantCoeff.getNumberOfLibors());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        hullWhiteModelWithConstantCoeff.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformResult);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        hullWhiteModelWithConstantCoeff.applyStateSpaceTransformInverse(
            process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformInverseResult);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -0.2302585092994045}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithConstantCoeff.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenReturnFirstElementAverageIs02302585092994045() {
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

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
    RandomVariable[] actualInitialState = hullWhiteModelWithConstantCoeff.getInitialState(process);

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
   * Test {@link HullWhiteModelWithConstantCoeff#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -0.09208787700281369}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithConstantCoeff.getInitialState(MonteCarloProcess)"
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

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = hullWhiteModelWithConstantCoeff.getInitialState(process);

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
   * Test {@link HullWhiteModelWithConstantCoeff#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithConstantCoeff.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenThrowUnsupportedOperationException() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        () -> hullWhiteModelWithConstantCoeff.getInitialState(process));
    verify(timeDiscretization).getTimeStep(0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = hullWhiteModelWithConstantCoeff.getNumeraire(process, 10.0d);

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
   * Test {@link HullWhiteModelWithConstantCoeff#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnNull() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            null,
            10.0d,
            10.0d,
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
    RandomVariable actualNumeraire = hullWhiteModelWithConstantCoeff.getNumeraire(process, 10.0d);

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
   * Test {@link HullWhiteModelWithConstantCoeff#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>When {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} return ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_whenTimeDiscretizationGetTimeReturnTen_thenReturnScalar()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
    RandomVariable actualNumeraire = hullWhiteModelWithConstantCoeff.getNumeraire(process, 10.0d);

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
   * Test {@link HullWhiteModelWithConstantCoeff#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithConstantCoeff.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnRandomVariableFromDoubleArray() {
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

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        hullWhiteModelWithConstantCoeff.getDrift(
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
   * Test {@link HullWhiteModelWithConstantCoeff#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return first element Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithConstantCoeff.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_givenTen_thenReturnFirstElementAverageIsNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        hullWhiteModelWithConstantCoeff.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(Double.NaN, randomVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMax(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code 3.1622058757617877}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithConstantCoeff.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenReturnFirstElementAverageIs31622058757617877() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithConstantCoeff.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(3.1622058757617877d, randomVariable.getAverage(), 0.0);
    assertEquals(3.1622058757617877d, randomVariable.getMax(), 0.0);
    assertEquals(3.1622058757617877d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {3.1622058757617877d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act
    RandomVariable actualRandomVariableForConstant =
        hullWhiteModelWithConstantCoeff.getRandomVariableForConstant(10.0d);

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
   * Test {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(1.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization2,
            analyticModel2,
            forwardRateCurve2,
            discountCurve2,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        () -> hullWhiteModelWithConstantCoeff.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenCallsDoubleValue() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        () -> hullWhiteModelWithConstantCoeff.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimeSteps()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenCallsGetNumberOfTimeSteps() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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

    HullWhiteModelWithConstantCoeff model =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization2,
            analyticModel2,
            forwardRateCurve2,
            discountCurve2,
            10.0d,
            10.0d,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getTimeIndex(10.0d);
    verify(timeDiscretization).getTimeStep(0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getForwardRate(MonteCarloProcess,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getForwardRate(MonteCarloProcess, double, double, double)"
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

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
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
        () -> hullWhiteModelWithConstantCoeff.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(start).doubleValue();
    verify(start).getFiltrationTime();
    verify(start).getTypePriority();
    verify(start).isDeterministic();
    verify(start).mult(0.9d);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualLIBOR = hullWhiteModelWithConstantCoeff.getLIBOR(process, 1, 1);

    // Assert
    verify(process, atLeast(1)).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(1);
    verify(process, atLeast(1)).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeStep(1);
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
   * Test {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTimeStep(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getLIBOR(process, 1, 1));
    verify(process, atLeast(1)).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(1);
    verify(process, atLeast(1)).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex3() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);

    ForwardCurve forwardRateCurve = mock(ForwardCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        mock(RandomVariableFromDoubleArray.class);
    when(randomVariableFromDoubleArray.exp()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(randomVariableFromDoubleArray);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenThrow(new UnsupportedOperationException());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getProcessValue(anyInt(), anyInt())).thenReturn(scalar);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getLIBOR(process, 1, 1));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(randomVariableFromDoubleArray).exp();
    verify(process).getProcessValue(1, 0);
    verify(process).getTime(1);
    verify(process).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(scalar).mult(-0.0d);
    verify(timeDiscretization).getTimeStep(1);
    verify(liborPeriodDiscretization).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex4() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);

    ForwardCurve forwardRateCurve = mock(ForwardCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        mock(RandomVariableFromDoubleArray.class);
    when(randomVariableFromDoubleArray.exp()).thenThrow(new UnsupportedOperationException());

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(randomVariableFromDoubleArray);

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getProcessValue(anyInt(), anyInt())).thenReturn(scalar);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getLIBOR(process, 1, 1));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(randomVariableFromDoubleArray).exp();
    verify(process).getProcessValue(1, 0);
    verify(process).getTime(1);
    verify(process).getTimeIndex(10.0d);
    verify(scalar).mult(-0.0d);
    verify(liborPeriodDiscretization).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex_givenScalarWithValueIsTen()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);

    ForwardCurve forwardRateCurve = mock(ForwardCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenThrow(new UnsupportedOperationException());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getProcessValue(anyInt(), anyInt())).thenReturn(Scalar.of(10.0d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getLIBOR(process, 1, 1));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(process).getProcessValue(1, 0);
    verify(process).getTime(1);
    verify(process).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(timeDiscretization).getTimeStep(1);
    verify(liborPeriodDiscretization).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <ul>
   *   <li>Given {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithConstantCoeff.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex_givenUnsupportedOperationException()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenThrow(new UnsupportedOperationException());
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getLIBOR(process, 1, 1));
    verify(process).getProcessValue(1, 0);
    verify(process).getTime(1);
    verify(process).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithConstantCoeff.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenReturnTen() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(10, hullWhiteModelWithConstantCoeff.getNumberOfLibors());
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithConstantCoeff.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(10.5d, hullWhiteModelWithConstantCoeff.getLiborPeriod(1), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithConstantCoeff.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(0, hullWhiteModelWithConstantCoeff.getLiborPeriodIndex(10.0d));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HullWhiteModelWithConstantCoeff#getAnalyticModel()}
   *   <li>{@link HullWhiteModelWithConstantCoeff#getDiscountCurve()}
   *   <li>{@link HullWhiteModelWithConstantCoeff#getForwardRateCurve()}
   *   <li>{@link HullWhiteModelWithConstantCoeff#getLiborPeriodDiscretization()}
   *   <li>{@link HullWhiteModelWithConstantCoeff#getNumberOfComponents()}
   *   <li>{@link HullWhiteModelWithConstantCoeff#getNumberOfFactors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel HullWhiteModelWithConstantCoeff.getAnalyticModel()",
    "DiscountCurve HullWhiteModelWithConstantCoeff.getDiscountCurve()",
    "ForwardCurve HullWhiteModelWithConstantCoeff.getForwardRateCurve()",
    "TimeDiscretization HullWhiteModelWithConstantCoeff.getLiborPeriodDiscretization()",
    "int HullWhiteModelWithConstantCoeff.getNumberOfComponents()",
    "int HullWhiteModelWithConstantCoeff.getNumberOfFactors()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act
    AnalyticModel actualAnalyticModel = hullWhiteModelWithConstantCoeff.getAnalyticModel();
    DiscountCurve actualDiscountCurve = hullWhiteModelWithConstantCoeff.getDiscountCurve();
    ForwardCurve actualForwardRateCurve = hullWhiteModelWithConstantCoeff.getForwardRateCurve();
    TimeDiscretization actualLiborPeriodDiscretization =
        hullWhiteModelWithConstantCoeff.getLiborPeriodDiscretization();
    int actualNumberOfComponents = hullWhiteModelWithConstantCoeff.getNumberOfComponents();

    // Assert
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, hullWhiteModelWithConstantCoeff.getNumberOfFactors());
    assertSame(analyticModel, actualAnalyticModel);
    assertSame(discountCurve, actualDiscountCurve);
    assertSame(forwardRateCurve, actualForwardRateCurve);
    assertSame(liborPeriodDiscretization, actualLiborPeriodDiscretization);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.interestrate.LIBORMarketModel HullWhiteModelWithConstantCoeff.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getCloneWithModifiedData(new HashMap<>()));
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When minus two.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenMinusTwo_thenReturnFive() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        5.0d, hullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(-2.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenOne_thenReturnFive() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        5.0d, hullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenTen_thenReturnZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d, hullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenTwo_thenReturnFive() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        5.0d, hullWhiteModelWithConstantCoeff.getShortRateConditionalVariance(2.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code -1.1769263341851002E16}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenReturn11769263341851002e16() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        -1.1769263341851002E16d,
        hullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.04999999989694233}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenOne_thenReturn004999999989694233() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.04999999989694233d,
        hullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(1.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenTen_thenReturnZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d,
        hullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.05}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithConstantCoeff#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenTwo_thenReturn005() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.05d,
        hullWhiteModelWithConstantCoeff.getIntegratedBondSquaredVolatility(2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithConstantCoeff#getModelParameters()}.
   *
   * <p>Method under test: {@link HullWhiteModelWithConstantCoeff#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map HullWhiteModelWithConstantCoeff.getModelParameters()"})
  public void testGetModelParameters() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HullWhiteModelWithConstantCoeff hullWhiteModelWithConstantCoeff =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithConstantCoeff.getModelParameters());
  }
}
