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
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InhomogenousBachelierModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link InhomogenousBachelierModel#InhomogenousBachelierModel(RandomVariableFactory,
   *       RandomVariable, RandomVariable, RandomVariable)}
   *   <li>{@link InhomogenousBachelierModel#toString()}
   *   <li>{@link InhomogenousBachelierModel#getInitialValue()}
   *   <li>{@link InhomogenousBachelierModel#getNumberOfComponents()}
   *   <li>{@link InhomogenousBachelierModel#getNumberOfFactors()}
   *   <li>{@link InhomogenousBachelierModel#getRiskFreeRate()}
   *   <li>{@link InhomogenousBachelierModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InhomogenousBachelierModel.<init>(RandomVariableFactory, RandomVariable, RandomVariable, RandomVariable)",
    "RandomVariable InhomogenousBachelierModel.getInitialValue()",
    "int InhomogenousBachelierModel.getNumberOfComponents()",
    "int InhomogenousBachelierModel.getNumberOfFactors()",
    "RandomVariable InhomogenousBachelierModel.getRiskFreeRate()",
    "RandomVariable InhomogenousBachelierModel.getVolatility()",
    "java.lang.String InhomogenousBachelierModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    InhomogenousBachelierModel actualInhomogenousBachelierModel =
        new InhomogenousBachelierModel(
            randomVariableFactory, initialValue, riskFreeRate, volatility);
    actualInhomogenousBachelierModel.toString();
    RandomVariable actualInitialValue = actualInhomogenousBachelierModel.getInitialValue();
    int actualNumberOfComponents = actualInhomogenousBachelierModel.getNumberOfComponents();
    int actualNumberOfFactors = actualInhomogenousBachelierModel.getNumberOfFactors();
    RandomVariable actualRiskFreeRate = actualInhomogenousBachelierModel.getRiskFreeRate();
    RandomVariable actualVolatility = actualInhomogenousBachelierModel.getVolatility();

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
   * Test {@link InhomogenousBachelierModel#InhomogenousBachelierModel(double, double, double)}.
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#InhomogenousBachelierModel(double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InhomogenousBachelierModel.<init>(double, double, double)"})
  public void testNewInhomogenousBachelierModel() {
    // Arrange and Act
    InhomogenousBachelierModel actualInhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualInhomogenousBachelierModel.getInitialValue() instanceof Scalar);
    assertTrue(actualInhomogenousBachelierModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualInhomogenousBachelierModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualInhomogenousBachelierModel.getNumberOfComponents());
    assertEquals(1, actualInhomogenousBachelierModel.getNumberOfFactors());
  }

  /**
   * Test {@link InhomogenousBachelierModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogenousBachelierModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = inhomogenousBachelierModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link InhomogenousBachelierModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogenousBachelierModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        inhomogenousBachelierModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable.sin() instanceof Scalar);
    assertTrue(randomVariable.sqrt() instanceof Scalar);
    assertTrue(randomVariable.squared() instanceof Scalar);
    assertTrue(randomVariable.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link InhomogenousBachelierModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then calls {@link TimeDiscretization#getTimeStep(int)}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogenousBachelierModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenTen_thenCallsGetTimeStep() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        inhomogenousBachelierModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization).getTimeStep(1);
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable.sin() instanceof Scalar);
    assertTrue(randomVariable.sqrt() instanceof Scalar);
    assertTrue(randomVariable.squared() instanceof Scalar);
    assertTrue(randomVariable.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link InhomogenousBachelierModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code 1.033730594319867E7}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getFactorLoading(MonteCarloProcess,
   * int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogenousBachelierModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenReturnFirstElementAverageIs1033730594319867e7() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(-2.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        inhomogenousBachelierModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(1.033730594319867E7d, randomVariable.getAverage(), 0.0);
    assertEquals(1.033730594319867E7d, randomVariable.getMax(), 0.0);
    assertEquals(1.033730594319867E7d, randomVariable.getMin(), 0.0);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link InhomogenousBachelierModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code 2.6304909491734976E-44}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getFactorLoading(MonteCarloProcess,
   * int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogenousBachelierModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenReturnFirstElementAverageIs26304909491734976e44() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        inhomogenousBachelierModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    verify(timeDiscretization).getTimeStep(1);
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(2.6304909491734976E-44d, randomVariable.getAverage(), 0.0);
    assertEquals(2.6304909491734976E-44d, randomVariable.getMax(), 0.0);
    assertEquals(2.6304909491734976E-44d, randomVariable.getMin(), 0.0);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link InhomogenousBachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Average is {@code 2.6881171418161355E44}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogenousBachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_givenTen_thenReturnAverageIs26881171418161355e44() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);

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
        inhomogenousBachelierModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2.6881171418161355E44d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogenousBachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.989519570547216E46}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogenousBachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnAverageIs3989519570547216e46() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TimeDiscretizationFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        inhomogenousBachelierModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(3.989519570547216E46d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(3.989519570547216E46d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(3.989519570547216E46d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {3.989519570547216E46d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogenousBachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogenousBachelierModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TimeDiscretizationFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        inhomogenousBachelierModel.applyStateSpaceTransform(
            process, 1, 1, RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY));

    // Assert
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
    assertEquals(3, actualApplyStateSpaceTransformResult.getTypePriority());
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getMin(), 0.0);
  }

  /**
   * Test {@link InhomogenousBachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogenousBachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIs3720075976020836e43() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);

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
        inhomogenousBachelierModel.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {3.720075976020836E-43d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogenousBachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.5065674758999532E-45}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogenousBachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIs25065674758999532e45() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TimeDiscretizationFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        inhomogenousBachelierModel.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        2.5065674758999532E-45d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(
        2.5065674758999532E-45d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(
        2.5065674758999532E-45d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2.5065674758999532E-45d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogenousBachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogenousBachelierModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TimeDiscretizationFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        inhomogenousBachelierModel.applyStateSpaceTransformInverse(
            process, 1, 1, RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY));

    // Assert
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
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
  }

  /**
   * Test {@link InhomogenousBachelierModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnScalar() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = inhomogenousBachelierModel.getNumeraire(process, 10.0d);

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
   * Test {@link InhomogenousBachelierModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d).getRandomVariableForConstant(10.0d);

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
   * Test {@link InhomogenousBachelierModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then InitialValue return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InhomogenousBachelierModel InhomogenousBachelierModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap_thenInitialValueReturnScalar() {
    // Arrange
    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d);

    // Act
    InhomogenousBachelierModel actualCloneWithModifiedData =
        inhomogenousBachelierModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData.getInitialValue() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link InhomogenousBachelierModel#getImpliedBachelierVolatility(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getImpliedBachelierVolatility(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.getImpliedBachelierVolatility(double)"
  })
  public void testGetImpliedBachelierVolatility_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualImpliedBachelierVolatility =
        new InhomogenousBachelierModel(10.0d, 10.0d, 10.0d).getImpliedBachelierVolatility(10.0d);

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
    assertEquals(0.7071067811865475d, actualImpliedBachelierVolatility.getAverage(), 0.0);
    assertEquals(0.7071067811865475d, actualImpliedBachelierVolatility.getMax(), 0.0);
    assertEquals(0.7071067811865475d, actualImpliedBachelierVolatility.getMin(), 0.0);
    assertEquals(1, actualImpliedBachelierVolatility.size());
    assertTrue(actualImpliedBachelierVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualImpliedBachelierVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualImpliedBachelierVolatility.expectation();
    assertSame(actualImpliedBachelierVolatility, actualExpectationResult);
  }

  /**
   * Test {@link InhomogenousBachelierModel#getImpliedBachelierVolatility(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogenousBachelierModel#getImpliedBachelierVolatility(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogenousBachelierModel.getImpliedBachelierVolatility(double)"
  })
  public void testGetImpliedBachelierVolatility_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sqrt()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.invert()).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.exp()).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD riskFreeRate = mock(RandomVariableAAD.class);
    when(riskFreeRate.mult(anyDouble())).thenReturn(randomVariableAAD3);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(-2.0d);

    InhomogenousBachelierModel inhomogenousBachelierModel =
        new InhomogenousBachelierModel(
            randomVariableFactory,
            initialValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(-2.0d));

    // Act
    RandomVariable actualImpliedBachelierVolatility =
        inhomogenousBachelierModel.getImpliedBachelierVolatility(10.0d);

    // Assert
    verify(randomVariableAAD3).exp();
    verify(randomVariableAAD3).invert();
    verify(riskFreeRate).mult(-20.0d);
    verify(randomVariableAAD2).mult(9.0d);
    verify(randomVariableAAD).sqrt();
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
    assertEquals(-20.0d, actualImpliedBachelierVolatility.getAverage(), 0.0);
    assertEquals(-20.0d, actualImpliedBachelierVolatility.getMax(), 0.0);
    assertEquals(-20.0d, actualImpliedBachelierVolatility.getMin(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierVolatility.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualImpliedBachelierVolatility).getGradient().size());
    assertEquals(1, actualImpliedBachelierVolatility.size());
    assertEquals(3, actualImpliedBachelierVolatility.getTypePriority());
    assertTrue(actualImpliedBachelierVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualImpliedBachelierVolatility.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {-20.0d}, actualImpliedBachelierVolatility.getRealizations(), 0.0);
  }
}
