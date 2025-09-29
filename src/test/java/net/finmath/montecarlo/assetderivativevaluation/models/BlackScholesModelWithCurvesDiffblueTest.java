package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesModelWithCurvesDiffblueTest {
  /**
   * Test {@link BlackScholesModelWithCurves#BlackScholesModelWithCurves(Double, DiscountCurve,
   * Double, DiscountCurve, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#BlackScholesModelWithCurves(Double,
   * DiscountCurve, Double, DiscountCurve, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModelWithCurves.<init>(Double, DiscountCurve, Double, DiscountCurve, RandomVariableFactory)"
  })
  public void testNewBlackScholesModelWithCurves() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BlackScholesModelWithCurves actualBlackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    // Assert
    assertTrue(
        actualBlackScholesModelWithCurves.getVolatility() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualBlackScholesModelWithCurves.getNumberOfComponents());
    assertEquals(1, actualBlackScholesModelWithCurves.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModelWithCurves#BlackScholesModelWithCurves(RandomVariable,
   * DiscountCurve, RandomVariable, DiscountCurve, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithCurves#BlackScholesModelWithCurves(RandomVariable, DiscountCurve,
   * RandomVariable, DiscountCurve, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModelWithCurves.<init>(RandomVariable, DiscountCurve, RandomVariable, DiscountCurve, RandomVariableFactory)"
  })
  public void testNewBlackScholesModelWithCurves2() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BlackScholesModelWithCurves actualBlackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            initialValue,
            discountCurveForForwardRate,
            volatility,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    // Assert
    RandomVariable volatility2 = actualBlackScholesModelWithCurves.getVolatility();
    assertTrue(volatility2 instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualBlackScholesModelWithCurves.getNumberOfComponents());
    assertEquals(1, actualBlackScholesModelWithCurves.getNumberOfFactors());
    assertSame(volatility, volatility2);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithCurves.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = blackScholesModelWithCurves.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithCurves.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnRandomVariableFromFloatArray() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        blackScholesModelWithCurves.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveForForwardRate, atLeast(1)).getDiscountFactor(anyDouble());
    assertTrue(actualDrift[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getFactorLoading(MonteCarloProcess,
   * int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithCurves.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        blackScholesModelWithCurves.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithCurves#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithCurves.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        blackScholesModelWithCurves.applyStateSpaceTransform(
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
   * Test {@link BlackScholesModelWithCurves#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithCurves#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithCurves.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        blackScholesModelWithCurves.applyStateSpaceTransformInverse(
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
   * Test {@link BlackScholesModelWithCurves#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithCurves.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = blackScholesModelWithCurves.getNumeraire(process, 10.0d);

    // Assert
    verify(discountCurveForDiscountRate).getDiscountFactor(10.0d);
    assertTrue(actualNumeraire instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(0.1d, actualNumeraire.getAverage(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMax(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMin(), 0.0);
    assertEquals(1, actualNumeraire.getTypePriority());
    assertEquals(1, actualNumeraire.size());
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.1d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BlackScholesModelWithCurves#toString()}
   *   <li>{@link BlackScholesModelWithCurves#getNumberOfComponents()}
   *   <li>{@link BlackScholesModelWithCurves#getNumberOfFactors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int BlackScholesModelWithCurves.getNumberOfComponents()",
    "int BlackScholesModelWithCurves.getNumberOfFactors()",
    "String BlackScholesModelWithCurves.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    // Act
    blackScholesModelWithCurves.toString();
    int actualNumberOfComponents = blackScholesModelWithCurves.getNumberOfComponents();

    // Assert
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, blackScholesModelWithCurves.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithCurves.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    // Act
    RandomVariable actualRandomVariableForConstant =
        blackScholesModelWithCurves.getRandomVariableForConstant(10.0d);

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
   * Test {@link BlackScholesModelWithCurves#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Given {@code initialValue}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BlackScholesModelWithCurves BlackScholesModelWithCurves.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_givenInitialValue() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("initialValue", Scalar.of(10.0d));
    dataModified.put("volatility", null);

    // Act and Assert
    RandomVariable volatility =
        blackScholesModelWithCurves.getCloneWithModifiedData(dataModified).getVolatility();
    assertTrue(volatility instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.average() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, volatility.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BlackScholesModelWithCurves BlackScholesModelWithCurves.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    // Act and Assert
    RandomVariable volatility =
        blackScholesModelWithCurves.getCloneWithModifiedData(new HashMap<>()).getVolatility();
    assertTrue(volatility instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.average() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(volatility.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, volatility.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getInitialValue(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getInitialValue(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithCurves.getInitialValue(MonteCarloProcess)"
  })
  public void testGetInitialValue() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialValue = blackScholesModelWithCurves.getInitialValue(process);

    // Assert
    assertTrue(actualInitialValue[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualInitialValue.length);
  }

  /**
   * Test {@link BlackScholesModelWithCurves#getVolatility()}.
   *
   * <p>Method under test: {@link BlackScholesModelWithCurves#getVolatility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BlackScholesModelWithCurves.getVolatility()"})
  public void testGetVolatility() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves blackScholesModelWithCurves =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());

    // Act
    RandomVariable actualVolatility = blackScholesModelWithCurves.getVolatility();

    // Assert
    assertTrue(actualVolatility instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(1, actualVolatility.getTypePriority());
    assertEquals(1, actualVolatility.size());
    assertEquals(10.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.0d, actualVolatility.getMax(), 0.0);
    assertEquals(10.0d, actualVolatility.getMin(), 0.0);
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualVolatility.getRealizations(), 0.0);
  }
}
