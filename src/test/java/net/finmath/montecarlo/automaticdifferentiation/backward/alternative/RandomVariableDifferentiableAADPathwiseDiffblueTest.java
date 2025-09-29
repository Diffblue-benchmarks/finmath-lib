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

public class RandomVariableDifferentiableAADPathwiseDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#of(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#of(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADPathwise RandomVariableDifferentiableAADPathwise.of(RandomVariable)"
  })
  public void testOfWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAADPathwise actualOfResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Assert
    RandomVariable randomVariable2 = actualOfResult.getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualOfResult.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualOfResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
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
   * Test {@link RandomVariableDifferentiableAADPathwise#of(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#of(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADPathwise RandomVariableDifferentiableAADPathwise.of(double)"
  })
  public void testOfWithValue() {
    // Arrange and Act
    RandomVariableDifferentiableAADPathwise actualOfResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Assert
    RandomVariable randomVariable = actualOfResult.getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualOfResult.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualOfResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
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
   * RandomVariableDifferentiableAADPathwise#RandomVariableDifferentiableAADPathwise(double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#RandomVariableDifferentiableAADPathwise(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADPathwise.<init>(double)"})
  public void testNewRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariableDifferentiableAADPathwise actualRandomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d);

    // Assert
    RandomVariable randomVariable =
        actualRandomVariableDifferentiableAADPathwise.getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableDifferentiableAADPathwise.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAADPathwise.size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAADPathwise.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAADPathwise.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualRandomVariableDifferentiableAADPathwise.getFiltrationTime(),
        0.0);
    assertSame(randomVariable, actualRandomVariableDifferentiableAADPathwise.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAADPathwise.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#RandomVariableDifferentiableAADPathwise(double,
   * double[])}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#RandomVariableDifferentiableAADPathwise(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADPathwise.<init>(double, double[])"})
  public void testNewRandomVariableDifferentiableAADPathwise2() {
    // Arrange and Act
    RandomVariableDifferentiableAADPathwise actualRandomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    RandomVariable randomVariable =
        actualRandomVariableDifferentiableAADPathwise.getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, actualRandomVariableDifferentiableAADPathwise.getGradient().size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getMax(), 0.0);
    assertEquals(16.0d, actualRandomVariableDifferentiableAADPathwise.getVariance(), 0.0);
    assertEquals(2.0d, actualRandomVariableDifferentiableAADPathwise.getMin(), 0.0);
    assertEquals(2.0d, actualRandomVariableDifferentiableAADPathwise.getStandardError(), 0.0);
    assertEquals(
        21.333333333333332d,
        actualRandomVariableDifferentiableAADPathwise.getSampleVariance(),
        0.0);
    assertEquals(3, actualRandomVariableDifferentiableAADPathwise.getTypePriority());
    assertEquals(4, actualRandomVariableDifferentiableAADPathwise.size());
    assertEquals(4.0d, actualRandomVariableDifferentiableAADPathwise.getStandardDeviation(), 0.0);
    assertEquals(6.0d, actualRandomVariableDifferentiableAADPathwise.getAverage(), 0.0);
    assertFalse(actualRandomVariableDifferentiableAADPathwise.isDeterministic());
    assertSame(randomVariable, actualRandomVariableDifferentiableAADPathwise.getValues());
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d},
        actualRandomVariableDifferentiableAADPathwise.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#RandomVariableDifferentiableAADPathwise(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#RandomVariableDifferentiableAADPathwise(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADPathwise.<init>(RandomVariable)"})
  public void testNewRandomVariableDifferentiableAADPathwise3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAADPathwise actualRandomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(randomVariable);

    // Assert
    RandomVariable randomVariable2 =
        actualRandomVariableDifferentiableAADPathwise.getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwise.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAADPathwise.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableDifferentiableAADPathwise.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAADPathwise.size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAADPathwise.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAADPathwise.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAADPathwise.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualRandomVariableDifferentiableAADPathwise.getFiltrationTime(),
        0.0);
    assertSame(randomVariable, randomVariable2);
    assertSame(randomVariable, actualRandomVariableDifferentiableAADPathwise.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAADPathwise.getRealizations(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableDifferentiableAADPathwise#getOperatorTreeNode()}
   *   <li>{@link RandomVariableDifferentiableAADPathwise#getRandomVariable()}
   *   <li>{@link RandomVariableDifferentiableAADPathwise#getTypePriority()}
   *   <li>{@link RandomVariableDifferentiableAADPathwise#getValues()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADPathwise.OperatorTreeNode RandomVariableDifferentiableAADPathwise.getOperatorTreeNode()",
    "RandomVariable RandomVariableDifferentiableAADPathwise.getRandomVariable()",
    "int RandomVariableDifferentiableAADPathwise.getTypePriority()",
    "RandomVariable RandomVariableDifferentiableAADPathwise.getValues()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

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
   * Test {@link RandomVariableDifferentiableAADPathwise#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiableAADPathwise.getGradient(Set)"})
  public void testGetGradientWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiableAADPathwise.getGradient(Set)"})
  public void testGetGradientWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(0L);
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiableAADPathwise.getGradient(Set)"})
  public void testGetGradientWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(new HashSet<>()).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiableAADPathwise.getTangents(Set)"})
  public void testGetTangentsWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiableAADPathwise.getTangents(Set)"})
  public void testGetTangentsWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(0L);
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiableAADPathwise.getTangents(Set)"})
  public void testGetTangentsWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(new HashSet<>()));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD()"
  })
  public void testGetAverageAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD = ofResult.getAverageAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD()"
  })
  public void testGetAverageAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).getAverageAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(
            RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetAverageAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualAverageAsRandomVariableAAD =
        ofResult.getAverageAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverageAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getAverageAsRandomVariableAAD()"
  })
  public void testGetAverageAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualAverageAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d))
            .getAverageAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAverageAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualAverageAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualAverageAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualAverageAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD = ofResult.getVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).getVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(
            RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetVarianceAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        ofResult.getVarianceAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d))
            .getVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable)
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(
            RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardDeviationAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d))
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardDeviationAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable)
            .getStandardErrorAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(Scalar.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(
            RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   * with {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD(RandomVariable)"
  })
  public void testGetStandardErrorAsRandomVariableAADWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable probabilities =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD(probabilities);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d))
            .getStandardErrorAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualStandardErrorAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        ofResult.getSampleVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSampleVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable)
            .getSampleVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable2, actualSampleVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d))
            .getSampleVarianceAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualSampleVarianceAsRandomVariableAAD.getValues());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getMinAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getMinAsRandomVariableAAD()"
  })
  public void testGetMinAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualMinAsRandomVariableAAD = ofResult.getMinAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMinAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getMinAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getMinAsRandomVariableAAD()"
  })
  public void testGetMinAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).getMinAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualMinAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getMinAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getMinAsRandomVariableAAD()"
  })
  public void testGetMinAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).getMinAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualMinAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMinAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getMaxAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getMaxAsRandomVariableAAD()"
  })
  public void testGetMaxAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualMaxAsRandomVariableAAD = ofResult.getMaxAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMaxAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getMaxAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getMaxAsRandomVariableAAD()"
  })
  public void testGetMaxAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).getMaxAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualMaxAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getMaxAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.getMaxAsRandomVariableAAD()"
  })
  public void testGetMaxAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).getMaxAsRandomVariableAAD();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualMaxAsRandomVariableAAD)
            .getGradient()
            .size());
    assertSame(randomVariable, actualMaxAsRandomVariableAAD.getValues());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAADPathwise.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableDifferentiableAADPathwise.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAADPathwise.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
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
   * Test {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAADPathwise.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals(
            (RandomVariable) new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAADPathwise.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAADPathwise.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act and Assert
    assertFalse(ofResult.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#get(int)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADPathwise.get(int)"})
  public void testGet_thenReturnMinusOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Act and Assert
    assertEquals(-1.0d, ofResult.get(1), 0.0);
    assertEquals(1, ofResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#size()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableDifferentiableAADPathwise.size()"})
  public void testSize() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Act
    int actualSizeResult = ofResult.size();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(4, actualSizeResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#isDeterministic()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAADPathwise.isDeterministic()"})
  public void testIsDeterministic() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Act
    boolean actualIsDeterministicResult = ofResult.isDeterministic();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertFalse(actualIsDeterministicResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getRealizations()"})
  public void testGetRealizations() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d},
        RandomVariableDifferentiableAADPathwise.of(randomVariable).getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getRealizations()"})
  public void testGetRealizations2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Act
    double[] actualRealizations = ofResult.getRealizations();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertArrayEquals(new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualRealizations, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getRealizations()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getRealizations()"})
  public void testGetRealizations_givenRandomVariableDifferentiableAADPathwiseWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act and Assert
    assertArrayEquals(new double[] {10.0d}, ofResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getRealizations()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getRealizations()"})
  public void testGetRealizations_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).getRealizations());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADPathwise.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(10.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADPathwise.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(10.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADPathwise.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d));

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(10.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADPathwise.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADPathwise.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_givenScalarWithValueIsTen_thenReturnZero() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d));

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardDeviation(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADPathwise.getStandardDeviation(RandomVariable)"
  })
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardDeviation(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADPathwise.getStandardDeviation(RandomVariable)"
  })
  public void testGetStandardDeviationWithRandomVariable_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d));

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardError(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADPathwise.getStandardError(RandomVariable)"
  })
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getStandardError(RandomVariable)} with
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADPathwise.getStandardError(RandomVariable)"
  })
  public void testGetStandardErrorWithRandomVariable_givenScalarWithValueIsTen_thenReturnZero() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d));

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    double[] actualHistogram =
        RandomVariableDifferentiableAADPathwise.of(randomVariable)
            .getHistogram(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithOne() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {});

    // Assert
    assertArrayEquals(new double[] {1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAADPathwise.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndOne() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADPathwise.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

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
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADPathwise.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations2() {
    // Arrange and Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAADPathwise.of(
                RandomVariableDifferentiableAADPathwise.of(10.0d))
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
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADPathwise.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

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
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADPathwise.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations4() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable);

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
   * Test {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] RandomVariableDifferentiableAADPathwise.getHistogram(int, double)"
  })
  public void testGetHistogramWithNumberOfPointsStandardDeviations5() {
    // Arrange
    RandomVariableFromFloatArray randomVariable = new RandomVariableFromFloatArray(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});

    // Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).getHistogram(10, 10.0d);

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
   * Test {@link RandomVariableDifferentiableAADPathwise#cache()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cache()"})
  public void testCache() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(double)"})
  public void testCapWithDouble() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable2, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(double)"})
  public void testCapWithDouble2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable2, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(double)"})
  public void testCapWithDouble_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertNull(actualCapResult.getRealizations());
    assertNull(actualCapResult.getOperator());
    assertNull(actualCapResult.getRealizationsStream());
    assertSame(randomVariable, actualCapResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(double)"})
  public void testCapWithDouble_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(RandomVariable)"})
  public void testCapWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(RandomVariable)"})
  public void testCapWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return RandomVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnRandomVariableIsNull() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable cap =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCapResult = ofResult.cap(cap);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertNull(((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable());
    assertNull(actualCapResult.getValues());
    assertEquals(3, actualCapResult.getTypePriority());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualCapResult).getGradient().size());
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.floor(double)"})
  public void testFloorWithDouble() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable2, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.floor(double)"})
  public void testFloorWithDouble2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable2, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.floor(double)"})
  public void testFloorWithDouble_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertNull(actualFloorResult.getRealizations());
    assertNull(actualFloorResult.getOperator());
    assertNull(actualFloorResult.getRealizationsStream());
    assertSame(randomVariable, actualFloorResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.floor(double)"})
  public void testFloorWithDouble_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return RandomVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable_thenReturnRandomVariableIsNull() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable floor =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertNull(((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable());
    assertNull(actualFloorResult.getValues());
    assertEquals(3, actualFloorResult.getTypePriority());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.floor(RandomVariable)"
  })
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getGradient().size());
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(RandomVariable)"})
  public void testAddWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddResult =
        ofResult.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddResult).getGradient().size());
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(RandomVariable)"})
  public void testAddWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddResult =
        ofResult.add(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#add(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(double)"})
  public void testAddWithValue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(double)"})
  public void testAddWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertNull(actualAddResult.getRealizations());
    assertNull(actualAddResult.getOperator());
    assertNull(actualAddResult.getRealizationsStream());
    assertSame(randomVariable, actualAddResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(double)"})
  public void testAddWithValue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.add(double)"})
  public void testAddWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(RandomVariable)"})
  public void testSubWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubResult =
        ofResult.sub(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualSubResult).getGradient().size());
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(RandomVariable)"})
  public void testSubWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubResult =
        ofResult.sub(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(10.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSubResult.size());
    assertFalse(actualSubResult.isDeterministic());
    assertSame(randomVariable2, actualSubResult.getValues());
    assertArrayEquals(
        new double[] {0.0d, 11.0d, 0.0d, 11.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(double)"})
  public void testSubWithValue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).sub(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(double)"})
  public void testSubWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
    assertNull(actualSubResult.getOperator());
    assertNull(actualSubResult.getRealizationsStream());
    assertSame(randomVariable, actualSubResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(double)"})
  public void testSubWithValue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).sub(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sub(double)"})
  public void testSubWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwise.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult =
        ofResult.mult(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(2, actualMultResult.getGradient().size());
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwise.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult =
        ofResult.mult(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwise.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult =
        ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwise.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariableDifferentiable actualMultResult = ofResult.mult(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwise.mult(RandomVariable)"
  })
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariableDifferentiable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.mult(double)"})
  public void testMultWithValue() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).mult(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.mult(double)"})
  public void testMultWithValue2() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).mult(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.mult(double)"})
  public void testMultWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
    assertNull(actualMultResult.getOperator());
    assertNull(actualMultResult.getRealizationsStream());
    assertSame(randomVariable, actualMultResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.mult(double)"})
  public void testMultWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(RandomVariable)"})
  public void testDivWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDivResult =
        ofResult.div(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualDivResult).getGradient().size());
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(RandomVariable)"})
  public void testDivWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDivResult =
        ofResult.div(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(double)"})
  public void testDivWithValue() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(double)"})
  public void testDivWithValue_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
    assertNull(actualDivResult.getOperator());
    assertNull(actualDivResult.getRealizationsStream());
    assertSame(randomVariable, actualDivResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(double)"})
  public void testDivWithValue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.div(double)"})
  public void testDivWithValue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.vid(RandomVariable)"})
  public void testVidWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVidResult =
        ofResult.vid(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualVidResult).getGradient().size());
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.vid(RandomVariable)"})
  public void testVidWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVidResult =
        ofResult.vid(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#pow(double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.pow(double)"})
  public void testPow() {
    // Arrange
    RandomVariableDifferentiableAADStochasticNonOptimized randomVariable =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).pow(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable2, actualPowResult.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#pow(double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.pow(double)"})
  public void testPow_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).pow(10.0d);

    // Assert
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
    assertNull(actualPowResult.getRealizations());
    assertNull(actualPowResult.getOperator());
    assertNull(actualPowResult.getRealizationsStream());
    assertSame(randomVariable, actualPowResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#pow(double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.pow(double)"})
  public void testPow_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).pow(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualPowResult.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#pow(double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.pow(double)"})
  public void testPow_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualPowResult = ofResult.pow(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualPowResult.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.bus(RandomVariable)"})
  public void testBusWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualBusResult =
        ofResult.bus(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualBusResult).getGradient().size());
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.bus(RandomVariable)"})
  public void testBusWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualBusResult =
        ofResult.bus(RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableUniqueVariable);
    assertEquals(10.0d, actualBusResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualBusResult.size());
    assertFalse(actualBusResult.isDeterministic());
    assertSame(randomVariable2, actualBusResult.getValues());
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#average()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.average()"})
  public void testAverage_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof Scalar);
    assertTrue(actualAverageResult.isNaN() instanceof Scalar);
    assertNull(actualAverageResult.getRealizations());
    assertNull(actualAverageResult.getOperator());
    assertNull(actualAverageResult.getRealizationsStream());
    assertSame(randomVariable, randomVariable2);
    assertSame(randomVariable, actualAverageResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#average()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.average()"})
  public void testAverage_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualAverageResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#average()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.average()"})
  public void testAverage_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAverageResult = ofResult.average();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAverageResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAverageResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.squared()"})
  public void testSquared_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).squared();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualSquaredResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.squared()"})
  public void testSquared_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSquaredResult = ofResult.squared();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSquaredResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.squared()"})
  public void testSquared_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#squared()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.squared()"})
  public void testSquared_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSquaredResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualSquaredResult.getOperator());
    assertNull(actualSquaredResult.getRealizationsStream());
    assertSame(randomVariable, actualSquaredResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sqrt()"})
  public void testSqrt_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).sqrt();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualSqrtResult.getValues());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sqrt()"})
  public void testSqrt_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSqrtResult = ofResult.sqrt();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSqrtResult.getValues());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sqrt()"})
  public void testSqrt_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#sqrt()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sqrt()"})
  public void testSqrt_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSqrtResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualSqrtResult.getOperator());
    assertNull(actualSqrtResult.getRealizationsStream());
    assertSame(randomVariable, actualSqrtResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.exp()"})
  public void testExp_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualExpResult.getValues());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.exp()"})
  public void testExp_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualExpResult = ofResult.exp();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualExpResult.getValues());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.exp()"})
  public void testExp_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#exp()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.exp()"})
  public void testExp_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualExpResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualExpResult.getOperator());
    assertNull(actualExpResult.getRealizationsStream());
    assertSame(randomVariable, actualExpResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.log()"})
  public void testLog_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).log();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualLogResult.getValues());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.log()"})
  public void testLog_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualLogResult = ofResult.log();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualLogResult.getValues());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.log()"})
  public void testLog_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#log()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.log()"})
  public void testLog_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualLogResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualLogResult.getOperator());
    assertNull(actualLogResult.getRealizationsStream());
    assertSame(randomVariable, actualLogResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sin()"})
  public void testSin_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).sin();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualSinResult.getValues());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sin()"})
  public void testSin_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSinResult = ofResult.sin();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSinResult.getValues());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sin()"})
  public void testSin_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#sin()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.sin()"})
  public void testSin_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSinResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualSinResult.getOperator());
    assertNull(actualSinResult.getRealizationsStream());
    assertSame(randomVariable, actualSinResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cos()"})
  public void testCos_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualCosResult.getValues());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cos()"})
  public void testCos_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualCosResult = ofResult.cos();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualCosResult.getValues());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cos()"})
  public void testCos_thenRandomVariableReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getRandomVariable();
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
   * Test {@link RandomVariableDifferentiableAADPathwise#cos()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.cos()"})
  public void testCos_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCosResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualCosResult.getOperator());
    assertNull(actualCosResult.getRealizationsStream());
    assertSame(randomVariable, actualCosResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.accrue(RandomVariable, double)"
  })
  public void testAccrue() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAADStochasticNonOptimized.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAADPathwise.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getGradient().size());
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1010.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#accrue(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnAverageIs10100() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.discount(RandomVariable, double)"
  })
  public void testDiscount() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAADStochasticNonOptimized.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualDiscountResult.getValues());
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.discount(RandomVariable, double)"
  })
  public void testDiscount_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAADPathwise.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getGradient().size());
    assertSame(randomVariable, actualDiscountResult.getValues());
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.09900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs009900990099009901() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then return Average is {@code 0.9090909090909091}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#discount(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.discount(RandomVariable, double)"
  })
  public void testDiscount_whenScalarWithValueIsOne_thenReturnAverageIs09090909090909091() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.9090909090909091d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized valueIfTriggerNonNegative =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getGradient().size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(
            valueIfTriggerNonNegative,
            RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getGradient().size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getGradient().size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise valueIfTriggerNonNegative =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getGradient().size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(
            valueIfTriggerNonNegative, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getGradient().size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
    assertNull(actualChooseResult.getRealizations());
    assertNull(actualChooseResult.getOperator());
    assertNull(actualChooseResult.getRealizationsStream());
    assertSame(valueIfTriggerNonNegative, randomVariable);
    assertSame(valueIfTriggerNonNegative, actualChooseResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualChooseResult).getGradient().size());
    assertSame(randomVariable, actualChooseResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#invert()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.invert()"})
  public void testInvert_givenScalarWithValueIsTen_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualInvertResult.getOperator());
    assertNull(actualInvertResult.getRealizationsStream());
    assertSame(randomVariable, actualInvertResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#invert()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.invert()"})
  public void testInvert_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});

    // Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).invert();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable2, actualInvertResult.getValues());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#invert()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.invert()"})
  public void testInvert_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualInvertResult = ofResult.invert();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualInvertResult.getValues());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#abs()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.abs()"})
  public void testAbs_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualAbsResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getGradient().size());
    assertSame(randomVariable2, actualAbsResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#abs()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.abs()"})
  public void testAbs_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAbsResult = ofResult.abs();

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getGradient().size());
    assertSame(randomVariable, actualAbsResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#abs()}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.abs()"})
  public void testAbs_thenRandomVariableReturnScalar() {
    // Arrange and Act
    RandomVariable actualAbsResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAbsResult).getRandomVariable();
    assertTrue(randomVariable instanceof Scalar);
    assertNull(actualAbsResult.getOperator());
    assertNull(actualAbsResult.getRealizationsStream());
    assertSame(randomVariable, actualAbsResult.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, double)} with
   * {@code RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(Scalar.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, double)} with
   * {@code RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, double)} with
   * {@code RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            RandomVariableDifferentiableAADStochasticNonOptimized.of(Double.NEGATIVE_INFINITY),
            10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, double)} with
   * {@code RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return Average is one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnAverageIsOneHundredTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(110.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMin(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized factor1 =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 0.0d, 110.0d, 0.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable9() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable10() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable11() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable12() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable factor2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 11.0d, 110.0d, 11.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable13() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 0.0d, 110.0d, 0.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable14() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(
        new double[] {110.0d, 0.0d, 110.0d, 0.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable15() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable, RandomVariable)}
   * with {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnGradientSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        3, ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getGradient().size());
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);
    RandomVariableUniqueVariable denominator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 0.0d, 11.0d, 0.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableDifferentiableAADPathwise4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        3, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd99() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd992() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd993() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndZero() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableUniqueVariable denominator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableUniqueVariable);
    assertEquals(
        1, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(
        new double[] {11.0d, 0.0d, 11.0d, 0.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getGradient().size());
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADStochasticNonOptimized numerator =
        RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        3, ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getGradient().size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getGradient().size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getGradient().size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getGradient().size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableFromFloatArrayWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(
            numerator, RandomVariableDifferentiableAADStochasticNonOptimized.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADStochasticNonOptimized);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getGradient().size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        2, ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getGradient().size());
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.isNaN()"})
  public void testIsNaN() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).isNaN();

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
   * Test {@link RandomVariableDifferentiableAADPathwise#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.isNaN()"})
  public void testIsNaN_givenRandomVariableDifferentiableAADPathwiseWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

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
   * Test {@link RandomVariableDifferentiableAADPathwise#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.isNaN()"})
  public void testIsNaN_givenScalarWithValueIsTen_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).isNaN();

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
   * Test {@link RandomVariableDifferentiableAADPathwise#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.isNaN()"})
  public void testIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADPathwise.of(Double.NaN).isNaN();

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
   * Test {@link RandomVariableDifferentiableAADPathwise#isNaN()}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.isNaN()"})
  public void testIsNaN_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAADPathwise.of(randomVariable).isNaN();

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
   * Test {@link RandomVariableDifferentiableAADPathwise#isNaN()}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.17677669529663687}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAADPathwise.isNaN()"})
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
        RandomVariableDifferentiableAADPathwise.of(randomVariable).isNaN();

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
   * Test {@link RandomVariableDifferentiableAADPathwise#getRealizationsStream()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADPathwise#getRealizationsStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.stream.DoubleStream RandomVariableDifferentiableAADPathwise.getRealizationsStream()"
  })
  public void testGetRealizationsStream_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        RandomVariableDifferentiableAADPathwise.of(Scalar.of(10.0d)).getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#apply(DoubleUnaryOperator)} with {@code
   * operator}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.apply(DoubleUnaryOperator)"
  })
  public void testApplyWithOperator() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.apply(mock(DoubleUnaryOperator.class)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#apply(DoubleBinaryOperator,
   * RandomVariable)} with {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#apply(DoubleBinaryOperator, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwise#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwise#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAADPathwise.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
  }
}
