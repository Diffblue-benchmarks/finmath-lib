package net.finmath.montecarlo.interestrate.simple;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCorrelationModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCorrelationModelExponentialDecay;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCorrelationModelThreeParameterExponentialDecay;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelFromVolatilityAndCorrelation;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORVolatilityModelFourParameterExponentialForm;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORVolatilityModelTwoParameterExponentialForm;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SimpleLIBORMarketModelWithWMCDiffblueTest {
  /**
   * Test {@link
   * SimpleLIBORMarketModelWithWMC#SimpleLIBORMarketModelWithWMC(TimeDiscretizationFromArray,
   * TimeDiscretizationFromArray, int, double[], LIBORVolatilityModel, LIBORCorrelationModel,
   * SimpleLIBORMarketModel)}.
   *
   * <p>Method under test: {@link
   * SimpleLIBORMarketModelWithWMC#SimpleLIBORMarketModelWithWMC(TimeDiscretizationFromArray,
   * TimeDiscretizationFromArray, int, double[], LIBORVolatilityModel, LIBORCorrelationModel,
   * SimpleLIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModelWithWMC.<init>(TimeDiscretizationFromArray, TimeDiscretizationFromArray, int, double[], LIBORVolatilityModel, LIBORCorrelationModel, SimpleLIBORMarketModel)"
  })
  public void testNewSimpleLIBORMarketModelWithWMC() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization2,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    SimpleLIBORMarketModelWithWMC actualSimpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    LIBORCovarianceModel covarianceModel3 =
        actualSimpleLIBORMarketModelWithWMC.getCovarianceModel();
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) covarianceModel3).getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(covarianceModel3 instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization3 =
        actualSimpleLIBORMarketModelWithWMC.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TimeDiscretizationFromArray);
    TimeDiscretization timeDiscretization3 =
        actualSimpleLIBORMarketModelWithWMC.getTimeDiscretization();
    assertTrue(timeDiscretization3 instanceof TimeDiscretizationFromArray);
    assertEquals(
        2,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) covarianceModel3)
            .getParameter()
            .length);
    assertEquals(timeDiscretization3, liborPeriodDiscretization3);
    assertSame(correlationModel, correlationModel2);
    assertSame(liborPeriodDiscretization3, covarianceModel3.getLiborPeriodDiscretization());
    assertSame(timeDiscretization3, covarianceModel3.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) covarianceModel3)
            .getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization3.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * SimpleLIBORMarketModelWithWMC#SimpleLIBORMarketModelWithWMC(TimeDiscretizationFromArray,
   * TimeDiscretizationFromArray, int, double[], LIBORVolatilityModel, LIBORCorrelationModel,
   * SimpleLIBORMarketModel)}.
   *
   * <p>Method under test: {@link
   * SimpleLIBORMarketModelWithWMC#SimpleLIBORMarketModelWithWMC(TimeDiscretizationFromArray,
   * TimeDiscretizationFromArray, int, double[], LIBORVolatilityModel, LIBORCorrelationModel,
   * SimpleLIBORMarketModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModelWithWMC.<init>(TimeDiscretizationFromArray, TimeDiscretizationFromArray, int, double[], LIBORVolatilityModel, LIBORCorrelationModel, SimpleLIBORMarketModel)"
  })
  public void testNewSimpleLIBORMarketModelWithWMC2() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization2,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    SimpleLIBORMarketModelWithWMC actualSimpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    LIBORCovarianceModel covarianceModel3 =
        actualSimpleLIBORMarketModelWithWMC.getCovarianceModel();
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) covarianceModel3).getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    assertTrue(covarianceModel3 instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization3 =
        actualSimpleLIBORMarketModelWithWMC.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TimeDiscretizationFromArray);
    TimeDiscretization timeDiscretization3 =
        actualSimpleLIBORMarketModelWithWMC.getTimeDiscretization();
    assertTrue(timeDiscretization3 instanceof TimeDiscretizationFromArray);
    assertEquals(
        5,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) covarianceModel3)
            .getParameter()
            .length);
    assertEquals(timeDiscretization3, liborPeriodDiscretization3);
    assertSame(correlationModel, correlationModel2);
    assertSame(liborPeriodDiscretization3, covarianceModel3.getLiborPeriodDiscretization());
    assertSame(timeDiscretization3, covarianceModel3.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) covarianceModel3)
            .getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization3.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getDrift(int, int, RandomVariable[],
   * RandomVariable[])} with {@code timeIndex}, {@code component}, {@code realizationAtTimeIndex},
   * {@code realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModelWithWMC.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentRealizationAtTimeIndexRealizationPredictor() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualDrift =
        simpleLIBORMarketModelWithWMC.getDrift(
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualDrift instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(10.5d, actualDrift.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualDrift.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getDrift(int, int, RandomVariable[],
   * RandomVariable[])} with {@code timeIndex}, {@code component}, {@code realizationAtTimeIndex},
   * {@code realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModelWithWMC.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentRealizationAtTimeIndexRealizationPredictor2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TenorFromArray timeDiscretizationFromArray =
        new TenorFromArray(new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualDrift =
        simpleLIBORMarketModelWithWMC.getDrift(
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualDrift instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualDrift.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualDrift.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(Scalar.of(1.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(1.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex4() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization2,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex5() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization2,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TimeDiscretizationFromArray(1.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(0.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray a = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray b = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray c = new RandomVariableFromDoubleArray(10.0d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            timeDiscretization,
            liborPeriodDiscretization2,
            a,
            b,
            c,
            new RandomVariableFromDoubleArray(10.0d),
            true);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD, atLeast(1)).getAverage();
    verify(randomVariableAAD, atLeast(1)).getStandardDeviation();
    verify(randomVariableAAD, atLeast(1)).sub(10.0d);
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then calls {@link BrownianMotion#getBrownianIncrement(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex_thenCallsGetBrownianIncrement() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#mult(double)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex_thenCallsMult() {
    // Arrange
    RandomVariableAAD b = mock(RandomVariableAAD.class);
    when(b.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    Scalar a = Scalar.of(1.0d);
    Scalar c = Scalar.of(10.0d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            timeDiscretization,
            liborPeriodDiscretization,
            a,
            b,
            c,
            new RandomVariableFromDoubleArray(10.0d),
            true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TimeDiscretizationFromArray(1.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(0.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization2,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD, atLeast(1)).getAverage();
    verify(randomVariableAAD, atLeast(1)).getStandardDeviation();
    verify(b).mult(0.5d);
    verify(randomVariableAAD, atLeast(1)).sub(10.0d);
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.1d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModelWithWMC#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModelWithWMC.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex_thenReturnAverageIsZero() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel targetScheme =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(0.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    SimpleLIBORMarketModelWithWMC simpleLIBORMarketModelWithWMC =
        new SimpleLIBORMarketModelWithWMC(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel,
            targetScheme);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModelWithWMC.getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(covarianceModel, atLeast(1)).getFactorLoading(eq(0), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualMonteCarloWeights.getAverage(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getMax(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualMonteCarloWeights.getRealizations(),
        0.0);
  }
}
