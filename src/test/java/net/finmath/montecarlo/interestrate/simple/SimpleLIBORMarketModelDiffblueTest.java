package net.finmath.montecarlo.interestrate.simple;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionView;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCorrelationModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCorrelationModelExponentialDecay;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCorrelationModelThreeParameterExponentialDecay;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelFromVolatilityAndCorrelation;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORVolatilityModelTwoParameterExponentialForm;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel.Driftapproximation;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel.Measure;
import net.finmath.montecarlo.templatemethoddesign.LogNormalProcess;
import net.finmath.montecarlo.templatemethoddesign.LogNormalProcess.Scheme;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SimpleLIBORMarketModelDiffblueTest {
  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, TimeDiscretization, int, double[], LIBORCovarianceModel)"
  })
  public void testNewSimpleLIBORMarketModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

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
    SimpleLIBORMarketModel actualSimpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualSimpleLIBORMarketModel.getBrownianMotion()
            instanceof BrownianMotionFromMersenneRandomNumbers);
    LIBORCovarianceModel covarianceModel3 = actualSimpleLIBORMarketModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization timeDiscretization2 = actualSimpleLIBORMarketModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(actualSimpleLIBORMarketModel.getModel());
    assertNull(actualSimpleLIBORMarketModel.getProcess());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfLibors());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfComponents());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfPaths());
    assertEquals(3, actualSimpleLIBORMarketModel.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualSimpleLIBORMarketModel.getDriftAproximationMethod());
    assertEquals(Measure.SPOT, actualSimpleLIBORMarketModel.getMeasure());
    assertEquals(Scheme.EULER, actualSimpleLIBORMarketModel.getScheme());
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(
        liborPeriodDiscretization, actualSimpleLIBORMarketModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization, double[],
   * LIBORCovarianceModel, BrownianMotion)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * double[], LIBORCovarianceModel, BrownianMotion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, double[], LIBORCovarianceModel, BrownianMotion)"
  })
  public void testNewSimpleLIBORMarketModel2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    // Act
    SimpleLIBORMarketModel actualSimpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    BrownianMotion brownianMotion3 = actualSimpleLIBORMarketModel.getBrownianMotion();
    assertTrue(brownianMotion3 instanceof BrownianMotionWithControlVariate);
    LIBORCovarianceModel covarianceModel3 = actualSimpleLIBORMarketModel.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualSimpleLIBORMarketModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualSimpleLIBORMarketModel.getModel());
    assertNull(actualSimpleLIBORMarketModel.getProcess());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfLibors());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfComponents());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfPaths());
    assertEquals(3, actualSimpleLIBORMarketModel.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualSimpleLIBORMarketModel.getDriftAproximationMethod());
    assertEquals(Measure.SPOT, actualSimpleLIBORMarketModel.getMeasure());
    assertEquals(Scheme.EULER, actualSimpleLIBORMarketModel.getScheme());
    assertSame(brownianMotion2, brownianMotion3);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
    assertSame(timeDiscretization, actualSimpleLIBORMarketModel.getTimeDiscretization());
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization, double[],
   * LIBORCovarianceModel, BrownianMotion)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * double[], LIBORCovarianceModel, BrownianMotion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, double[], LIBORCovarianceModel, BrownianMotion)"
  })
  public void testNewSimpleLIBORMarketModel3() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            new SimpleLIBORMarketModel(
                liborPeriodDiscretization,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                covarianceModel2,
                new BrownianMotionWithControlVariate(brownianMotion2)));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel)}.
   *
   * <ul>
   *   <li>Then return NumberOfLibors is one.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, TimeDiscretization, int, double[], LIBORVolatilityModel)"
  })
  public void testNewSimpleLIBORMarketModel_thenReturnNumberOfLiborsIsOne() {
    // Arrange
    TenorFromArray timeDiscretizationFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {-0.3162277660168379d, -1.0d, -0.3162277660168379d, -1.0d});
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act
    SimpleLIBORMarketModel actualSimpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel);

    // Assert
    assertEquals(1, actualSimpleLIBORMarketModel.getNumberOfLibors());
    assertEquals(1, actualSimpleLIBORMarketModel.getNumberOfComponents());
    assertEquals(1, actualSimpleLIBORMarketModel.getInitialValue().length);
    assertSame(
        liborPeriodDiscretization, actualSimpleLIBORMarketModel.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel)}.
   *
   * <ul>
   *   <li>Then return NumberOfLibors is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, TimeDiscretization, int, double[], LIBORVolatilityModel)"
  })
  public void testNewSimpleLIBORMarketModel_thenReturnNumberOfLiborsIsTen() {
    // Arrange
    TenorFromArray timeDiscretizationFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act
    SimpleLIBORMarketModel actualSimpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel);

    // Assert
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfLibors());
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfComponents());
    assertSame(
        liborPeriodDiscretization, actualSimpleLIBORMarketModel.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel, LIBORCorrelationModel)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel, LIBORCorrelationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, TimeDiscretization, int, double[], LIBORVolatilityModel, LIBORCorrelationModel)"
  })
  public void testNewSimpleLIBORMarketModel_whenOne_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    TenorFromArray timeDiscretizationFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 1, 10.0d, 10.0d, 10.0d, true);

    // Act
    SimpleLIBORMarketModel actualSimpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            1,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel);

    // Assert
    assertTrue(
        actualSimpleLIBORMarketModel.getBrownianMotion()
            instanceof BrownianMotionFromMersenneRandomNumbers);
    assertTrue(
        actualSimpleLIBORMarketModel.getCovarianceModel()
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization2 =
        actualSimpleLIBORMarketModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization3 = actualSimpleLIBORMarketModel.getTimeDiscretization();
    assertTrue(timeDiscretization3 instanceof TenorFromArray);
    assertEquals(1, actualSimpleLIBORMarketModel.getNumberOfFactors());
    assertEquals(1, actualSimpleLIBORMarketModel.getNumberOfPaths());
    assertEquals(timeDiscretization3, liborPeriodDiscretization2);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization, double[],
   * LIBORCovarianceModel, BrownianMotion)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * double[], LIBORCovarianceModel, BrownianMotion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, double[], LIBORCovarianceModel, BrownianMotion)"
  })
  public void testNewSimpleLIBORMarketModel_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray start = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            new SimpleLIBORMarketModel(
                liborPeriodDiscretization,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                covarianceModel2,
                new BrownianMotionWithControlVariate(
                    new BrownianBridge(
                        timeDiscretization,
                        10,
                        42,
                        start,
                        new RandomVariableFromDoubleArray(10.0d)))));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel, LIBORCorrelationModel)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#SimpleLIBORMarketModel(TimeDiscretization,
   * TimeDiscretization, int, double[], LIBORVolatilityModel, LIBORCorrelationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleLIBORMarketModel.<init>(TimeDiscretization, TimeDiscretization, int, double[], LIBORVolatilityModel, LIBORCorrelationModel)"
  })
  public void testNewSimpleLIBORMarketModel_whenTen_thenReturnNumberOfPathsIsTen() {
    // Arrange
    TenorFromArray timeDiscretizationFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act
    SimpleLIBORMarketModel actualSimpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretizationFromArray,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            volatilityModel,
            correlationModel);

    // Assert
    assertTrue(
        actualSimpleLIBORMarketModel.getBrownianMotion()
            instanceof BrownianMotionFromMersenneRandomNumbers);
    assertTrue(
        actualSimpleLIBORMarketModel.getCovarianceModel()
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization2 =
        actualSimpleLIBORMarketModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization3 = actualSimpleLIBORMarketModel.getTimeDiscretization();
    assertTrue(timeDiscretization3 instanceof TenorFromArray);
    assertEquals(10, actualSimpleLIBORMarketModel.getNumberOfPaths());
    assertEquals(3, actualSimpleLIBORMarketModel.getNumberOfFactors());
    assertEquals(timeDiscretization3, liborPeriodDiscretization2);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getInitialValue(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getInitialValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableFromDoubleArray SimpleLIBORMarketModel.getInitialValue(int)"})
  public void testGetInitialValueWithInt_thenAbsReturnRandomVariableFromDoubleArray() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariableFromDoubleArray actualInitialValue = simpleLIBORMarketModel.getInitialValue(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualInitialValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualInitialValue.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualInitialValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualInitialValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualInitialValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualInitialValue.getVariance(), 0.0);
    assertEquals(1, actualInitialValue.getTypePriority());
    assertEquals(1, actualInitialValue.size());
    assertEquals(1.0d, actualInitialValue.getAverage(), 0.0);
    assertEquals(1.0d, actualInitialValue.getMax(), 0.0);
    assertEquals(1.0d, actualInitialValue.getMin(), 0.0);
    assertTrue(actualInitialValue.isDeterministic());
    assertArrayEquals(new double[] {1.0d}, actualInitialValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getInitialValue()}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getInitialValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] SimpleLIBORMarketModel.getInitialValue()"})
  public void testGetInitialValue_thenFirstElementReturnRandomVariableFromDoubleArray() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialValue = simpleLIBORMarketModel.getInitialValue();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualInitialValue[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[6] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[7] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[8] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialValue[9] instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualInitialValue.length);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getFactorLoading(int, int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)}
   *       with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getFactorLoading(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModel.getFactorLoading(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenReturnRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {randomVariableFromDoubleArray});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualFactorLoading =
        simpleLIBORMarketModel.getFactorLoading(
            1, 0, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualFactorLoading);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code componentIndex}, {@code realizationAtTimeIndex}, {@code
   * realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModel.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentIndexRealizationAtTimeIndexRealizationPredictor() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new RuntimeException());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            simpleLIBORMarketModel.getDrift(
                1,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code componentIndex}, {@code realizationAtTimeIndex}, {@code
   * realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModel.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentIndexRealizationAtTimeIndexRealizationPredictor2() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable actualDrift =
        simpleLIBORMarketModel.getDrift(
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTime(1);
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
    assertEquals(10.0d, actualDrift.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualDrift.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code componentIndex}, {@code realizationAtTimeIndex}, {@code
   * realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModel.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentIndexRealizationAtTimeIndexRealizationPredictor3() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            covarianceModel2);

    // Act
    RandomVariable actualDrift =
        simpleLIBORMarketModel.getDrift(
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
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
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualDrift.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code componentIndex}, {@code realizationAtTimeIndex}, {@code
   * realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleLIBORMarketModel.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentIndexRealizationAtTimeIndexRealizationPredictor4() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            covarianceModel2);

    // Act
    RandomVariable actualDrift =
        simpleLIBORMarketModel.getDrift(
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
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
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualDrift.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code realizationAtTimeIndex}, {@code realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] SimpleLIBORMarketModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexRealizationAtTimeIndexRealizationPredictor() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new RuntimeException());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            simpleLIBORMarketModel.getDrift(
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code realizationAtTimeIndex}, {@code realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] SimpleLIBORMarketModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexRealizationAtTimeIndexRealizationPredictor2() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        simpleLIBORMarketModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualDrift.length);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code realizationAtTimeIndex}, {@code realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] SimpleLIBORMarketModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexRealizationAtTimeIndexRealizationPredictor3() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable[] actualDrift =
        simpleLIBORMarketModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
    assertNull(actualDrift[0]);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[], RandomVariable[])} with
   * {@code timeIndex}, {@code realizationAtTimeIndex}, {@code realizationPredictor}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] SimpleLIBORMarketModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexRealizationAtTimeIndexRealizationPredictor4() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 10.5d, 10.0d, 10.5d},
            covarianceModel2);

    // Act
    RandomVariable[] actualDrift =
        simpleLIBORMarketModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertNull(actualDrift[0]);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SimpleLIBORMarketModel.getLMMTerminasureDriftEuler(int, int, RandomVariable[])"
  })
  public void testGetLMMTerminasureDriftEuler() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    double[] actualLMMTerminasureDriftEuler =
        simpleLIBORMarketModel.getLMMTerminasureDriftEuler(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualLMMTerminasureDriftEuler,
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SimpleLIBORMarketModel.getLMMTerminasureDriftEuler(int, int, RandomVariable[])"
  })
  public void testGetLMMTerminasureDriftEuler2() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            liborPeriodDiscretization,
            10,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            covarianceModel2);

    // Act
    double[] actualLMMTerminasureDriftEuler =
        simpleLIBORMarketModel.getLMMTerminasureDriftEuler(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualLMMTerminasureDriftEuler,
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SimpleLIBORMarketModel.getLMMTerminasureDriftEuler(int, int, RandomVariable[])"
  })
  public void testGetLMMTerminasureDriftEuler_thenReturnArrayOfDoubleWithZeroAndZero() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            covarianceModel2);

    // Act
    double[] actualLMMTerminasureDriftEuler =
        simpleLIBORMarketModel.getLMMTerminasureDriftEuler(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualLMMTerminasureDriftEuler,
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getLMMTerminasureDriftEuler(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SimpleLIBORMarketModel.getLMMTerminasureDriftEuler(int, int, RandomVariable[])"
  })
  public void testGetLMMTerminasureDriftEuler_thenThrowRuntimeException() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new RuntimeException());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            simpleLIBORMarketModel.getLMMTerminasureDriftEuler(
                1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
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
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex2() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
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
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1.5}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnAverageIs15() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(1.0d, 10, 0.5d),
            10,
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(1.5d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.5d, actualNumeraire.getFiltrationTime(), 0.0);
    assertEquals(1.5d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.5d, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.5d, 1.5d, 1.5d, 1.5d, 1.5d, 1.5d, 1.5d, 1.5d, 1.5d, 1.5d},
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnFiltrationTimeIsNaN() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray timeDiscretization =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(1.0d, 10, 0.5d),
            10,
            new double[] {1.0d, Double.NaN, 1.0d, Double.NaN},
            covarianceModel2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(1);

    // Assert
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
    assertEquals(Double.NaN, actualNumeraire.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SimpleLIBORMarketModel SimpleLIBORMarketModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> simpleLIBORMarketModel.getCloneWithModifiedData(new HashMap<>()));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SimpleLIBORMarketModel.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    Object actualCloneWithModifiedSeed = simpleLIBORMarketModel.getCloneWithModifiedSeed(42);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    BrownianMotion brownianMotion2 =
        ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getBrownianMotion();
    assertTrue(brownianMotion2 instanceof BrownianMotionFromMersenneRandomNumbers);
    LIBORCovarianceModel covarianceModel3 =
        ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedSeed instanceof SimpleLIBORMarketModel);
    TimeDiscretization liborPeriodDiscretization2 =
        ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getModel());
    assertNull(((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getProcess());
    assertEquals(10, ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getNumberOfLibors());
    assertEquals(
        10, ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getNumberOfComponents());
    assertEquals(10, ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getNumberOfPaths());
    assertEquals(3, ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getDriftAproximationMethod());
    assertEquals(Measure.SPOT, ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getMeasure());
    assertEquals(Scheme.EULER, ((SimpleLIBORMarketModel) actualCloneWithModifiedSeed).getScheme());
    assertEquals(brownianMotion, brownianMotion2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getModel()}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LIBORMarketModelFromCovarianceModel SimpleLIBORMarketModel.getModel()"})
  public void testGetModel() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    LIBORMarketModelFromCovarianceModel actualModel = simpleLIBORMarketModel.getModel();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertNull(actualModel);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualRandomVariableForConstant =
        simpleLIBORMarketModel.getRandomVariableForConstant(10.0d);

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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant2() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualRandomVariableForConstant =
        simpleLIBORMarketModel.getRandomVariableForConstant(10.0d);

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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant3() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADPathwiseFactory());

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualRandomVariableForConstant =
        simpleLIBORMarketModel.getRandomVariableForConstant(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualRandomVariableForConstant instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableForConstant.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableForConstant.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableForConstant.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
            .getGradient()
            .size());
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertEquals(3, actualRandomVariableForConstant.getTypePriority());
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertSame(randomVariable, actualRandomVariableForConstant.getValues());
    assertArrayEquals(new double[] {10.0d}, actualRandomVariableForConstant.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SimpleLIBORMarketModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnRandomVariableFromFloatArray() {
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualRandomVariableForConstant =
        simpleLIBORMarketModel.getRandomVariableForConstant(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
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
   * Test {@link SimpleLIBORMarketModel#getModelParameters()}.
   *
   * <p>Method under test: {@link SimpleLIBORMarketModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SimpleLIBORMarketModel.getModelParameters()"})
  public void testGetModelParameters() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> simpleLIBORMarketModel.getModelParameters());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }
}
