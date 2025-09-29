package net.finmath.montecarlo.interestrate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.models.HullWhiteModel;
import net.finmath.montecarlo.interestrate.models.HullWhiteModelWithConstantCoeff;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelStandard;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelHoLee;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LIBORMonteCarloSimulationFromLIBORModelDiffblueTest {
  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getMonteCarloWeights(double)} with {@code
   * time}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getMonteCarloWeights(double)"
  })
  public void testGetMonteCarloWeightsWithTime() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getMonteCarloWeights(10.0d);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualMonteCarloWeights);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getMonteCarloWeights(int)} with {@code
   * timeIndex}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getMonteCarloWeights(int)"
  })
  public void testGetMonteCarloWeightsWithTimeIndex() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualMonteCarloWeights);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMonteCarloSimulationFromLIBORModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    int actualNumberOfFactors =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumberOfFactors();

    // Assert
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(3, actualNumberOfFactors);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMonteCarloSimulationFromLIBORModel.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    int actualNumberOfPaths =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumberOfPaths();

    // Assert
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10, actualNumberOfPaths);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return toLocalTime toString is {@code 00:00}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime LIBORMonteCarloSimulationFromLIBORModel.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnToLocalTimeToStringIs0000()
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    LocalDateTime actualReferenceDate =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getReferenceDate();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals("00:00", actualReferenceDate.toLocalTime().toString());
    LocalDate toLocalDateResult = actualReferenceDate.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult.toString());
    assertSame(referenceDate, toLocalDateResult);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORMonteCarloSimulationFromLIBORModel.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    double actualTime = new LIBORMonteCarloSimulationFromLIBORModel(process).getTime(1);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10.5d, actualTime, 0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization LIBORMonteCarloSimulationFromLIBORModel.getTimeDiscretization()"
  })
  public void testGetTimeDiscretization() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    TimeDiscretization actualTimeDiscretization =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getTimeDiscretization();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertSame(tenorFromArray, actualTimeDiscretization);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMonteCarloSimulationFromLIBORModel.getTimeIndex(double)"})
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    int actualTimeIndex = new LIBORMonteCarloSimulationFromLIBORModel(process).getTimeIndex(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(0, actualTimeIndex);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualRandomVariableForConstant =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getRandomVariableForConstant(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
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
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getBrownianMotion()}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getBrownianMotion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion LIBORMonteCarloSimulationFromLIBORModel.getBrownianMotion()"})
  public void testGetBrownianMotion() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    BrownianMotion actualBrownianMotion =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getBrownianMotion();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertSame(stochasticDriver, actualBrownianMotion);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getLIBOR(int, int)} with {@code timeIndex},
   * {@code liborIndex}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getLIBOR(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getLIBOR(int, int)"})
  public void testGetLIBORWithTimeIndexLiborIndex_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
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

    HullWhiteModelWithConstantCoeff model =
        new HullWhiteModelWithConstantCoeff(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            10.0d,
            10.0d,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualLIBOR =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getLIBOR(1, 1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
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
    assertEquals(0.0d, actualLIBOR.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardError(), 0.0);
    assertEquals(0.0d, actualLIBOR.getVariance(), 0.0);
    assertEquals(1, actualLIBOR.getTypePriority());
    assertEquals(1, actualLIBOR.size());
    assertTrue(actualLIBOR.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLIBOR.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getAverage(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMax(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualLIBOR.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getLIBORs(int)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getLIBORs(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORMonteCarloSimulationFromLIBORModel.getLIBORs(int)"})
  public void testGetLIBORs_thenReturnArrayLengthIsZero() throws CalculationException {
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
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(null);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualLIBORs =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getLIBORs(1);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualLIBORs.length);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getForwardRate(double, double, double)}
   * with {@code time}, {@code periodStart}, {@code periodEnd}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getForwardRate(double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getForwardRate(double, double, double)"
  })
  public void testGetForwardRateWithTimePeriodStartPeriodEnd() throws CalculationException {
    // Arrange
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(hullWhiteModel.getForwardRate(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(hullWhiteModel);

    // Act
    RandomVariable actualForwardRate =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getForwardRate(10.0d, 10.0d, 10.0d);

    // Assert
    verify(hullWhiteModel)
        .getForwardRate(isA(MonteCarloProcess.class), eq(10.0d), eq(10.0d), eq(10.0d));
    verify(process).getModel();
    assertSame(randomVariableFromDoubleArray, actualForwardRate);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORMonteCarloSimulationFromLIBORModel.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    double actualLiborPeriod =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getLiborPeriod(1);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10.5d, actualLiborPeriod, 0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getLiborPeriodDiscretization()}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getLiborPeriodDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization LIBORMonteCarloSimulationFromLIBORModel.getLiborPeriodDiscretization()"
  })
  public void testGetLiborPeriodDiscretization() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    TimeDiscretization actualLiborPeriodDiscretization =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getLiborPeriodDiscretization();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertSame(liborPeriodDiscretization, actualLiborPeriodDiscretization);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMonteCarloSimulationFromLIBORModel.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    int actualLiborPeriodIndex =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getLiborPeriodIndex(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(0, actualLiborPeriodIndex);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfComponents()}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMonteCarloSimulationFromLIBORModel.getNumberOfComponents()"})
  public void testGetNumberOfComponents_thenReturnTwo() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    int actualNumberOfComponents =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumberOfComponents();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(2, actualNumberOfComponents);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMonteCarloSimulationFromLIBORModel.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenReturnTen() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    int actualNumberOfLibors =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumberOfLibors();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10, actualNumberOfLibors);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenReturnAverageIsNaN() throws CalculationException {
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
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    LIBORMarketModelStandard model =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumeraire(10.0d);

    // Assert
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualNumeraire.getAverage(), 0.0);
    assertEquals(Double.NaN, actualNumeraire.getMax(), 0.0);
    assertEquals(Double.NaN, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenReturnRealizationsIsArrayOfDoubleWithOne()
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard model =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumeraire(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LIBORMonteCarloSimulationFromLIBORModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenReturnScalar() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getNumeraire(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
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
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(1.0d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getModel()}.
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LIBORModel LIBORMonteCarloSimulationFromLIBORModel.getModel()"})
  public void testGetModel() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    LIBORModel actualModel = new LIBORMonteCarloSimulationFromLIBORModel(process).getModel();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertNull(actualModel);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object LIBORMonteCarloSimulationFromLIBORModel.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    when(brownianMotion.getCloneWithModifiedSeed(anyInt()))
        .thenReturn(new BrownianMotionWithControlVariate(brownianMotion2));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    Object actualCloneWithModifiedSeed =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getCloneWithModifiedSeed(42);

    // Assert
    verify(brownianMotion).getCloneWithModifiedSeed(42);
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(
        ((LIBORMonteCarloSimulationFromLIBORModel) actualCloneWithModifiedSeed).getBrownianMotion()
            instanceof BrownianMotionWithControlVariate);
    assertTrue(actualCloneWithModifiedSeed instanceof LIBORMonteCarloSimulationFromLIBORModel);
    assertTrue(
        ((LIBORMonteCarloSimulationFromLIBORModel) actualCloneWithModifiedSeed).getProcess()
            instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization2 =
        ((LIBORMonteCarloSimulationFromLIBORModel) actualCloneWithModifiedSeed)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(((LIBORMonteCarloSimulationFromLIBORModel) actualCloneWithModifiedSeed).getModel());
    assertEquals(
        10,
        ((LIBORMonteCarloSimulationFromLIBORModel) actualCloneWithModifiedSeed).getNumberOfPaths());
    assertEquals(
        3,
        ((LIBORMonteCarloSimulationFromLIBORModel) actualCloneWithModifiedSeed)
            .getNumberOfFactors());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(Map)} with {@code
   * dataModified}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORModelMonteCarloSimulationModel LIBORMonteCarloSimulationFromLIBORModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedDataWithDataModified() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        new LIBORMonteCarloSimulationFromLIBORModel(process);

    // Act
    LIBORModelMonteCarloSimulationModel actualCloneWithModifiedData =
        liborMonteCarloSimulationFromLIBORModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof LIBORMonteCarloSimulationFromLIBORModel);
    MonteCarloProcess process2 = actualCloneWithModifiedData.getProcess();
    assertTrue(process2 instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(2, ((EulerSchemeFromProcessModel) process2).getInitialState().length);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(Map)} with {@code
   * dataModified}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORModelMonteCarloSimulationModel LIBORMonteCarloSimulationFromLIBORModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedDataWithDataModified2() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        new LIBORMonteCarloSimulationFromLIBORModel(process);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("discountCurve", "Data Modified");

    // Act
    LIBORModelMonteCarloSimulationModel actualCloneWithModifiedData =
        liborMonteCarloSimulationFromLIBORModel.getCloneWithModifiedData(dataModified);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof LIBORMonteCarloSimulationFromLIBORModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertSame(process, actualCloneWithModifiedData.getProcess());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(String, Object)}
   * with {@code entityKey}, {@code dataModified}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORModelMonteCarloSimulationModel LIBORMonteCarloSimulationFromLIBORModel.getCloneWithModifiedData(String, Object)"
  })
  public void testGetCloneWithModifiedDataWithEntityKeyDataModified() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    LIBORModelMonteCarloSimulationModel actualCloneWithModifiedData =
        new LIBORMonteCarloSimulationFromLIBORModel(process)
            .getCloneWithModifiedData("Entity Key", "Data Modified");

    // Assert
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    BrownianMotion brownianMotion2 = actualCloneWithModifiedData.getBrownianMotion();
    assertTrue(brownianMotion2 instanceof BrownianMotionWithControlVariate);
    assertTrue(actualCloneWithModifiedData instanceof LIBORMonteCarloSimulationFromLIBORModel);
    TermStructureModel model2 = actualCloneWithModifiedData.getModel();
    assertTrue(model2 instanceof HullWhiteModel);
    MonteCarloProcess process2 = actualCloneWithModifiedData.getProcess();
    assertTrue(process2 instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertSame(brownianMotion2, process2.getStochasticDriver());
    assertSame(model2, process2.getModel());
    assertSame(timeDiscretization, brownianMotion2.getTimeDiscretization());
    assertSame(timeDiscretization, process2.getTimeDiscretization());
    assertSame(
        liborPeriodDiscretization2, ((HullWhiteModel) model2).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(String, Object)}
   * with {@code entityKey}, {@code dataModified}.
   *
   * <p>Method under test: {@link
   * LIBORMonteCarloSimulationFromLIBORModel#getCloneWithModifiedData(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORModelMonteCarloSimulationModel LIBORMonteCarloSimulationFromLIBORModel.getCloneWithModifiedData(String, Object)"
  })
  public void testGetCloneWithModifiedDataWithEntityKeyDataModified2() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    LIBORModelMonteCarloSimulationModel actualCloneWithModifiedData =
        new LIBORMonteCarloSimulationFromLIBORModel(process)
            .getCloneWithModifiedData("discountCurve", "Data Modified");

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    BrownianMotion brownianMotion2 = actualCloneWithModifiedData.getBrownianMotion();
    assertTrue(brownianMotion2 instanceof BrownianMotionWithControlVariate);
    assertTrue(actualCloneWithModifiedData instanceof LIBORMonteCarloSimulationFromLIBORModel);
    TermStructureModel model2 = actualCloneWithModifiedData.getModel();
    assertTrue(model2 instanceof HullWhiteModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization2, timeDiscretization);
    assertSame(process, actualCloneWithModifiedData.getProcess());
    assertSame(timeDiscretization, brownianMotion2.getTimeDiscretization());
    assertSame(
        liborPeriodDiscretization2, ((HullWhiteModel) model2).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORMonteCarloSimulationFromLIBORModel#getModelParameters()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMonteCarloSimulationFromLIBORModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map LIBORMonteCarloSimulationFromLIBORModel.getModelParameters()"})
  public void testGetModelParameters_thenReturnEmpty() {
    // Arrange
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getModelParameters()).thenReturn(new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(hullWhiteModel);

    // Act
    Map<String, RandomVariable> actualModelParameters =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getModelParameters();

    // Assert
    verify(hullWhiteModel).getModelParameters();
    verify(process).getModel();
    assertTrue(actualModelParameters.isEmpty());
  }
}
