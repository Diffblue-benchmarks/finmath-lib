package net.finmath.montecarlo.interestrate;

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
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.models.HullWhiteModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelStandard;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelHoLee;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TermStructureMonteCarloSimulationFromTermStructureModelDiffblueTest {
  /**
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getMonteCarloWeights(double)} with
   * {@code time}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationFromTermStructureModel.getMonteCarloWeights(double)"
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getMonteCarloWeights(10.0d);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualMonteCarloWeights);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getMonteCarloWeights(int)}
   * with {@code timeIndex}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationFromTermStructureModel.getMonteCarloWeights(int)"
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualMonteCarloWeights);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int TermStructureMonteCarloSimulationFromTermStructureModel.getNumberOfFactors()"
  })
  public void testGetNumberOfFactors_thenReturnThree() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    int actualNumberOfFactors =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process).getNumberOfFactors();

    // Assert
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(3, actualNumberOfFactors);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getNumberOfPaths()}.
   *
   * <ul>
   *   <li>Given {@link BrownianMotion} {@link BrownianMotion#getNumberOfPaths()} return ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int TermStructureMonteCarloSimulationFromTermStructureModel.getNumberOfPaths()"
  })
  public void testGetNumberOfPaths_givenBrownianMotionGetNumberOfPathsReturnTen_thenReturnTen() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    int actualNumberOfPaths =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process).getNumberOfPaths();

    // Assert
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10, actualNumberOfPaths);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getReferenceDate()}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDateTime TermStructureMonteCarloSimulationFromTermStructureModel.getReferenceDate()"
  })
  public void testGetReferenceDate() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            new TermStructureMonteCarloSimulationFromTermStructureModel(process)
                .getReferenceDate());
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TermStructureMonteCarloSimulationFromTermStructureModel.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    double actualTime =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process).getTime(1);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10.5d, actualTime, 0.0);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization TermStructureMonteCarloSimulationFromTermStructureModel.getTimeDiscretization()"
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getTimeDiscretization();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertSame(tenorFromArray, actualTimeDiscretization);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int TermStructureMonteCarloSimulationFromTermStructureModel.getTimeIndex(double)"
  })
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    int actualTimeIndex =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process).getTimeIndex(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(0, actualTimeIndex);
  }

  /**
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationFromTermStructureModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualRandomVariableForConstant =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getRandomVariableForConstant(10.0d);

    // Assert
    verify(brownianMotion).getRandomVariableForConstant(10.0d);
    verify(brownianMotion).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualRandomVariableForConstant);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getForwardRate(double,
   * double, double)} with {@code time}, {@code periodStart}, {@code periodEnd}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getForwardRate(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationFromTermStructureModel.getForwardRate(double, double, double)"
  })
  public void testGetForwardRateWithTimePeriodStartPeriodEnd_thenReturnScalar()
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
    RandomVariable actualForwardRate =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getForwardRate(10.0d, 10.0d, 10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualForwardRate instanceof Scalar);
    assertTrue(actualForwardRate.abs() instanceof Scalar);
    assertTrue(actualForwardRate.cos() instanceof Scalar);
    assertTrue(actualForwardRate.exp() instanceof Scalar);
    assertTrue(actualForwardRate.expm1() instanceof Scalar);
    assertTrue(actualForwardRate.invert() instanceof Scalar);
    assertTrue(actualForwardRate.isNaN() instanceof Scalar);
    assertTrue(actualForwardRate.sin() instanceof Scalar);
    assertTrue(actualForwardRate.sqrt() instanceof Scalar);
    assertTrue(actualForwardRate.squared() instanceof Scalar);
    assertTrue(actualForwardRate.variance() instanceof Scalar);
    assertNull(actualForwardRate.getRealizations());
    assertNull(actualForwardRate.getOperator());
    assertNull(actualForwardRate.getRealizationsStream());
    assertEquals(0, actualForwardRate.getTypePriority());
    assertEquals(0.0d, actualForwardRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardRate.getVariance(), 0.0);
    assertEquals(1, actualForwardRate.size());
    assertTrue(actualForwardRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardRate.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getAverage(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getMax(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualForwardRate.expectation();
    assertSame(actualForwardRate, actualExpectationResult);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getNumberOfComponents()}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int TermStructureMonteCarloSimulationFromTermStructureModel.getNumberOfComponents()"
  })
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getNumberOfComponents();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(2, actualNumberOfComponents);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getNumeraire(double)} with
   * {@code time}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationFromTermStructureModel.getNumeraire(double)"
  })
  public void testGetNumeraireWithTime_thenReturnRandomVariableFromDoubleArray()
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process).getNumeraire(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.getTypePriority());
    assertEquals(1, actualNumeraire.size());
    assertEquals(1.0d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMin(), 0.0);
    assertEquals(10.0d, actualNumeraire.getFiltrationTime(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertArrayEquals(new double[] {1.0d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getNumeraire(double)} with
   * {@code time}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationFromTermStructureModel.getNumeraire(double)"
  })
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process).getNumeraire(10.0d);

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
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object TermStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedSeed(int)"
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
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getCloneWithModifiedSeed(42);

    // Assert
    verify(brownianMotion).getCloneWithModifiedSeed(42);
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(
        ((TermStructureMonteCarloSimulationFromTermStructureModel) actualCloneWithModifiedSeed)
                .getBrownianMotion()
            instanceof BrownianMotionWithControlVariate);
    assertTrue(
        actualCloneWithModifiedSeed
            instanceof TermStructureMonteCarloSimulationFromTermStructureModel);
    assertTrue(
        ((TermStructureMonteCarloSimulationFromTermStructureModel) actualCloneWithModifiedSeed)
                .getProcess()
            instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization2 =
        ((TermStructureMonteCarloSimulationFromTermStructureModel) actualCloneWithModifiedSeed)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(
        ((TermStructureMonteCarloSimulationFromTermStructureModel) actualCloneWithModifiedSeed)
            .getModel());
    assertEquals(
        10,
        ((TermStructureMonteCarloSimulationFromTermStructureModel) actualCloneWithModifiedSeed)
            .getNumberOfPaths());
    assertEquals(
        3,
        ((TermStructureMonteCarloSimulationFromTermStructureModel) actualCloneWithModifiedSeed)
            .getNumberOfFactors());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(Map)} with
   * {@code dataModified}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureMonteCarloSimulationModel TermStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedData(Map)"
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
    TermStructureMonteCarloSimulationFromTermStructureModel
        termStructureMonteCarloSimulationFromTermStructureModel =
            new TermStructureMonteCarloSimulationFromTermStructureModel(process);

    // Act
    TermStructureMonteCarloSimulationModel actualCloneWithModifiedData =
        termStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedData(
            new HashMap<>());

    // Assert
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    TermStructureModel model2 = actualCloneWithModifiedData.getModel();
    ForwardCurve forwardRateCurve2 = model2.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualCloneWithModifiedData
            instanceof TermStructureMonteCarloSimulationFromTermStructureModel);
    assertTrue(model2 instanceof HullWhiteModel);
    ShortRateVolatilityModel volatilityModel2 = ((HullWhiteModel) model2).getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        ((HullWhiteModel) model2).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = volatilityModel2.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TimeDiscretizationFromArray);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(new double[] {0.0d}, timeDiscretization2.getAsDoubleArray(), 0.0);
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
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(Map)} with
   * {@code dataModified}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureMonteCarloSimulationModel TermStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedData(Map)"
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
    TermStructureMonteCarloSimulationFromTermStructureModel
        termStructureMonteCarloSimulationFromTermStructureModel =
            new TermStructureMonteCarloSimulationFromTermStructureModel(process);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("discountCurve", "Data Modified");

    // Act
    TermStructureMonteCarloSimulationModel actualCloneWithModifiedData =
        termStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedData(
            dataModified);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    TermStructureModel model2 = actualCloneWithModifiedData.getModel();
    ForwardCurve forwardRateCurve2 = model2.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualCloneWithModifiedData
            instanceof TermStructureMonteCarloSimulationFromTermStructureModel);
    assertTrue(model2 instanceof HullWhiteModel);
    ShortRateVolatilityModel volatilityModel2 = ((HullWhiteModel) model2).getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        ((HullWhiteModel) model2).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = volatilityModel2.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TimeDiscretizationFromArray);
    assertSame(process, actualCloneWithModifiedData.getProcess());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) forwardRateCurve2).getTimes(), 0.0);
    assertArrayEquals(new double[] {0.0d}, timeDiscretization2.getAsDoubleArray(), 0.0);
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
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(String,
   * Object)} with {@code entityKey}, {@code dataModified}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureMonteCarloSimulationModel TermStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedData(String, Object)"
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
    TermStructureMonteCarloSimulationModel actualCloneWithModifiedData =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getCloneWithModifiedData("Entity Key", "Data Modified");

    // Assert
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    BrownianMotion brownianMotion2 = actualCloneWithModifiedData.getBrownianMotion();
    assertTrue(brownianMotion2 instanceof BrownianMotionWithControlVariate);
    assertTrue(
        actualCloneWithModifiedData
            instanceof TermStructureMonteCarloSimulationFromTermStructureModel);
    MonteCarloProcess process2 = actualCloneWithModifiedData.getProcess();
    assertTrue(process2 instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertSame(brownianMotion2, process2.getStochasticDriver());
    assertSame(timeDiscretization, brownianMotion2.getTimeDiscretization());
    assertSame(timeDiscretization, process2.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(String,
   * Object)} with {@code entityKey}, {@code dataModified}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getCloneWithModifiedData(String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureMonteCarloSimulationModel TermStructureMonteCarloSimulationFromTermStructureModel.getCloneWithModifiedData(String, Object)"
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
    TermStructureMonteCarloSimulationModel actualCloneWithModifiedData =
        new TermStructureMonteCarloSimulationFromTermStructureModel(process)
            .getCloneWithModifiedData("discountCurve", "Data Modified");

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    BrownianMotion brownianMotion2 = actualCloneWithModifiedData.getBrownianMotion();
    assertTrue(brownianMotion2 instanceof BrownianMotionWithControlVariate);
    assertTrue(
        actualCloneWithModifiedData
            instanceof TermStructureMonteCarloSimulationFromTermStructureModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertSame(process, actualCloneWithModifiedData.getProcess());
    assertSame(timeDiscretization, brownianMotion2.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationFromTermStructureModel#getModelParameters()}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationFromTermStructureModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map TermStructureMonteCarloSimulationFromTermStructureModel.getModelParameters()"
  })
  public void testGetModelParameters() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            new TermStructureMonteCarloSimulationFromTermStructureModel(process)
                .getModelParameters());
    verify(brownianMotion).getTimeDiscretization();
  }
}
