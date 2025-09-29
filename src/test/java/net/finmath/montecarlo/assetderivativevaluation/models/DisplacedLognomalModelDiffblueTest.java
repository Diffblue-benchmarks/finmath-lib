package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import net.finmath.montecarlo.RandomVariableFromArrayFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DisplacedLognomalModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DisplacedLognomalModel#DisplacedLognomalModel(RandomVariableFactory,
   *       RandomVariable, RandomVariable, RandomVariable, RandomVariable)}
   *   <li>{@link DisplacedLognomalModel#toString()}
   *   <li>{@link DisplacedLognomalModel#getDisplacement()}
   *   <li>{@link DisplacedLognomalModel#getInitialValue()}
   *   <li>{@link DisplacedLognomalModel#getNumberOfComponents()}
   *   <li>{@link DisplacedLognomalModel#getNumberOfFactors()}
   *   <li>{@link DisplacedLognomalModel#getRandomVariableFactory()}
   *   <li>{@link DisplacedLognomalModel#getRiskFreeRate()}
   *   <li>{@link DisplacedLognomalModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisplacedLognomalModel.<init>(RandomVariableFactory, RandomVariable, RandomVariable, RandomVariable, RandomVariable)",
    "RandomVariable DisplacedLognomalModel.getDisplacement()",
    "RandomVariable DisplacedLognomalModel.getInitialValue()",
    "int DisplacedLognomalModel.getNumberOfComponents()",
    "int DisplacedLognomalModel.getNumberOfFactors()",
    "RandomVariableFactory DisplacedLognomalModel.getRandomVariableFactory()",
    "RandomVariable DisplacedLognomalModel.getRiskFreeRate()",
    "RandomVariable DisplacedLognomalModel.getVolatility()",
    "java.lang.String DisplacedLognomalModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray displacement = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    DisplacedLognomalModel actualDisplacedLognomalModel =
        new DisplacedLognomalModel(
            randomVariableFactory, initialValue, riskFreeRate, displacement, volatility);
    actualDisplacedLognomalModel.toString();
    RandomVariable actualDisplacement = actualDisplacedLognomalModel.getDisplacement();
    RandomVariable actualInitialValue = actualDisplacedLognomalModel.getInitialValue();
    int actualNumberOfComponents = actualDisplacedLognomalModel.getNumberOfComponents();
    int actualNumberOfFactors = actualDisplacedLognomalModel.getNumberOfFactors();
    RandomVariableFactory actualRandomVariableFactory =
        actualDisplacedLognomalModel.getRandomVariableFactory();
    RandomVariable actualRiskFreeRate = actualDisplacedLognomalModel.getRiskFreeRate();
    RandomVariable actualVolatility = actualDisplacedLognomalModel.getVolatility();

    // Assert
    assertTrue(actualRandomVariableFactory instanceof RandomVariableFloatFactory);
    assertTrue(actualInitialValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRiskFreeRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVolatility instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
    assertSame(randomVariableFactory, actualRandomVariableFactory);
    assertSame(displacement, actualDisplacement);
    assertSame(initialValue, actualInitialValue);
    assertSame(riskFreeRate, actualRiskFreeRate);
    assertSame(volatility, actualVolatility);
  }

  /**
   * Test {@link DisplacedLognomalModel#DisplacedLognomalModel(double, double, double, double)}.
   *
   * <p>Method under test: {@link DisplacedLognomalModel#DisplacedLognomalModel(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognomalModel.<init>(double, double, double, double)"})
  public void testNewDisplacedLognomalModel() {
    // Arrange and Act
    DisplacedLognomalModel actualDisplacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(
        actualDisplacedLognomalModel.getRandomVariableFactory()
            instanceof RandomVariableFromArrayFactory);
    assertTrue(actualDisplacedLognomalModel.getDisplacement() instanceof Scalar);
    assertTrue(actualDisplacedLognomalModel.getInitialValue() instanceof Scalar);
    assertTrue(actualDisplacedLognomalModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualDisplacedLognomalModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualDisplacedLognomalModel.getNumberOfComponents());
    assertEquals(1, actualDisplacedLognomalModel.getNumberOfFactors());
  }

  /**
   * Test {@link DisplacedLognomalModel#DisplacedLognomalModel(RandomVariableFactory, double,
   * double, double, double)}.
   *
   * <p>Method under test: {@link
   * DisplacedLognomalModel#DisplacedLognomalModel(RandomVariableFactory, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisplacedLognomalModel.<init>(RandomVariableFactory, double, double, double, double)"
  })
  public void testNewDisplacedLognomalModel2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    // Act
    DisplacedLognomalModel actualDisplacedLognomalModel =
        new DisplacedLognomalModel(randomVariableFactory, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    RandomVariableFactory randomVariableFactory2 =
        actualDisplacedLognomalModel.getRandomVariableFactory();
    assertTrue(randomVariableFactory2 instanceof RandomVariableFloatFactory);
    assertTrue(
        actualDisplacedLognomalModel.getDisplacement() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualDisplacedLognomalModel.getInitialValue() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualDisplacedLognomalModel.getRiskFreeRate() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualDisplacedLognomalModel.getVolatility() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualDisplacedLognomalModel.getNumberOfComponents());
    assertEquals(1, actualDisplacedLognomalModel.getNumberOfFactors());
    assertSame(randomVariableFactory, randomVariableFactory2);
  }

  /**
   * Test {@link DisplacedLognomalModel#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognomalModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLognomalModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState_thenFirstElementReturnScalar() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = displacedLognomalModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link DisplacedLognomalModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognomalModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DisplacedLognomalModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnScalar() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        displacedLognomalModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof Scalar);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link DisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link DisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DisplacedLognomalModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        displacedLognomalModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link DisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DisplacedLognomalModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.addSumProduct(
            Mockito.<RandomVariable[]>any(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.exp()).thenReturn(randomVariableAAD);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        displacedLognomalModel.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    verify(randomVariable).exp();
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    verify(randomVariable).addSumProduct(isA(RandomVariable[].class), isA(RandomVariable[].class));
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
   * Test {@link DisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DisplacedLognomalModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

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
        displacedLognomalModel.applyStateSpaceTransform(
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
    assertEquals(5.918283910522855E47d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(5.918283910522855E47d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(5.918283910522855E47d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {5.918283910522855E47d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link DisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DisplacedLognomalModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

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
        displacedLognomalModel.applyStateSpaceTransformInverse(
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
   * Test {@link DisplacedLognomalModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognomalModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DisplacedLognomalModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnScalar() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = displacedLognomalModel.getNumeraire(process, 10.0d);

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
   * Test {@link DisplacedLognomalModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognomalModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DisplacedLognomalModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        displacedLognomalModel.getRandomVariableForConstant(10.0d);

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
   * Test {@link DisplacedLognomalModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link DisplacedLognomalModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DisplacedLognomalModel DisplacedLognomalModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData() {
    // Arrange
    DisplacedLognomalModel displacedLognomalModel =
        new DisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    DisplacedLognomalModel actualCloneWithModifiedData =
        displacedLognomalModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(
        actualCloneWithModifiedData.getRandomVariableFactory()
            instanceof RandomVariableFromArrayFactory);
    assertTrue(actualCloneWithModifiedData.getDisplacement() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getInitialValue() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }
}
