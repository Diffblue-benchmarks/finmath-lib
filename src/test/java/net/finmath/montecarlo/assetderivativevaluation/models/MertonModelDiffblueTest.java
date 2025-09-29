package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.modelling.descriptor.MertonModelDescriptor;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.model.ProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MertonModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return RiskFreeRate is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MertonModel#MertonModel(RandomVariable, DiscountCurve, RandomVariable,
   *       DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)}
   *   <li>{@link MertonModel#getJumpIntensity()}
   *   <li>{@link MertonModel#getJumpSizeMean()}
   *   <li>{@link MertonModel#getJumpSizeStdDev()}
   *   <li>{@link MertonModel#getNumberOfComponents()}
   *   <li>{@link MertonModel#getNumberOfFactors()}
   *   <li>{@link MertonModel#getRiskFreeRate()}
   *   <li>{@link MertonModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(RandomVariable, DiscountCurve, RandomVariable, DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)",
    "void MertonModel.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)",
    "RandomVariable MertonModel.getJumpIntensity()",
    "RandomVariable MertonModel.getJumpSizeMean()",
    "RandomVariable MertonModel.getJumpSizeStdDev()",
    "int MertonModel.getNumberOfComponents()",
    "int MertonModel.getNumberOfFactors()",
    "RandomVariable MertonModel.getRiskFreeRate()",
    "RandomVariable MertonModel.getVolatility()"
  })
  public void testGettersAndSetters_thenReturnRiskFreeRateIsNull() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFromDoubleArray jumpIntensity = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray jumpSizeMean = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray jumpSizeStDev = new RandomVariableFromDoubleArray(10.0d);

    // Act
    MertonModel actualMertonModel =
        new MertonModel(
            initialValue,
            discountCurveForForwardRate,
            volatility,
            discountCurveForDiscountRate,
            jumpIntensity,
            jumpSizeMean,
            jumpSizeStDev,
            new RandomVariableFloatFactory());
    RandomVariable actualJumpIntensity = actualMertonModel.getJumpIntensity();
    RandomVariable actualJumpSizeMean = actualMertonModel.getJumpSizeMean();
    RandomVariable actualJumpSizeStdDev = actualMertonModel.getJumpSizeStdDev();
    int actualNumberOfComponents = actualMertonModel.getNumberOfComponents();
    int actualNumberOfFactors = actualMertonModel.getNumberOfFactors();
    RandomVariable actualRiskFreeRate = actualMertonModel.getRiskFreeRate();
    RandomVariable actualVolatility = actualMertonModel.getVolatility();

    // Assert
    assertTrue(actualJumpSizeMean instanceof RandomVariableFromDoubleArray);
    assertTrue(actualJumpSizeStdDev instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVolatility instanceof RandomVariableFromDoubleArray);
    assertNull(actualRiskFreeRate);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
    assertSame(jumpIntensity, actualJumpIntensity);
    assertSame(jumpSizeMean, actualJumpSizeMean);
    assertSame(jumpSizeStDev, actualJumpSizeStdDev);
    assertSame(volatility, actualVolatility);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then RiskFreeRate return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MertonModel#MertonModel(RandomVariable, RandomVariable, RandomVariable,
   *       RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)}
   *   <li>{@link MertonModel#getJumpIntensity()}
   *   <li>{@link MertonModel#getJumpSizeMean()}
   *   <li>{@link MertonModel#getJumpSizeStdDev()}
   *   <li>{@link MertonModel#getNumberOfComponents()}
   *   <li>{@link MertonModel#getNumberOfFactors()}
   *   <li>{@link MertonModel#getRiskFreeRate()}
   *   <li>{@link MertonModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(RandomVariable, DiscountCurve, RandomVariable, DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)",
    "void MertonModel.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)",
    "RandomVariable MertonModel.getJumpIntensity()",
    "RandomVariable MertonModel.getJumpSizeMean()",
    "RandomVariable MertonModel.getJumpSizeStdDev()",
    "int MertonModel.getNumberOfComponents()",
    "int MertonModel.getNumberOfFactors()",
    "RandomVariable MertonModel.getRiskFreeRate()",
    "RandomVariable MertonModel.getVolatility()"
  })
  public void testGettersAndSetters_thenRiskFreeRateReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray discountRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray jumpIntensity = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray jumpSizeMean = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray jumpSizeStDev = new RandomVariableFromDoubleArray(10.0d);

    // Act
    MertonModel actualMertonModel =
        new MertonModel(
            initialValue,
            riskFreeRate,
            volatility,
            discountRate,
            jumpIntensity,
            jumpSizeMean,
            jumpSizeStDev,
            new RandomVariableFloatFactory());
    RandomVariable actualJumpIntensity = actualMertonModel.getJumpIntensity();
    RandomVariable actualJumpSizeMean = actualMertonModel.getJumpSizeMean();
    RandomVariable actualJumpSizeStdDev = actualMertonModel.getJumpSizeStdDev();
    int actualNumberOfComponents = actualMertonModel.getNumberOfComponents();
    int actualNumberOfFactors = actualMertonModel.getNumberOfFactors();
    RandomVariable actualRiskFreeRate = actualMertonModel.getRiskFreeRate();
    RandomVariable actualVolatility = actualMertonModel.getVolatility();

    // Assert
    assertTrue(actualJumpSizeMean instanceof RandomVariableFromDoubleArray);
    assertTrue(actualJumpSizeStdDev instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRiskFreeRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVolatility instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
    assertSame(jumpIntensity, actualJumpIntensity);
    assertSame(jumpSizeMean, actualJumpSizeMean);
    assertSame(jumpSizeStDev, actualJumpSizeStdDev);
    assertSame(riskFreeRate, actualRiskFreeRate);
    assertSame(volatility, actualVolatility);
  }

  /**
   * Test {@link MertonModel#MertonModel(double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MertonModel.<init>(double, double, double, double, double, double)"})
  public void testNewMertonModel() {
    // Arrange and Act
    MertonModel actualMertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualMertonModel.getJumpIntensity() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeMean() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeStdDev() instanceof Scalar);
    assertTrue(actualMertonModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualMertonModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualMertonModel.getNumberOfComponents());
    assertEquals(1, actualMertonModel.getNumberOfFactors());
  }

  /**
   * Test {@link MertonModel#MertonModel(double, double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, double, double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(double, double, double, double, double, double, double)"
  })
  public void testNewMertonModel2() {
    // Arrange and Act
    MertonModel actualMertonModel =
        new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualMertonModel.getJumpIntensity() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeMean() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeStdDev() instanceof Scalar);
    assertTrue(actualMertonModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualMertonModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualMertonModel.getNumberOfComponents());
    assertEquals(1, actualMertonModel.getNumberOfFactors());
  }

  /**
   * Test {@link MertonModel#MertonModel(double, double, double, double, double, double, double,
   * RandomVariableFactory)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, double, double, double, double,
   * double, double, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(double, double, double, double, double, double, double, RandomVariableFactory)"
  })
  public void testNewMertonModel3() {
    // Arrange and Act
    MertonModel actualMertonModel =
        new MertonModel(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, new RandomVariableFloatFactory());

    // Assert
    assertTrue(actualMertonModel.getJumpIntensity() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getJumpSizeMean() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getJumpSizeStdDev() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getRiskFreeRate() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getVolatility() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualMertonModel.getNumberOfComponents());
    assertEquals(1, actualMertonModel.getNumberOfFactors());
  }

  /**
   * Test {@link MertonModel#MertonModel(double, DiscountCurve, double, DiscountCurve, double,
   * double, double)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, DiscountCurve, double,
   * DiscountCurve, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(double, DiscountCurve, double, DiscountCurve, double, double, double)"
  })
  public void testNewMertonModel4() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    MertonModel actualMertonModel =
        new MertonModel(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualMertonModel.getJumpIntensity() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeMean() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeStdDev() instanceof Scalar);
    assertTrue(actualMertonModel.getVolatility() instanceof Scalar);
    assertNull(actualMertonModel.getRiskFreeRate());
    assertEquals(1, actualMertonModel.getNumberOfComponents());
    assertEquals(1, actualMertonModel.getNumberOfFactors());
  }

  /**
   * Test {@link MertonModel#MertonModel(MertonModelDescriptor)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(MertonModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MertonModel.<init>(MertonModelDescriptor)"})
  public void testNewMertonModel5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    MertonModel actualMertonModel =
        new MertonModel(
            new MertonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d));

    // Assert
    assertTrue(actualMertonModel.getJumpIntensity() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeMean() instanceof Scalar);
    assertTrue(actualMertonModel.getJumpSizeStdDev() instanceof Scalar);
    assertTrue(actualMertonModel.getVolatility() instanceof Scalar);
    assertNull(actualMertonModel.getRiskFreeRate());
    assertEquals(1, actualMertonModel.getNumberOfComponents());
    assertEquals(1, actualMertonModel.getNumberOfFactors());
  }

  /**
   * Test {@link MertonModel#MertonModel(double, DiscountCurve, double, DiscountCurve, double,
   * double, double, RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then JumpIntensity return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, DiscountCurve, double,
   * DiscountCurve, double, double, double, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(double, DiscountCurve, double, DiscountCurve, double, double, double, RandomVariableFactory)"
  })
  public void testNewMertonModel_thenJumpIntensityReturnRandomVariableFromFloatArray() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    MertonModel actualMertonModel =
        new MertonModel(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            new RandomVariableFloatFactory());

    // Assert
    assertTrue(actualMertonModel.getJumpIntensity() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getJumpSizeMean() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getJumpSizeStdDev() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMertonModel.getVolatility() instanceof RandomVariableFromFloatArray);
    assertNull(actualMertonModel.getRiskFreeRate());
    assertEquals(1, actualMertonModel.getNumberOfComponents());
    assertEquals(1, actualMertonModel.getNumberOfFactors());
  }

  /**
   * Test {@link MertonModel#applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MertonModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        mertonModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link MertonModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MertonModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        mertonModel.applyStateSpaceTransformInverse(
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
   * Test {@link MertonModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link MertonModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] MertonModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = mertonModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link MertonModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.1}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MertonModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnAverageIs01() {
    // Arrange
    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    MertonModel mertonModel =
        new MertonModel(
            new MertonModelDescriptor(
                referenceDate,
                10.0d,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                discountCurveForDiscountRate,
                10.0d,
                10.0d,
                10.0d,
                10.0d));
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = mertonModel.getNumeraire(process, 10.0d);

    // Assert
    verify(discountCurveForDiscountRate).getDiscountFactor(10.0d);
    assertTrue(actualNumeraire instanceof Scalar);
    assertEquals(0.1d, actualNumeraire.getAverage(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMax(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MertonModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.6881171418161356E43}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MertonModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnAverageIs26881171418161356e43() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = mertonModel.getNumeraire(process, 10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MertonModel#getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -2.218281828459045}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MertonModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIs2218281828459045() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    MertonModel mertonModel =
        new MertonModel(
            new MertonModelDescriptor(
                referenceDate,
                1.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                1.0d,
                1.0d,
                1.0d,
                1.0d));
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        mertonModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveForForwardRate, atLeast(1)).getDiscountFactor(anyDouble());
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(-2.218281828459045d, randomVariable.getAverage(), 0.0);
    assertEquals(-2.218281828459045d, randomVariable.getMax(), 0.0);
    assertEquals(-2.218281828459045d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link MertonModel#getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -220294.65794806718}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MertonModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIs22029465794806718() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        mertonModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(-220294.65794806718d, randomVariable.getAverage(), 0.0);
    assertEquals(-220294.65794806718d, randomVariable.getMax(), 0.0);
    assertEquals(-220294.65794806718d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link MertonModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link MertonModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MertonModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        mertonModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link MertonModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link MertonModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MertonModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        mertonModel.getRandomVariableForConstant(10.0d);

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
   * Test {@link MertonModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@link MertonModel}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ProcessModel MertonModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenHashMap_thenReturnMertonModel() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    ProcessModel actualCloneWithModifiedData =
        mertonModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof MertonModel);
    assertTrue(((MertonModel) actualCloneWithModifiedData).getJumpIntensity() instanceof Scalar);
    assertTrue(((MertonModel) actualCloneWithModifiedData).getJumpSizeMean() instanceof Scalar);
    assertTrue(((MertonModel) actualCloneWithModifiedData).getJumpSizeStdDev() instanceof Scalar);
    assertTrue(((MertonModel) actualCloneWithModifiedData).getRiskFreeRate() instanceof Scalar);
    assertTrue(((MertonModel) actualCloneWithModifiedData).getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }
}
