package net.finmath.montecarlo.automaticdifferentiation.backward.alternative;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.RandomVariableDifferentiable;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableDifferentiableAADStochasticNonOptimizedDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#of(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#of(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADStochasticNonOptimized RandomVariableDifferentiableAADStochasticNonOptimized.of(RandomVariable)"
  })
  public void testOfWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAADStochasticNonOptimized actualOfResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Assert
    RandomVariable randomVariable2 = actualOfResult.getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualOfResult.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.expm1() instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.variance() instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.0d, actualOfResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualOfResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualOfResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualOfResult.getVariance(), 0.0);
    assertEquals(1, actualOfResult.getGradient().size());
    assertEquals(1, actualOfResult.size());
    assertEquals(10.0d, actualOfResult.getAverage(), 0.0);
    assertEquals(10.0d, actualOfResult.getMax(), 0.0);
    assertEquals(10.0d, actualOfResult.getMin(), 0.0);
    assertEquals(3, actualOfResult.getTypePriority());
    assertTrue(actualOfResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualOfResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, randomVariable2);
    assertSame(randomVariable, actualOfResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualOfResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#of(double)} with {@code
   * value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#of(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADStochasticNonOptimized RandomVariableDifferentiableAADStochasticNonOptimized.of(double)"
  })
  public void testOfWithValue() {
    // Arrange and Act
    RandomVariableDifferentiableAADStochasticNonOptimized actualOfResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Assert
    RandomVariable randomVariable = actualOfResult.getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualOfResult.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.expm1() instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualOfResult.variance() instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.0d, actualOfResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualOfResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualOfResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualOfResult.getVariance(), 0.0);
    assertEquals(1, actualOfResult.getGradient().size());
    assertEquals(1, actualOfResult.size());
    assertEquals(10.0d, actualOfResult.getAverage(), 0.0);
    assertEquals(10.0d, actualOfResult.getMax(), 0.0);
    assertEquals(10.0d, actualOfResult.getMin(), 0.0);
    assertEquals(3, actualOfResult.getTypePriority());
    assertTrue(actualOfResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualOfResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualOfResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualOfResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#RandomVariableDifferentiableAADStochasticNonOptimized(double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#RandomVariableDifferentiableAADStochasticNonOptimized(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADStochasticNonOptimized.<init>(double)"})
  public void testNewRandomVariableDifferentiableAADStochasticNonOptimized() {
    // Arrange and Act
    RandomVariableDifferentiableAADStochasticNonOptimized
        actualRandomVariableDifferentiableAADStochasticNonOptimized =
            new RandomVariableDifferentiableAADStochasticNonOptimized(10.0d);

    // Assert
    RandomVariable randomVariable =
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        0.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getSampleVariance(), 0.0);
    assertEquals(
        0.0d,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviation(),
        0.0);
    assertEquals(
        0.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getStandardError(), 0.0);
    assertEquals(
        0.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getVariance(), 0.0);
    assertEquals(
        1, actualRandomVariableDifferentiableAADStochasticNonOptimized.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAADStochasticNonOptimized.size());
    assertEquals(
        10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAADStochasticNonOptimized.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAADStochasticNonOptimized.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getFiltrationTime(),
        0.0);
    assertSame(
        randomVariable, actualRandomVariableDifferentiableAADStochasticNonOptimized.getValues());
    assertArrayEquals(
        new double[] {10.0d},
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#RandomVariableDifferentiableAADStochasticNonOptimized(double,
   * double[])}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#RandomVariableDifferentiableAADStochasticNonOptimized(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableDifferentiableAADStochasticNonOptimized.<init>(double, double[])"
  })
  public void testNewRandomVariableDifferentiableAADStochasticNonOptimized2() {
    // Arrange and Act
    RandomVariableDifferentiableAADStochasticNonOptimized
        actualRandomVariableDifferentiableAADStochasticNonOptimized =
            new RandomVariableDifferentiableAADStochasticNonOptimized(
                10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    RandomVariable randomVariable =
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, actualRandomVariableDifferentiableAADStochasticNonOptimized.getGradient().size());
    assertEquals(
        10.0d,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getFiltrationTime(),
        0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getMax(), 0.0);
    assertEquals(
        16.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getVariance(), 0.0);
    assertEquals(2.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getMin(), 0.0);
    assertEquals(
        2.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getStandardError(), 0.0);
    assertEquals(
        21.333333333333332d,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getSampleVariance(),
        0.0);
    assertEquals(3, actualRandomVariableDifferentiableAADStochasticNonOptimized.getTypePriority());
    assertEquals(4, actualRandomVariableDifferentiableAADStochasticNonOptimized.size());
    assertEquals(
        4.0d,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviation(),
        0.0);
    assertEquals(
        6.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getAverage(), 0.0);
    assertFalse(actualRandomVariableDifferentiableAADStochasticNonOptimized.isDeterministic());
    assertSame(
        randomVariable, actualRandomVariableDifferentiableAADStochasticNonOptimized.getValues());
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d},
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#RandomVariableDifferentiableAADStochasticNonOptimized(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#RandomVariableDifferentiableAADStochasticNonOptimized(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableDifferentiableAADStochasticNonOptimized.<init>(RandomVariable)"
  })
  public void testNewRandomVariableDifferentiableAADStochasticNonOptimized3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAADStochasticNonOptimized
        actualRandomVariableDifferentiableAADStochasticNonOptimized =
            new RandomVariableDifferentiableAADStochasticNonOptimized(randomVariable);

    // Assert
    RandomVariable randomVariable2 =
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualRandomVariableDifferentiableAADStochasticNonOptimized.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        0.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getSampleVariance(), 0.0);
    assertEquals(
        0.0d,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviation(),
        0.0);
    assertEquals(
        0.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getStandardError(), 0.0);
    assertEquals(
        0.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getVariance(), 0.0);
    assertEquals(
        1, actualRandomVariableDifferentiableAADStochasticNonOptimized.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAADStochasticNonOptimized.size());
    assertEquals(
        10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADStochasticNonOptimized.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAADStochasticNonOptimized.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAADStochasticNonOptimized.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getFiltrationTime(),
        0.0);
    assertSame(randomVariable, randomVariable2);
    assertSame(
        randomVariable, actualRandomVariableDifferentiableAADStochasticNonOptimized.getValues());
    assertArrayEquals(
        new double[] {10.0d},
        actualRandomVariableDifferentiableAADStochasticNonOptimized.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableDifferentiableAADStochasticNonOptimized#getOperatorTreeNode()}
   *   <li>{@link RandomVariableDifferentiableAADStochasticNonOptimized#getRandomVariable()}
   *   <li>{@link RandomVariableDifferentiableAADStochasticNonOptimized#getTypePriority()}
   *   <li>{@link RandomVariableDifferentiableAADStochasticNonOptimized#getValues()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADStochasticNonOptimized.OperatorTreeNode RandomVariableDifferentiableAADStochasticNonOptimized.getOperatorTreeNode()",
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getRandomVariable()",
    "int RandomVariableDifferentiableAADStochasticNonOptimized.getTypePriority()",
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getValues()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    ofResult.getOperatorTreeNode();
    RandomVariable actualRandomVariable = ofResult.getRandomVariable();
    int actualTypePriority = ofResult.getTypePriority();

    // Assert
    assertTrue(actualRandomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(3, actualTypePriority);
    assertSame(actualRandomVariable, ofResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getGradient(Set)} with {@code
   * Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map RandomVariableDifferentiableAADStochasticNonOptimized.getGradient(Set)"
  })
  public void testGetGradientWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getGradient(Set)} with {@code
   * Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map RandomVariableDifferentiableAADStochasticNonOptimized.getGradient(Set)"
  })
  public void testGetGradientWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(0L);
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getGradient(Set)} with {@code
   * Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map RandomVariableDifferentiableAADStochasticNonOptimized.getGradient(Set)"
  })
  public void testGetGradientWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(new HashSet<>()).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getTangents(Set)} with {@code
   * Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map RandomVariableDifferentiableAADStochasticNonOptimized.getTangents(Set)"
  })
  public void testGetTangentsWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getTangents(Set)} with {@code
   * Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map RandomVariableDifferentiableAADStochasticNonOptimized.getTangents(Set)"
  })
  public void testGetTangentsWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(0L);
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getTangents(Set)} with {@code
   * Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map RandomVariableDifferentiableAADStochasticNonOptimized.getTangents(Set)"
  })
  public void testGetTangentsWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(new HashSet<>()));
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD()"
  })
  public void testGetAverageAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD = ofResult.getAverageAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD()"
  })
  public void testGetAverageAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getAverageAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(100.0d, actualAverageAsRandomVariableAAD.getAverage(), 0.0);
    assertEquals(100.0d, actualAverageAsRandomVariableAAD.getMax(), 0.0);
    assertEquals(100.0d, actualAverageAsRandomVariableAAD.getMin(), 0.0);
    assertArrayEquals(
        new double[] {100.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY},
        actualAverageAsRandomVariableAAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(
            RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY},
        actualAverageAsRandomVariableAAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable_thenReturnAverageIsZero() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(probabilities);

    // Assert
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.0d, actualAverageAsRandomVariableAAD.getAverage(), 0.0);
    assertEquals(0.0d, actualAverageAsRandomVariableAAD.getMax(), 0.0);
    assertEquals(0.0d, actualAverageAsRandomVariableAAD.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverageAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getAverageAsRandomVariableAAD()"
  })
  public void testGetAverageAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualAverageAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getAverageAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAverageAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD = ofResult.getVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(
            RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(
            RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getStandardErrorAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(
            RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getStandardErrorAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        ofResult.getSampleVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualSampleVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSampleVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getSampleVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualSampleVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualSampleVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getSampleVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualSampleVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                    actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized)
                actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSampleVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getMinAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getMinAsRandomVariableAAD()"
  })
  public void testGetMinAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualMinAsRandomVariableAAD = ofResult.getMinAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMinAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getMinAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getMinAsRandomVariableAAD()"
  })
  public void testGetMinAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getMinAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualMinAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getMinAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getMinAsRandomVariableAAD()"
  })
  public void testGetMinAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getMinAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMinAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMinAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMinAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getMaxAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getMaxAsRandomVariableAAD()"
  })
  public void testGetMaxAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualMaxAsRandomVariableAAD = ofResult.getMaxAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMaxAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getMaxAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getMaxAsRandomVariableAAD()"
  })
  public void testGetMaxAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getMaxAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualMaxAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getMaxAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.getMaxAsRandomVariableAAD()"
  })
  public void testGetMaxAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getMaxAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualMaxAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMaxAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMaxAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean RandomVariableDifferentiableAADStochasticNonOptimized.equals(RandomVariable)"
  })
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized
        randomVariableDifferentiableAADStochasticNonOptimized =
            new RandomVariableDifferentiableAADStochasticNonOptimized(Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableDifferentiableAADStochasticNonOptimized.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean RandomVariableDifferentiableAADStochasticNonOptimized.equals(RandomVariable)"
  })
  public void testEqualsWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    boolean actualEqualsResult = ofResult.equals((RandomVariable) randomVariable);

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean RandomVariableDifferentiableAADStochasticNonOptimized.equals(RandomVariable)"
  })
  public void testEqualsWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals(
            (RandomVariable) new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean RandomVariableDifferentiableAADStochasticNonOptimized.equals(RandomVariable)"
  })
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean RandomVariableDifferentiableAADStochasticNonOptimized.equals(RandomVariable)"
  })
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act and Assert
    assertFalse(ofResult.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#get(int)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADStochasticNonOptimized.get(int)"})
  public void testGet_thenReturnMinusOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act and Assert
    assertEquals(-1.0d, ofResult.get(1), 0.0);
    assertEquals(1, ofResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#size()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableDifferentiableAADStochasticNonOptimized.size()"})
  public void testSize() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act
    int actualSizeResult = ofResult.size();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(4, actualSizeResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isDeterministic()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean RandomVariableDifferentiableAADStochasticNonOptimized.isDeterministic()"
  })
  public void testIsDeterministic() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act
    boolean actualIsDeterministicResult = ofResult.isDeterministic();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertFalse(actualIsDeterministicResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getRealizations()"
  })
  public void testGetRealizations() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act and Assert
    assertArrayEquals(new double[] {10.0d}, ofResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getRealizations()"
  })
  public void testGetRealizations2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d},
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getRealizations()"
  })
  public void testGetRealizations3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act
    double[] actualRealizations = ofResult.getRealizations();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertArrayEquals(new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualRealizations, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getRealizations()"
  })
  public void testGetRealizations_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getRealizations());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getAverage(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getAverage(RandomVariable)"
  })
  public void testGetAverageWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getAverage(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getAverage(RandomVariable)"
  })
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act and Assert
    assertEquals(100.0d, ofResult.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getVariance(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getVariance(RandomVariable)"
  })
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getVariance(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getVariance(RandomVariable)"
  })
  public void testGetVarianceWithRandomVariable_givenScalarWithValueIsTen_thenReturnZero() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d));

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviation(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviation(RandomVariable)"
  })
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviation(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getStandardDeviation(RandomVariable)"
  })
  public void testGetStandardDeviationWithRandomVariable_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d));

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardError(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getStandardError(RandomVariable)"
  })
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardError(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADStochasticNonOptimized.getStandardError(RandomVariable)"
  })
  public void testGetStandardErrorWithRandomVariable_givenScalarWithValueIsTen_thenReturnZero() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d));

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])} with
   * {@code intervalPoints}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(double[])"
  })
  public void testGetHistogramWithIntervalPoints() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    double[] actualHistogram =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getHistogram(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])} with
   * {@code intervalPoints}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(double[])"
  })
  public void testGetHistogramWithIntervalPoints2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])} with
   * {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(double[])"
  })
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithOne() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {});

    // Assert
    assertArrayEquals(new double[] {1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])} with
   * {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(double[])"
  })
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndOne() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   * with {@code numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    double[][] actualHistogram = ofResult.getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   * with {@code numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations2() {
    // Arrange and Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(
                RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d))
            .getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   * with {@code numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act
    double[][] actualHistogram = ofResult.getHistogram(10, 10.0d);

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {
          -56.611111111111114d,
          -44.388888888888886d,
          -32.16666666666667d,
          -19.944444444444443d,
          -7.722222222222221d,
          4.5d,
          16.72222222222222d,
          28.944444444444446d,
          41.16666666666667d,
          53.388888888888886d,
          65.61111111111111d
        },
        actualHistogram[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   * with {@code numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations4() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable);

    // Act
    double[][] actualHistogram = ofResult.getHistogram(10, 10.0d);

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   * with {@code numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADStochasticNonOptimized.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations5() {
    // Arrange
    RandomVariableFromFloatArray randomVariable = new RandomVariableFromFloatArray(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});

    // Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable)
            .getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cache()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cache()"
  })
  public void testCache() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)} with {@code
   * double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(double)"
  })
  public void testCapWithDouble() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).cap(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)} with {@code
   * double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(double)"
  })
  public void testCapWithDouble2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)} with {@code
   * double}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(double)"
  })
  public void testCapWithDouble_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertNull(actualCapResult.getRealizations());
    assertNull(actualCapResult.getOperator());
    assertNull(actualCapResult.getRealizationsStream());
    assertSame(randomVariable, actualCapResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)} with {@code
   * double}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(double)"
  })
  public void testCapWithDouble_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(RandomVariable)"
  })
  public void testCapWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(RandomVariable)"
  })
  public void testCapWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(RandomVariable)"
  })
  public void testCapWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return RandomVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(RandomVariable)"
  })
  public void testCapWithRandomVariable_thenReturnRandomVariableIsNull() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable cap =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCapResult = ofResult.cap(cap);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertNull(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable());
    assertNull(actualCapResult.getValues());
    assertEquals(3, actualCapResult.getTypePriority());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cap(RandomVariable)"
  })
  public void testCapWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCapResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)} with {@code
   * double}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(double)"
  })
  public void testFloorWithDouble() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).floor(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)} with {@code
   * double}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(double)"
  })
  public void testFloorWithDouble2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)} with {@code
   * double}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(double)"
  })
  public void testFloorWithDouble_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertNull(actualFloorResult.getRealizations());
    assertNull(actualFloorResult.getOperator());
    assertNull(actualFloorResult.getRealizationsStream());
    assertSame(randomVariable, actualFloorResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)} with {@code
   * double}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(double)"
  })
  public void testFloorWithDouble_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return RandomVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable_thenReturnRandomVariableIsNull() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable floor =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertNull(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable());
    assertNull(actualFloorResult.getValues());
    assertEquals(3, actualFloorResult.getTypePriority());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualFloorResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(RandomVariable)"
  })
  public void testAddWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddResult =
        ofResult.add(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(RandomVariable)"
  })
  public void testAddWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddResult =
        ofResult.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(RandomVariable)"
  })
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(RandomVariable)"
  })
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(RandomVariable)"
  })
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(0.0d, actualAddResult.getMax(), 0.0);
    assertEquals(0.0d, actualAddResult.getMin(), 0.0);
    assertEquals(10.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualAddResult.size());
    assertFalse(actualAddResult.isDeterministic());
    assertSame(randomVariable2, actualAddResult.getValues());
    assertArrayEquals(
        new double[] {20.0d, 9.0d, 20.0d, 9.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)} with {@code
   * value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(double)"
  })
  public void testAddWithValue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(double)"
  })
  public void testAddWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertNull(actualAddResult.getRealizations());
    assertNull(actualAddResult.getOperator());
    assertNull(actualAddResult.getRealizationsStream());
    assertSame(randomVariable, actualAddResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(double)"
  })
  public void testAddWithValue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).add(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.add(double)"
  })
  public void testAddWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(RandomVariable)"
  })
  public void testSubWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubResult =
        ofResult.sub(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(RandomVariable)"
  })
  public void testSubWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubResult =
        ofResult.sub(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(RandomVariable)"
  })
  public void testSubWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(RandomVariable)"
  })
  public void testSubWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(10.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSubResult.size());
    assertFalse(actualSubResult.isDeterministic());
    assertSame(randomVariable2, actualSubResult.getValues());
    assertArrayEquals(
        new double[] {0.0d, 11.0d, 0.0d, 11.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(RandomVariable)"
  })
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)} with {@code
   * value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(double)"
  })
  public void testSubWithValue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).sub(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(double)"
  })
  public void testSubWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
    assertNull(actualSubResult.getOperator());
    assertNull(actualSubResult.getRealizationsStream());
    assertSame(randomVariable, actualSubResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(double)"
  })
  public void testSubWithValue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).sub(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sub(double)"
  })
  public void testSubWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimized.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult =
        ofResult.mult(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(2, actualMultResult.getGradient().size());
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimized.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult =
        ofResult.mult(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimized.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult =
        ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimized.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult = ofResult.mult(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADStochasticNonOptimized.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariableDifferentiable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(0.0d, actualMultResult.getMax(), 0.0);
    assertEquals(0.0d, actualMultResult.getMin(), 0.0);
    assertEquals(10.0d, actualMultResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualMultResult.size());
    assertFalse(actualMultResult.isDeterministic());
    assertSame(randomVariable2, actualMultResult.getValues());
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)} with {@code
   * value}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.mult(double)"
  })
  public void testMultWithValue() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).mult(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)} with {@code
   * value}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.mult(double)"
  })
  public void testMultWithValue2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).mult(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.mult(double)"
  })
  public void testMultWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
    assertNull(actualMultResult.getOperator());
    assertNull(actualMultResult.getRealizationsStream());
    assertSame(randomVariable, actualMultResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.mult(double)"
  })
  public void testMultWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualMultResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(RandomVariable)"
  })
  public void testDivWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDivResult =
        ofResult.div(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(RandomVariable)"
  })
  public void testDivWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDivResult =
        ofResult.div(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(RandomVariable)"
  })
  public void testDivWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(RandomVariable)"
  })
  public void testDivWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(0.0d, actualDivResult.getMax(), 0.0);
    assertEquals(0.0d, actualDivResult.getMin(), 0.0);
    assertEquals(10.0d, actualDivResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualDivResult.size());
    assertFalse(actualDivResult.isDeterministic());
    assertSame(randomVariable2, actualDivResult.getValues());
    assertArrayEquals(
        new double[] {1.0d, -10.0d, 1.0d, -10.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(RandomVariable)"
  })
  public void testDivWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)} with {@code
   * value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(double)"
  })
  public void testDivWithValue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(double)"
  })
  public void testDivWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
    assertNull(actualDivResult.getOperator());
    assertNull(actualDivResult.getRealizationsStream());
    assertSame(randomVariable, actualDivResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(double)"
  })
  public void testDivWithValue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).div(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)} with {@code
   * value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.div(double)"
  })
  public void testDivWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDivResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.vid(RandomVariable)"
  })
  public void testVidWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVidResult =
        ofResult.vid(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.vid(RandomVariable)"
  })
  public void testVidWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVidResult =
        ofResult.vid(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.vid(RandomVariable)"
  })
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.vid(RandomVariable)"
  })
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.vid(RandomVariable)"
  })
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualVidResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(0.0d, actualVidResult.getMax(), 0.0);
    assertEquals(0.0d, actualVidResult.getMin(), 0.0);
    assertEquals(10.0d, actualVidResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualVidResult.size());
    assertFalse(actualVidResult.isDeterministic());
    assertSame(randomVariable2, actualVidResult.getValues());
    assertArrayEquals(
        new double[] {1.0d, -0.1d, 1.0d, -0.1d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.pow(double)"
  })
  public void testPow() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).pow(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualPowResult.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.pow(double)"
  })
  public void testPow_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).pow(10.0d);

    // Assert
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
    assertNull(actualPowResult.getRealizations());
    assertNull(actualPowResult.getOperator());
    assertNull(actualPowResult.getRealizationsStream());
    assertSame(randomVariable, actualPowResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.pow(double)"
  })
  public void testPow_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).pow(10.0d);

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualPowResult.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.pow(double)"
  })
  public void testPow_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualPowResult = ofResult.pow(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualPowResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualPowResult.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.bus(RandomVariable)"
  })
  public void testBusWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualBusResult =
        ofResult.bus(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.bus(RandomVariable)"
  })
  public void testBusWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualBusResult =
        ofResult.bus(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.bus(RandomVariable)"
  })
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.bus(RandomVariable)"
  })
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)} with
   * {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.bus(RandomVariable)"
  })
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualBusResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(10.0d, actualBusResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualBusResult.size());
    assertFalse(actualBusResult.isDeterministic());
    assertSame(randomVariable2, actualBusResult.getValues());
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#average()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.average()"
  })
  public void testAverage() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).average();

    // Assert
    assertTrue(
        actualAverageResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualAverageResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#average()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.average()"
  })
  public void testAverage_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).average();

    // Assert
    assertTrue(
        actualAverageResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof Scalar);
    assertTrue(actualAverageResult.isNaN() instanceof Scalar);
    assertNull(actualAverageResult.getRealizations());
    assertNull(actualAverageResult.getOperator());
    assertNull(actualAverageResult.getRealizationsStream());
    assertSame(randomVariable, randomVariable2);
    assertSame(randomVariable, actualAverageResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#average()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.average()"
  })
  public void testAverage_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAverageResult = ofResult.average();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAverageResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAverageResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAverageResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.squared()"
  })
  public void testSquared() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).squared();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSquaredResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualSquaredResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.squared()"
  })
  public void testSquared_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSquaredResult = ofResult.squared();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSquaredResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSquaredResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.squared()"
  })
  public void testSquared_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).squared();

    // Assert
    assertTrue(
        actualSquaredResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSquaredResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getMax(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getMin(), 0.0);
    assertEquals(10.0d, actualSquaredResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSquaredResult.size());
    assertFalse(actualSquaredResult.isDeterministic());
    assertSame(randomVariable2, actualSquaredResult.getValues());
    assertArrayEquals(
        new double[] {100.0d, 1.0d, 100.0d, 1.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.squared()"
  })
  public void testSquared_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).squared();

    // Assert
    assertTrue(
        actualSquaredResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSquaredResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualSquaredResult.getOperator());
    assertNull(actualSquaredResult.getRealizationsStream());
    assertSame(randomVariable, actualSquaredResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sqrt()"})
  public void testSqrt() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).sqrt();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualSqrtResult.getValues());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sqrt()"})
  public void testSqrt_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSqrtResult = ofResult.sqrt();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSqrtResult.getValues());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sqrt()"})
  public void testSqrt_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSqrtResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getMax(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getMin(), 0.0);
    assertEquals(10.0d, actualSqrtResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSqrtResult.size());
    assertFalse(actualSqrtResult.isDeterministic());
    assertSame(randomVariable2, actualSqrtResult.getValues());
    assertArrayEquals(
        new double[] {3.1622776601683795d, Double.NaN, 3.1622776601683795d, Double.NaN},
        actualSqrtResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sqrt()"})
  public void testSqrt_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSqrtResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualSqrtResult.getOperator());
    assertNull(actualSqrtResult.getRealizationsStream());
    assertSame(randomVariable, actualSqrtResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.exp()"})
  public void testExp() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualExpResult.getValues());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.exp()"})
  public void testExp_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualExpResult = ofResult.exp();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualExpResult.getValues());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.exp()"})
  public void testExp_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualExpResult.getAverage(), 0.0);
    assertEquals(0.0d, actualExpResult.getMax(), 0.0);
    assertEquals(0.0d, actualExpResult.getMin(), 0.0);
    assertEquals(10.0d, actualExpResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualExpResult.size());
    assertFalse(actualExpResult.isDeterministic());
    assertSame(randomVariable2, actualExpResult.getValues());
    assertArrayEquals(
        new double[] {
          22026.465794806718d, 0.36787944117144233d, 22026.465794806718d, 0.36787944117144233d
        },
        actualExpResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.exp()"})
  public void testExp_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualExpResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualExpResult.getOperator());
    assertNull(actualExpResult.getRealizationsStream());
    assertSame(randomVariable, actualExpResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.log()"})
  public void testLog() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).log();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualLogResult.getValues());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.log()"})
  public void testLog_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualLogResult = ofResult.log();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualLogResult.getValues());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.log()"})
  public void testLog_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualLogResult.getAverage(), 0.0);
    assertEquals(0.0d, actualLogResult.getMax(), 0.0);
    assertEquals(0.0d, actualLogResult.getMin(), 0.0);
    assertEquals(10.0d, actualLogResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualLogResult.size());
    assertFalse(actualLogResult.isDeterministic());
    assertSame(randomVariable2, actualLogResult.getValues());
    assertArrayEquals(
        new double[] {2.302585092994046d, Double.NaN, 2.302585092994046d, Double.NaN},
        actualLogResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.log()"})
  public void testLog_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualLogResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualLogResult.getOperator());
    assertNull(actualLogResult.getRealizationsStream());
    assertSame(randomVariable, actualLogResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sin()"})
  public void testSin() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).sin();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualSinResult.getValues());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sin()"})
  public void testSin_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSinResult = ofResult.sin();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSinResult.getValues());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sin()"})
  public void testSin_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSinResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSinResult.getMax(), 0.0);
    assertEquals(0.0d, actualSinResult.getMin(), 0.0);
    assertEquals(10.0d, actualSinResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSinResult.size());
    assertFalse(actualSinResult.isDeterministic());
    assertSame(randomVariable2, actualSinResult.getValues());
    assertArrayEquals(
        new double[] {
          -0.5440211108893698d, -0.8414709848078965d, -0.5440211108893698d, -0.8414709848078965d
        },
        actualSinResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.sin()"})
  public void testSin_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSinResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualSinResult.getOperator());
    assertNull(actualSinResult.getRealizationsStream());
    assertSame(randomVariable, actualSinResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cos()"})
  public void testCos() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualCosResult.getValues());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cos()"})
  public void testCos_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualCosResult = ofResult.cos();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualCosResult.getValues());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cos()"})
  public void testCos_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualCosResult.getAverage(), 0.0);
    assertEquals(0.0d, actualCosResult.getMax(), 0.0);
    assertEquals(0.0d, actualCosResult.getMin(), 0.0);
    assertEquals(10.0d, actualCosResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualCosResult.size());
    assertFalse(actualCosResult.isDeterministic());
    assertSame(randomVariable2, actualCosResult.getValues());
    assertArrayEquals(
        new double[] {
          -0.8390715290764524d, 0.5403023058681398d, -0.8390715290764524d, 0.5403023058681398d
        },
        actualCosResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.cos()"})
  public void testCos_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualCosResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualCosResult.getOperator());
    assertNull(actualCosResult.getRealizationsStream());
    assertSame(randomVariable, actualCosResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable,
   * double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.accrue(RandomVariable, double)"
  })
  public void testAccrue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAADStochasticNonOptimized.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable,
   * double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAADPathwise.of(1.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable,
   * double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAccrueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable,
   * double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1010.0}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnAverageIs10100() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable,
   * double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.discount(RandomVariable, double)"
  })
  public void testDiscount() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAADStochasticNonOptimized.of(1.0d), 10.0d);

    // Assert
    assertTrue(
        actualDiscountResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDiscountResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDiscountResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualDiscountResult.getValues());
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable,
   * double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.discount(RandomVariable, double)"
  })
  public void testDiscount_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAADPathwise.of(1.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDiscountResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualDiscountResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDiscountResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualDiscountResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDiscountResult.getValues());
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable,
   * double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.09900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs009900990099009901() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(
        actualDiscountResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable,
   * double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then return Average is {@code 0.9090909090909091}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.discount(RandomVariable, double)"
  })
  public void testDiscount_whenScalarWithValueIsOne_thenReturnAverageIs09090909090909091() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(
        actualDiscountResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(0.9090909090909091d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized valueIfTriggerNonNegative =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(
            valueIfTriggerNonNegative,
            RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableDifferentiableAADPathwiseWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADPathwise valueIfTriggerNonNegative =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableDifferentiableAADPathwiseWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(
            valueIfTriggerNonNegative, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
    assertNull(actualChooseResult.getRealizations());
    assertNull(actualChooseResult.getOperator());
    assertNull(actualChooseResult.getRealizationsStream());
    assertSame(valueIfTriggerNonNegative, randomVariable);
    assertSame(valueIfTriggerNonNegative, actualChooseResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualChooseResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#invert()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.invert()"
  })
  public void testInvert() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});

    // Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).invert();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualInvertResult.getValues());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#invert()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.invert()"
  })
  public void testInvert_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualInvertResult.getOperator());
    assertNull(actualInvertResult.getRealizationsStream());
    assertSame(randomVariable, actualInvertResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#invert()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.invert()"
  })
  public void testInvert_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualInvertResult = ofResult.invert();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualInvertResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualInvertResult.getValues());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#abs()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.abs()"})
  public void testAbs() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualAbsResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualAbsResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#abs()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.abs()"})
  public void testAbs_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAbsResult = ofResult.abs();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAbsResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#abs()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.abs()"})
  public void testAbs_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualAbsResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAbsResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualAbsResult.getOperator());
    assertNull(actualAbsResult.getRealizationsStream());
    assertSame(randomVariable, actualAbsResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * double)} with {@code RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(Scalar.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * double)} with {@code RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NEGATIVE_INFINITY),
            10.0d);

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * double)} with {@code RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * double)} with {@code RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return Average is one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnAverageIsOneHundredTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(110.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMin(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized factor1 =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 0.0d, 110.0d, 0.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable9() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable10() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable11() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized factor1 =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable12() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable factor2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 11.0d, 110.0d, 11.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable13() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 0.0d, 110.0d, 0.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable14() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 0.0d, 110.0d, 0.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable15() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnGradientSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized factor1 =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddProductResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        3,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddProductResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio4() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);
    RandomVariableUniqueVariable denominator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 0.0d, 11.0d, 0.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise4() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        3,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd99() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd992() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd993() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndZero() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableUniqueVariable denominator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 0.0d, 11.0d, 0.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        actualAddRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualAddRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        3,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableFromFloatArrayWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertTrue(
        actualSubRatioResult instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAADStochasticNonOptimized) actualSubRatioResult)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.isNaN()"
  })
  public void testIsNaN() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualIsNaNResult = ofResult.isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.isNaN()"
  })
  public void testIsNaN2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.isNaN()"
  })
  public void testIsNaN_givenScalarWithValueIsTen_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d)).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof Scalar);
    assertTrue(actualIsNaNResult.abs() instanceof Scalar);
    assertTrue(actualIsNaNResult.cos() instanceof Scalar);
    assertTrue(actualIsNaNResult.exp() instanceof Scalar);
    assertTrue(actualIsNaNResult.expm1() instanceof Scalar);
    assertTrue(actualIsNaNResult.invert() instanceof Scalar);
    assertTrue(actualIsNaNResult.isNaN() instanceof Scalar);
    assertTrue(actualIsNaNResult.sin() instanceof Scalar);
    assertTrue(actualIsNaNResult.sqrt() instanceof Scalar);
    assertTrue(actualIsNaNResult.squared() instanceof Scalar);
    assertTrue(actualIsNaNResult.variance() instanceof Scalar);
    assertNull(actualIsNaNResult.getRealizations());
    assertNull(actualIsNaNResult.getOperator());
    assertNull(actualIsNaNResult.getRealizationsStream());
    assertEquals(0, actualIsNaNResult.getTypePriority());
    assertEquals(0.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMin(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getVariance(), 0.0);
    assertEquals(1, actualIsNaNResult.size());
    assertTrue(actualIsNaNResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualIsNaNResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualIsNaNResult.expectation();
    assertSame(actualIsNaNResult, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.isNaN()"
  })
  public void testIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.isNaN()"
  })
  public void testIsNaN_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(4, actualIsNaNResult.size());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.17677669529663687}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADStochasticNonOptimized#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.isNaN()"
  })
  public void testIsNaN_thenReturnStandardErrorIs017677669529663687() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {
              Double.NaN, 10.0d, Double.NaN, 10.0d, Double.NaN, 10.0d, Double.NaN, 10.0d
            });
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(randomVariable).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.17677669529663687d, actualIsNaNResult.getStandardError(), 0.0);
    assertEquals(0.25d, actualIsNaNResult.getVariance(), 0.0);
    assertEquals(0.2857142857142857d, actualIsNaNResult.getSampleVariance(), 0.0);
    assertEquals(0.5d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(0.5d, actualIsNaNResult.getStandardDeviation(), 0.0);
    assertEquals(8, actualIsNaNResult.size());
    assertArrayEquals(
        new double[] {1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d},
        actualIsNaNResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#getRealizationsStream()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#getRealizationsStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.stream.DoubleStream RandomVariableDifferentiableAADStochasticNonOptimized.getRealizationsStream()"
  })
  public void testGetRealizationsStream_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        RandomVariableDifferentiableAADStochasticNonOptimized.of(Scalar.of(10.0d))
            .getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#apply(DoubleUnaryOperator)}
   * with {@code operator}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.apply(DoubleUnaryOperator)"
  })
  public void testApplyWithOperator() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.apply(mock(DoubleUnaryOperator.class)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#apply(DoubleBinaryOperator,
   * RandomVariable)} with {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADStochasticNonOptimized#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADStochasticNonOptimized#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADStochasticNonOptimized.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized ofResult =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
  }
}
