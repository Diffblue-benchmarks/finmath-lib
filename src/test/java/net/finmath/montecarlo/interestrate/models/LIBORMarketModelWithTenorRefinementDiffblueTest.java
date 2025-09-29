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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.CalibrationProduct;
import net.finmath.montecarlo.interestrate.TermStructureModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.TermStructCovarianceModelFromLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.TermStructureCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.TermStructureTenorTimeScalingPicewiseConstant;
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
public class LIBORMarketModelWithTenorRefinementDiffblueTest {
  @Mock private AnalyticModel analyticModel;

  @Mock private DiscountCurve discountCurve;

  @Mock private ForwardCurve forwardCurve;

  @InjectMocks private LIBORMarketModelWithTenorRefinement lIBORMarketModelWithTenorRefinement;

  @Mock private MonteCarloProcess monteCarloProcess;

  @Mock private TermStructureCovarianceModel termStructureCovarianceModel;

  /**
   * Test {@link
   * LIBORMarketModelWithTenorRefinement#LIBORMarketModelWithTenorRefinement(TimeDiscretization[],
   * Integer[], AnalyticModel, ForwardCurve, DiscountCurve, TermStructureCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#LIBORMarketModelWithTenorRefinement(TimeDiscretization[],
   * Integer[], AnalyticModel, ForwardCurve, DiscountCurve, TermStructureCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelWithTenorRefinement.<init>(TimeDiscretization[], Integer[], AnalyticModel, ForwardCurve, DiscountCurve, TermStructureCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelWithTenorRefinement() throws CalculationException {
    // Arrange
    TimeDiscretization[] liborPeriodDiscretizations =
        new TimeDiscretization[] {new TenorFromArray(10.0d, 10, 0.5d)};
    Integer[] numberOfDiscretizationIntervals = new Integer[] {10};

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TermStructureTenorTimeScalingPicewiseConstant tenorTimeScalingModel =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    TermStructCovarianceModelFromLIBORCovarianceModelParametric covarianceModel3 =
        new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
            tenorTimeScalingModel, covarianceModel2);

    // Act
    LIBORMarketModelWithTenorRefinement actualLiborMarketModelWithTenorRefinement =
        new LIBORMarketModelWithTenorRefinement(
            liborPeriodDiscretizations,
            numberOfDiscretizationIntervals,
            analyticModel,
            forwardCurve,
            discountCurve,
            covarianceModel3,
            null,
            null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(3, actualLiborMarketModelWithTenorRefinement.getNumberOfFactors());
    assertSame(covarianceModel3, actualLiborMarketModelWithTenorRefinement.getCovarianceModel());
    assertSame(analyticModel, actualLiborMarketModelWithTenorRefinement.getAnalyticModel());
    assertSame(discountCurve, actualLiborMarketModelWithTenorRefinement.getDiscountCurve());
    assertSame(forwardCurve, actualLiborMarketModelWithTenorRefinement.getForwardRateCurve());
  }

  /**
   * Test {@link
   * LIBORMarketModelWithTenorRefinement#LIBORMarketModelWithTenorRefinement(TimeDiscretization[],
   * Integer[], AnalyticModel, ForwardCurve, DiscountCurve, TermStructureCovarianceModel,
   * CalibrationProduct[], Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#LIBORMarketModelWithTenorRefinement(TimeDiscretization[],
   * Integer[], AnalyticModel, ForwardCurve, DiscountCurve, TermStructureCovarianceModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelWithTenorRefinement.<init>(TimeDiscretization[], Integer[], AnalyticModel, ForwardCurve, DiscountCurve, TermStructureCovarianceModel, CalibrationProduct[], Map)"
  })
  public void testNewLIBORMarketModelWithTenorRefinement_whenHashMap() throws CalculationException {
    // Arrange
    TimeDiscretization[] liborPeriodDiscretizations =
        new TimeDiscretization[] {new TenorFromArray(10.0d, 10, 0.5d)};
    Integer[] numberOfDiscretizationIntervals = new Integer[] {10};

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TermStructureTenorTimeScalingPicewiseConstant tenorTimeScalingModel =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    TermStructCovarianceModelFromLIBORCovarianceModelParametric covarianceModel3 =
        new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
            tenorTimeScalingModel, covarianceModel2);

    // Act
    LIBORMarketModelWithTenorRefinement actualLiborMarketModelWithTenorRefinement =
        new LIBORMarketModelWithTenorRefinement(
            liborPeriodDiscretizations,
            numberOfDiscretizationIntervals,
            analyticModel,
            forwardCurve,
            discountCurve,
            covarianceModel3,
            null,
            new HashMap<>());

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(3, actualLiborMarketModelWithTenorRefinement.getNumberOfFactors());
    assertSame(covarianceModel3, actualLiborMarketModelWithTenorRefinement.getCovarianceModel());
    assertSame(analyticModel, actualLiborMarketModelWithTenorRefinement.getAnalyticModel());
    assertSame(discountCurve, actualLiborMarketModelWithTenorRefinement.getDiscountCurve());
    assertSame(forwardCurve, actualLiborMarketModelWithTenorRefinement.getForwardRateCurve());
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelWithTenorRefinement.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenThrowIllegalArgumentException() {
    // Arrange
    when(monteCarloProcess.getStochasticDriver()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getFactorLoading(
                monteCarloProcess,
                1,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(monteCarloProcess).getStochasticDriver();
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        lIBORMarketModelWithTenorRefinement.applyStateSpaceTransform(
            monteCarloProcess, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformResult);
  }

  /**
   * Test {@link
   * LIBORMarketModelWithTenorRefinement#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        lIBORMarketModelWithTenorRefinement.applyStateSpaceTransformInverse(
            monteCarloProcess, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformInverseResult);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        lIBORMarketModelWithTenorRefinement.getRandomVariableForConstant(10.0d);

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
   * Test {@link LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetStateVariableForPeriod() {
    // Arrange
    when(termStructureCovarianceModel.getScaledTenorTime(anyDouble(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(
                liborPeriodDiscretization,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                10.0d,
                10.0d));
    verify(termStructureCovarianceModel).getScaledTenorTime(10.0d, 10.0d);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetStateVariableForPeriod2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(0.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(
                liborPeriodDiscretization,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                10.0d,
                10.0d));
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetStateVariableForPeriod_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    when(termStructureCovarianceModel.getScaledTenorTime(anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act
    RandomVariable actualStateVariableForPeriod =
        lIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(
            liborPeriodDiscretization,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            0.0d,
            10.0d);

    // Assert
    verify(termStructureCovarianceModel, atLeast(1)).getScaledTenorTime(0.0d, 10.0d);
    assertTrue(actualStateVariableForPeriod instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualStateVariableForPeriod.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualStateVariableForPeriod.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getStandardError(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getVariance(), 0.0);
    assertEquals(1, actualStateVariableForPeriod.getTypePriority());
    assertEquals(1, actualStateVariableForPeriod.size());
    assertEquals(10.0d, actualStateVariableForPeriod.getAverage(), 0.0);
    assertEquals(10.0d, actualStateVariableForPeriod.getMax(), 0.0);
    assertEquals(10.0d, actualStateVariableForPeriod.getMin(), 0.0);
    assertTrue(actualStateVariableForPeriod.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualStateVariableForPeriod.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualStateVariableForPeriod.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getStateVariableForPeriod(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetStateVariableForPeriod_thenReturnScalar() {
    // Arrange
    when(termStructureCovarianceModel.getScaledTenorTime(anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    RandomVariable actualStateVariableForPeriod =
        lIBORMarketModelWithTenorRefinement.getStateVariableForPeriod(
            liborPeriodDiscretization,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            10.0d,
            10.0d);

    // Assert
    verify(termStructureCovarianceModel).getScaledTenorTime(10.0d, 10.0d);
    assertTrue(actualStateVariableForPeriod instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.abs() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.cos() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.exp() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.expm1() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.invert() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.isNaN() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.sin() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.sqrt() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.squared() instanceof Scalar);
    assertTrue(actualStateVariableForPeriod.variance() instanceof Scalar);
    assertNull(actualStateVariableForPeriod.getRealizations());
    assertNull(actualStateVariableForPeriod.getOperator());
    assertNull(actualStateVariableForPeriod.getRealizationsStream());
    assertEquals(0, actualStateVariableForPeriod.getTypePriority());
    assertEquals(0.0d, actualStateVariableForPeriod.getAverage(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getMax(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getMin(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getStandardError(), 0.0);
    assertEquals(0.0d, actualStateVariableForPeriod.getVariance(), 0.0);
    assertEquals(1, actualStateVariableForPeriod.size());
    assertTrue(actualStateVariableForPeriod.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualStateVariableForPeriod.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualStateVariableForPeriod.expectation();
    assertSame(actualStateVariableForPeriod, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetLIBORForStateVariable() {
    // Arrange
    when(termStructureCovarianceModel.getScaledTenorTime(anyDouble(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(
                liborPeriodDiscretization,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                10.0d,
                10.0d));
    verify(termStructureCovarianceModel).getScaledTenorTime(10.0d, 10.0d);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetLIBORForStateVariable2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(
                liborPeriodDiscretization,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                10.0d,
                10.0d));
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetLIBORForStateVariable3() {
    // Arrange
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    when(termStructureCovarianceModel.getScaledTenorTime(anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(
                liborPeriodDiscretization,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                10.0d,
                10.0d));
    verify(forwardCurve).getForward(isNull(), eq(10.0d));
    verify(termStructureCovarianceModel).getScaledTenorTime(10.0d, 10.0d);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getLIBORForStateVariable(TimeDiscretization,
   * RandomVariable[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(TimeDiscretization, RandomVariable[], double, double)"
  })
  public void testGetLIBORForStateVariable_thenReturnScalar() {
    // Arrange
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(termStructureCovarianceModel.getScaledTenorTime(anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    RandomVariable actualLIBORForStateVariable =
        lIBORMarketModelWithTenorRefinement.getLIBORForStateVariable(
            liborPeriodDiscretization,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            10.0d,
            10.0d);

    // Assert
    verify(forwardCurve).getForward(isNull(), eq(10.0d));
    verify(termStructureCovarianceModel).getScaledTenorTime(10.0d, 10.0d);
    assertTrue(actualLIBORForStateVariable instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.abs() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.cos() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.exp() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.expm1() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.invert() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.isNaN() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.sin() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.sqrt() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.squared() instanceof Scalar);
    assertTrue(actualLIBORForStateVariable.variance() instanceof Scalar);
    assertNull(actualLIBORForStateVariable.getRealizations());
    assertNull(actualLIBORForStateVariable.getOperator());
    assertNull(actualLIBORForStateVariable.getRealizationsStream());
    assertEquals(0, actualLIBORForStateVariable.getTypePriority());
    assertEquals(0.0d, actualLIBORForStateVariable.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLIBORForStateVariable.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLIBORForStateVariable.getStandardError(), 0.0);
    assertEquals(0.0d, actualLIBORForStateVariable.getVariance(), 0.0);
    assertEquals(1, actualLIBORForStateVariable.size());
    assertTrue(actualLIBORForStateVariable.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLIBORForStateVariable.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualLIBORForStateVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, actualLIBORForStateVariable.getMax(), 0.0);
    assertEquals(Double.NaN, actualLIBORForStateVariable.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualLIBORForStateVariable.expectation();
    assertSame(actualLIBORForStateVariable, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getStateVariable(MonteCarloProcess, int,
   * double, double)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelWithTenorRefinement#getStateVariable(MonteCarloProcess, int, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getStateVariable(MonteCarloProcess, int, double, double)"
  })
  public void testGetStateVariable_thenThrowIllegalArgumentException() {
    // Arrange
    when(monteCarloProcess.getTimeDiscretization()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            lIBORMarketModelWithTenorRefinement.getStateVariable(
                monteCarloProcess, 1, 10.0d, 10.0d));
    verify(monteCarloProcess).getTimeDiscretization();
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getLIBOR(MonteCarloProcess, int, double,
   * double)} with {@code process}, {@code timeIndex}, {@code periodStart}, {@code periodEnd}.
   *
   * <p>Method under test: {@link LIBORMarketModelWithTenorRefinement#getLIBOR(MonteCarloProcess,
   * int, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelWithTenorRefinement.getLIBOR(MonteCarloProcess, int, double, double)"
  })
  public void testGetLIBORWithProcessTimeIndexPeriodStartPeriodEnd() {
    // Arrange
    when(monteCarloProcess.getTimeDiscretization()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> lIBORMarketModelWithTenorRefinement.getLIBOR(monteCarloProcess, 1, 10.0d, 10.0d));
    verify(monteCarloProcess).getTimeDiscretization();
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelWithTenorRefinement#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelWithTenorRefinement.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenReturnThree() {
    // Arrange
    when(termStructureCovarianceModel.getNumberOfFactors()).thenReturn(3);

    // Act
    int actualNumberOfFactors = lIBORMarketModelWithTenorRefinement.getNumberOfFactors();

    // Assert
    verify(termStructureCovarianceModel).getNumberOfFactors();
    assertEquals(3, actualNumberOfFactors);
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelWithTenorRefinement#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelWithTenorRefinement.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenThrowIllegalArgumentException() {
    // Arrange
    when(termStructureCovarianceModel.getNumberOfFactors())
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> lIBORMarketModelWithTenorRefinement.getNumberOfFactors());
    verify(termStructureCovarianceModel).getNumberOfFactors();
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#clone()}.
   *
   * <p>Method under test: {@link LIBORMarketModelWithTenorRefinement#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORMarketModelWithTenorRefinement.clone()"})
  public void testClone() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> lIBORMarketModelWithTenorRefinement.clone());
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Given {@code covarianceModel}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelWithTenorRefinement#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureModel LIBORMarketModelWithTenorRefinement.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_givenCovarianceModel() throws CalculationException {
    // Arrange
    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("covarianceModel", termStructureCovarianceModel);
    dataModified.put("foo", "Data Modified");

    // Act
    TermStructureModel actualCloneWithModifiedData =
        lIBORMarketModelWithTenorRefinement.getCloneWithModifiedData(dataModified);

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORMarketModelWithTenorRefinement);
    assertEquals(0, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link LIBORMarketModelWithTenorRefinement#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelWithTenorRefinement#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureModel LIBORMarketModelWithTenorRefinement.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() throws CalculationException {
    // Arrange and Act
    TermStructureModel actualCloneWithModifiedData =
        lIBORMarketModelWithTenorRefinement.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORMarketModelWithTenorRefinement);
    assertEquals(0, actualCloneWithModifiedData.getNumberOfFactors());
  }
}
