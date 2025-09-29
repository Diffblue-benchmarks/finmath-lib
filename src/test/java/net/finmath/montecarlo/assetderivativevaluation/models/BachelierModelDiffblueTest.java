package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BachelierModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BachelierModel#BachelierModel(RandomVariableFactory, RandomVariable,
   *       RandomVariable, RandomVariable)}
   *   <li>{@link BachelierModel#toString()}
   *   <li>{@link BachelierModel#getInitialValue()}
   *   <li>{@link BachelierModel#getNumberOfComponents()}
   *   <li>{@link BachelierModel#getNumberOfFactors()}
   *   <li>{@link BachelierModel#getRiskFreeRate()}
   *   <li>{@link BachelierModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BachelierModel.<init>(RandomVariableFactory, RandomVariable, RandomVariable, RandomVariable)",
    "RandomVariable BachelierModel.getInitialValue()",
    "int BachelierModel.getNumberOfComponents()",
    "int BachelierModel.getNumberOfFactors()",
    "RandomVariable BachelierModel.getRiskFreeRate()",
    "RandomVariable BachelierModel.getVolatility()",
    "java.lang.String BachelierModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    BachelierModel actualBachelierModel =
        new BachelierModel(randomVariableFactory, initialValue, riskFreeRate, volatility);
    actualBachelierModel.toString();
    RandomVariable actualInitialValue = actualBachelierModel.getInitialValue();
    int actualNumberOfComponents = actualBachelierModel.getNumberOfComponents();
    int actualNumberOfFactors = actualBachelierModel.getNumberOfFactors();
    RandomVariable actualRiskFreeRate = actualBachelierModel.getRiskFreeRate();
    RandomVariable actualVolatility = actualBachelierModel.getVolatility();

    // Assert
    assertTrue(actualRiskFreeRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVolatility instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
    assertSame(initialValue, actualInitialValue);
    assertSame(riskFreeRate, actualRiskFreeRate);
    assertSame(volatility, actualVolatility);
  }

  /**
   * Test {@link BachelierModel#BachelierModel(double, double, double)}.
   *
   * <p>Method under test: {@link BachelierModel#BachelierModel(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BachelierModel.<init>(double, double, double)"})
  public void testNewBachelierModel() {
    // Arrange and Act
    BachelierModel actualBachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBachelierModel.getInitialValue() instanceof Scalar);
    assertTrue(actualBachelierModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualBachelierModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualBachelierModel.getNumberOfComponents());
    assertEquals(1, actualBachelierModel.getNumberOfFactors());
  }

  /**
   * Test {@link BachelierModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link BachelierModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BachelierModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = bachelierModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link BachelierModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link BachelierModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BachelierModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        bachelierModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof Scalar);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link BachelierModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link BachelierModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BachelierModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        bachelierModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link BachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        bachelierModel.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualApplyStateSpaceTransformResult.getValues() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.isNaN() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformResult.getOperator());
    assertNull(actualApplyStateSpaceTransformResult.getRealizationsStream());
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
            .getGradient()
            .size());
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertEquals(3, actualApplyStateSpaceTransformResult.getTypePriority());
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link BachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);

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
    RandomVariable actualApplyStateSpaceTransformResult =
        bachelierModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
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
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {2.6881171418161355E44d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        bachelierModel.applyStateSpaceTransformInverse(process, 1, 1, randomVariable);

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualApplyStateSpaceTransformInverseResult.getValues() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isNaN() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformInverseResult.getOperator());
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizationsStream());
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
            .getGradient()
            .size());
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(3, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(
        3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);

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
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        bachelierModel.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
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
    assertEquals(
        3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {3.720075976020836E-43d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BachelierModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnScalar() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = bachelierModel.getNumeraire(process, 10.0d);

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
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link BachelierModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BachelierModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new BachelierModel(10.0d, 10.0d, 10.0d).getRandomVariableForConstant(10.0d);

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
   * Test {@link BachelierModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then InitialValue return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BachelierModel BachelierModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenHashMap_thenInitialValueReturnScalar() {
    // Arrange
    BachelierModel bachelierModel = new BachelierModel(10.0d, 10.0d, 10.0d);

    // Act
    BachelierModel actualCloneWithModifiedData =
        bachelierModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData.getInitialValue() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link BachelierModel#getImpliedBachelierVolatility(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#getImpliedBachelierVolatility(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BachelierModel.getImpliedBachelierVolatility(double)"})
  public void testGetImpliedBachelierVolatility_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualImpliedBachelierVolatility =
        new BachelierModel(10.0d, 10.0d, 10.0d).getImpliedBachelierVolatility(10.0d);

    // Assert
    assertTrue(actualImpliedBachelierVolatility instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.abs() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.cos() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.exp() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.expm1() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.invert() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.isNaN() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.sin() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.sqrt() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.squared() instanceof Scalar);
    assertTrue(actualImpliedBachelierVolatility.variance() instanceof Scalar);
    assertNull(actualImpliedBachelierVolatility.getRealizations());
    assertNull(actualImpliedBachelierVolatility.getOperator());
    assertNull(actualImpliedBachelierVolatility.getRealizationsStream());
    assertEquals(0, actualImpliedBachelierVolatility.getTypePriority());
    assertEquals(0.0d, actualImpliedBachelierVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getVariance(), 0.0);
    assertEquals(1, actualImpliedBachelierVolatility.size());
    assertEquals(2.6881171418161355E44d, actualImpliedBachelierVolatility.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualImpliedBachelierVolatility.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualImpliedBachelierVolatility.getMin(), 0.0);
    assertTrue(actualImpliedBachelierVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualImpliedBachelierVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualImpliedBachelierVolatility.expectation();
    assertSame(actualImpliedBachelierVolatility, actualExpectationResult);
  }

  /**
   * Test {@link BachelierModel#getImpliedBachelierVolatility(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#getImpliedBachelierVolatility(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BachelierModel.getImpliedBachelierVolatility(double)"})
  public void testGetImpliedBachelierVolatility_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.exp()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD riskFreeRate = mock(RandomVariableAAD.class);
    when(riskFreeRate.mult(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);

    BachelierModel bachelierModel =
        new BachelierModel(
            randomVariableFactory,
            initialValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualImpliedBachelierVolatility =
        bachelierModel.getImpliedBachelierVolatility(10.0d);

    // Assert
    verify(randomVariableAAD).exp();
    verify(riskFreeRate).mult(10.0d);
    assertTrue(
        actualImpliedBachelierVolatility.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualImpliedBachelierVolatility.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualImpliedBachelierVolatility instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualImpliedBachelierVolatility.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualImpliedBachelierVolatility.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualImpliedBachelierVolatility.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility).getGradient().size());
    assertEquals(1, actualImpliedBachelierVolatility.size());
    assertEquals(100.0d, actualImpliedBachelierVolatility.getAverage(), 0.0);
    assertEquals(100.0d, actualImpliedBachelierVolatility.getMax(), 0.0);
    assertEquals(100.0d, actualImpliedBachelierVolatility.getMin(), 0.0);
    assertEquals(3, actualImpliedBachelierVolatility.getTypePriority());
    assertTrue(actualImpliedBachelierVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualImpliedBachelierVolatility.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {100.0d}, actualImpliedBachelierVolatility.getRealizations(), 0.0);
  }
}
