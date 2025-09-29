package net.finmath.montecarlo.automaticdifferentiation.backward.alternative;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.RandomVariableDifferentiable;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableDifferentiableAADStochasticNonOptimizedFactoryDiffblueTest {
  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimizedFactory#RandomVariableDifferentiableAADStochasticNonOptimizedFactory()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimizedFactory#RandomVariableDifferentiableAADStochasticNonOptimizedFactory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADStochasticNonOptimizedFactory.<init>()"})
  public void testNewRandomVariableDifferentiableAADStochasticNonOptimizedFactory() {
    // Arrange and Act
    RandomVariableDifferentiableAADStochasticNonOptimizedFactory
        actualRandomVariableDifferentiableAADStochasticNonOptimizedFactory =
            new RandomVariableDifferentiableAADStochasticNonOptimizedFactory();

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimizedFactory.createRandomVariable(
                null)
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimizedFactory.createRandomVariable(
                10.0d)
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimizedFactory#createRandomVariable(double,
   * double)} with {@code time}, {@code value}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimizedFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimizedFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableAADStochasticNonOptimizedFactory()
            .createRandomVariable(10.0d, 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.0d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertEquals(1, actualCreateRandomVariableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(3, actualCreateRandomVariableResult.getTypePriority());
    assertTrue(actualCreateRandomVariableResult.isDeterministic());
    assertSame(randomVariable, actualCreateRandomVariableResult.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualCreateRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimizedFactory#createRandomVariable(double,
   * double[])} with {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimizedFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimizedFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableAADStochasticNonOptimizedFactory()
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualCreateRandomVariableResult.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.5d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(2.375d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(22.5625d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(3, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(30.083333333333332d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(4, actualCreateRandomVariableResult.size());
    assertEquals(4.75d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertFalse(actualCreateRandomVariableResult.isDeterministic());
    assertSame(randomVariable, actualCreateRandomVariableResult.getValues());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }
}
