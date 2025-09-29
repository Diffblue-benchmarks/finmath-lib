package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesModelWithStockNumeraireDiffblueTest {
  /**
   * Test {@link BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(double,
   * double, double)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlackScholesModelWithStockNumeraire.<init>(double, double, double)"})
  public void testNewBlackScholesModelWithStockNumeraire() {
    // Arrange and Act
    BlackScholesModelWithStockNumeraire actualBlackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesModelWithStockNumeraire.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualBlackScholesModelWithStockNumeraire.getVolatility() instanceof Scalar);
    assertEquals(1, actualBlackScholesModelWithStockNumeraire.getNumberOfComponents());
    assertEquals(1, actualBlackScholesModelWithStockNumeraire.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(double,
   * double, double, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(double, double, double,
   * RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModelWithStockNumeraire.<init>(double, double, double, RandomVariableFactory)"
  })
  public void testNewBlackScholesModelWithStockNumeraire2() {
    // Arrange and Act
    BlackScholesModelWithStockNumeraire actualBlackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(
            10.0d, 10.0d, 10.0d, new RandomVariableFloatFactory());

    // Assert
    assertTrue(
        actualBlackScholesModelWithStockNumeraire.getRiskFreeRate()
            instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualBlackScholesModelWithStockNumeraire.getVolatility()
            instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualBlackScholesModelWithStockNumeraire.getNumberOfComponents());
    assertEquals(1, actualBlackScholesModelWithStockNumeraire.getNumberOfFactors());
  }

  /**
   * Test {@link
   * BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModelWithStockNumeraire.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)"
  })
  public void testNewBlackScholesModelWithStockNumeraire3() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    BlackScholesModelWithStockNumeraire actualBlackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    // Assert
    RandomVariable riskFreeRate2 = actualBlackScholesModelWithStockNumeraire.getRiskFreeRate();
    assertTrue(riskFreeRate2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesModelWithStockNumeraire.getVolatility()
            instanceof RandomVariableFromDoubleArray);
    assertSame(riskFreeRate, riskFreeRate2);
  }

  /**
   * Test {@link
   * BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#BlackScholesModelWithStockNumeraire(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModelWithStockNumeraire.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)"
  })
  public void testNewBlackScholesModelWithStockNumeraire4() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(2.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    BlackScholesModelWithStockNumeraire actualBlackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    // Assert
    assertTrue(
        actualBlackScholesModelWithStockNumeraire.getVolatility()
            instanceof RandomVariableFromDoubleArray);
    RandomVariable riskFreeRate2 = actualBlackScholesModelWithStockNumeraire.getRiskFreeRate();
    assertTrue(riskFreeRate2 instanceof RandomVariableDifferentiableAAD);
    assertSame(riskFreeRate, riskFreeRate2);
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithStockNumeraire.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState =
        blackScholesModelWithStockNumeraire.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <p>Method under test: {@link BlackScholesModelWithStockNumeraire#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithStockNumeraire.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        blackScholesModelWithStockNumeraire.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof Scalar);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithStockNumeraire.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        blackScholesModelWithStockNumeraire.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithStockNumeraire.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        blackScholesModelWithStockNumeraire.applyStateSpaceTransform(
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
   * Test {@link
   * BlackScholesModelWithStockNumeraire#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithStockNumeraire.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        blackScholesModelWithStockNumeraire.applyStateSpaceTransformInverse(
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
   * Test {@link BlackScholesModelWithStockNumeraire#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithStockNumeraire.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray start = new RandomVariableFromDoubleArray(10.0d);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model,
            new BrownianMotionWithControlVariate(
                new BrownianBridge(
                    timeDiscretization, 10, 42, start, new RandomVariableFromDoubleArray(10.0d))));

    // Act
    RandomVariable actualNumeraire =
        blackScholesModelWithStockNumeraire.getNumeraire(process, 10.0d);

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
   * Test {@link BlackScholesModelWithStockNumeraire#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithStockNumeraire.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);

    RandomVariableFromDoubleArray end = new RandomVariableFromDoubleArray(10.0d);
    end.addProduct(new RandomVariableFromDoubleArray(10.0d), 0.5d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(1.0d);
    end.addProduct(factor1, new RandomVariableFromDoubleArray(1.0d));
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(
            new BrownianBridge(
                timeDiscretization, 10, 42, new RandomVariableFromDoubleArray(10.0d), end));
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire =
        blackScholesModelWithStockNumeraire.getNumeraire(process, 10.0d);

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
   * Test {@link BlackScholesModelWithStockNumeraire#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAAD} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithStockNumeraire.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_whenRandomVariableDifferentiableAADWithValueIsOne() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableDifferentiableAAD start = RandomVariableDifferentiableAAD.of(1.0d);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model,
            new BrownianMotionWithControlVariate(
                new BrownianBridge(
                    timeDiscretization, 10, 42, start, new RandomVariableFromDoubleArray(10.0d))));

    // Act
    RandomVariable actualNumeraire =
        blackScholesModelWithStockNumeraire.getNumeraire(process, 10.0d);

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
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BlackScholesModelWithStockNumeraire#toString()}
   *   <li>{@link BlackScholesModelWithStockNumeraire#getNumberOfComponents()}
   *   <li>{@link BlackScholesModelWithStockNumeraire#getNumberOfFactors()}
   *   <li>{@link BlackScholesModelWithStockNumeraire#getRiskFreeRate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int BlackScholesModelWithStockNumeraire.getNumberOfComponents()",
    "int BlackScholesModelWithStockNumeraire.getNumberOfFactors()",
    "RandomVariable BlackScholesModelWithStockNumeraire.getRiskFreeRate()",
    "String BlackScholesModelWithStockNumeraire.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);

    // Act
    String actualToStringResult = blackScholesModelWithStockNumeraire.toString();
    int actualNumberOfComponents = blackScholesModelWithStockNumeraire.getNumberOfComponents();
    int actualNumberOfFactors = blackScholesModelWithStockNumeraire.getNumberOfFactors();

    // Assert
    assertTrue(blackScholesModelWithStockNumeraire.getRiskFreeRate() instanceof Scalar);
    assertEquals(
        "BlackScholesModel [initialValue=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0],"
            + " riskFreeRate=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0], volatility=Scalar"
            + " [value=10.0, filtrationTime=-Infinity, typePriority()=0], randomVariableFactory=RandomVariableFrom"
            + "ArrayFactory [isUseDoublePrecisionFloatingPointImplementation=true], initialState=[Scalar [value=2"
            + ".302585092994046, filtrationTime=-Infinity, typePriority()=0]], drift=[Scalar [value=60.0,"
            + " filtrationTime=-Infinity, typePriority()=0]], factorLoadings=[Scalar [value=10.0, filtrationTime=-Infinity,"
            + " typePriority()=0]]]",
        actualToStringResult);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModelWithStockNumeraire.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d)
            .getRandomVariableForConstant(10.0d);

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
   * Test {@link BlackScholesModelWithStockNumeraire#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Given {@code initialValue}.
   *   <li>When {@link HashMap#HashMap()} {@code initialValue} is {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithStockNumeraire#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BlackScholesModelWithStockNumeraire BlackScholesModelWithStockNumeraire.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_givenInitialValue_whenHashMapInitialValueIsA() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("initialValue", (byte) 'A');
    dataModified.put("riskFreeRate", null);
    dataModified.put("volatility", null);

    // Act
    BlackScholesModelWithStockNumeraire actualCloneWithModifiedData =
        blackScholesModelWithStockNumeraire.getCloneWithModifiedData(dataModified);

    // Assert
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModelWithStockNumeraire#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BlackScholesModelWithStockNumeraire BlackScholesModelWithStockNumeraire.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);

    // Act
    BlackScholesModelWithStockNumeraire actualCloneWithModifiedData =
        blackScholesModelWithStockNumeraire.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getInitialValue(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelWithStockNumeraire#getInitialValue(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModelWithStockNumeraire.getInitialValue(MonteCarloProcess)"
  })
  public void testGetInitialValue() {
    // Arrange
    BlackScholesModelWithStockNumeraire blackScholesModelWithStockNumeraire =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialValue =
        blackScholesModelWithStockNumeraire.getInitialValue(process);

    // Assert
    assertTrue(actualInitialValue[0] instanceof Scalar);
    assertEquals(1, actualInitialValue.length);
  }

  /**
   * Test {@link BlackScholesModelWithStockNumeraire#getVolatility()}.
   *
   * <p>Method under test: {@link BlackScholesModelWithStockNumeraire#getVolatility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BlackScholesModelWithStockNumeraire.getVolatility()"})
  public void testGetVolatility() {
    // Arrange and Act
    RandomVariable actualVolatility =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d).getVolatility();

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertTrue(actualVolatility.abs() instanceof Scalar);
    assertTrue(actualVolatility.cos() instanceof Scalar);
    assertTrue(actualVolatility.exp() instanceof Scalar);
    assertTrue(actualVolatility.expm1() instanceof Scalar);
    assertTrue(actualVolatility.invert() instanceof Scalar);
    assertTrue(actualVolatility.isNaN() instanceof Scalar);
    assertTrue(actualVolatility.sin() instanceof Scalar);
    assertTrue(actualVolatility.sqrt() instanceof Scalar);
    assertTrue(actualVolatility.squared() instanceof Scalar);
    assertTrue(actualVolatility.variance() instanceof Scalar);
    assertNull(actualVolatility.getRealizations());
    assertNull(actualVolatility.getOperator());
    assertNull(actualVolatility.getRealizationsStream());
    assertEquals(0, actualVolatility.getTypePriority());
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(1, actualVolatility.size());
    assertEquals(10.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.0d, actualVolatility.getMax(), 0.0);
    assertEquals(10.0d, actualVolatility.getMin(), 0.0);
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }
}
