package net.finmath.montecarlo.automaticdifferentiation.forward;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.stochastic.ConditionalExpectationEstimator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class RandomVariableDifferentiableADDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiableAD#of(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#of(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAD RandomVariableDifferentiableAD.of(RandomVariable)"
  })
  public void testOfWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAD actualOfResult =
        RandomVariableDifferentiableAD.of(randomVariable);

    // Assert
    RandomVariable values = actualOfResult.getValues();
    assertTrue(values instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualOfResult.variance() instanceof RandomVariableDifferentiableAD);
    assertNull(actualOfResult.getTangents());
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
    assertSame(randomVariable, values);
    assertArrayEquals(new double[] {10.0d}, actualOfResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#of(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#of(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableDifferentiableAD RandomVariableDifferentiableAD.of(double)"})
  public void testOfWithValue() {
    // Arrange and Act
    RandomVariableDifferentiableAD actualOfResult = RandomVariableDifferentiableAD.of(10.0d);

    // Assert
    assertTrue(actualOfResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualOfResult.variance() instanceof RandomVariableDifferentiableAD);
    assertNull(actualOfResult.getTangents());
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
    assertArrayEquals(new double[] {10.0d}, actualOfResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#RandomVariableDifferentiableAD(double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#RandomVariableDifferentiableAD(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAD.<init>(double)"})
  public void testNewRandomVariableDifferentiableAD() {
    // Arrange and Act
    RandomVariableDifferentiableAD actualRandomVariableDifferentiableAD =
        new RandomVariableDifferentiableAD(10.0d);

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAD.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.variance() instanceof RandomVariableDifferentiableAD);
    assertNull(actualRandomVariableDifferentiableAD.getTangents());
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableDifferentiableAD.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAD.size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAD.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAD.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableDifferentiableAD.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#RandomVariableDifferentiableAD(double, double[])}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#RandomVariableDifferentiableAD(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAD.<init>(double, double[])"})
  public void testNewRandomVariableDifferentiableAD2() {
    // Arrange and Act
    RandomVariableDifferentiableAD actualRandomVariableDifferentiableAD =
        new RandomVariableDifferentiableAD(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAD.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.variance() instanceof RandomVariableDifferentiableAD);
    assertNull(actualRandomVariableDifferentiableAD.getTangents());
    assertEquals(-1.0d, actualRandomVariableDifferentiableAD.getMin(), 0.0);
    assertEquals(1, actualRandomVariableDifferentiableAD.getGradient().size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getMax(), 0.0);
    assertEquals(2.75d, actualRandomVariableDifferentiableAD.getStandardError(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAD.getTypePriority());
    assertEquals(30.25d, actualRandomVariableDifferentiableAD.getVariance(), 0.0);
    assertEquals(4, actualRandomVariableDifferentiableAD.size());
    assertEquals(4.5d, actualRandomVariableDifferentiableAD.getAverage(), 0.0);
    assertEquals(
        40.333333333333336d, actualRandomVariableDifferentiableAD.getSampleVariance(), 0.0);
    assertEquals(5.5d, actualRandomVariableDifferentiableAD.getStandardDeviation(), 0.0);
    assertFalse(actualRandomVariableDifferentiableAD.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableDifferentiableAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#RandomVariableDifferentiableAD(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#RandomVariableDifferentiableAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAD.<init>(RandomVariable)"})
  public void testNewRandomVariableDifferentiableAD3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAD actualRandomVariableDifferentiableAD =
        new RandomVariableDifferentiableAD(randomVariable);

    // Assert
    RandomVariable values = actualRandomVariableDifferentiableAD.getValues();
    assertTrue(values instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAD.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableAD.variance() instanceof RandomVariableDifferentiableAD);
    assertNull(actualRandomVariableDifferentiableAD.getTangents());
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAD.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableDifferentiableAD.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAD.size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAD.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAD.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAD.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableDifferentiableAD.getFiltrationTime(), 0.0);
    assertSame(randomVariable, values);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAD.getRealizations(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableDifferentiableAD#getOperatorTreeNode()}
   *   <li>{@link RandomVariableDifferentiableAD#getTangents()}
   *   <li>{@link RandomVariableDifferentiableAD#getTypePriority()}
   *   <li>{@link RandomVariableDifferentiableAD#getValues()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAD.OperatorTreeNode RandomVariableDifferentiableAD.getOperatorTreeNode()",
    "Map RandomVariableDifferentiableAD.getTangents()",
    "int RandomVariableDifferentiableAD.getTypePriority()",
    "RandomVariable RandomVariableDifferentiableAD.getValues()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    ofResult.getOperatorTreeNode();
    Map<Long, RandomVariable> actualTangents = ofResult.getTangents();
    int actualTypePriority = ofResult.getTypePriority();

    // Assert
    assertTrue(ofResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertNull(actualTangents);
    assertEquals(3, actualTypePriority);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAD.getGradient(Set)"})
  public void testGetGradientWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAD.getGradient(Set)"})
  public void testGetGradientWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(0L);
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAD.getGradient(Set)"})
  public void testGetGradientWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(new HashSet<>()).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAD.getTangents(Set)"})
  public void testGetTangentsWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAD.getTangents(Set)"})
  public void testGetTangentsWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(0L);
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAD.getTangents(Set)"})
  public void testGetTangentsWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(new HashSet<>()));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD randomVariableDifferentiableAD =
        new RandomVariableDifferentiableAD(Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableDifferentiableAD.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_givenFalse() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.isDeterministic()).thenReturn(false);
    when(randomVariable.getFiltrationTime()).thenReturn(Double.NEGATIVE_INFINITY);

    // Act
    ofResult.equals((RandomVariable) randomVariable);

    // Assert
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable, atLeast(1)).isDeterministic();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#get(int)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_givenScalarWithValueIsTen_thenCallsGet() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.get(anyInt())).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);

    // Act
    ofResult.equals((RandomVariable) randomVariable);

    // Assert
    verify(randomVariable).get(0);
    verify(randomVariable).isDeterministic();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableAAD} {@link RandomVariableAAD#getFiltrationTime()} return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenRandomVariableAADGetFiltrationTimeReturnTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);

    // Act
    ofResult.equals((RandomVariable) randomVariable);

    // Assert
    verify(randomVariable).getFiltrationTime();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertFalse(ofResult.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getRealizations()"})
  public void testGetRealizations() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d},
        RandomVariableDifferentiableAD.of(randomVariable).getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getRealizations()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getRealizations()"})
  public void testGetRealizations_givenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertArrayEquals(new double[] {10.0d}, ofResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getRealizations()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getRealizations()"})
  public void testGetRealizations_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getRealizations());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertEquals(100.0d, ofResult.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_givenScalarWithValueIsTen_thenReturnZero() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable_givenScalarWithValueIsTen_thenReturnZero() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getQuantile(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getQuantile(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getQuantile(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAD.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities_givenScalarWithValueIsTen_thenReturnTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    double[] actualHistogram =
        RandomVariableDifferentiableAD.of(randomVariable)
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints3() {
    // Arrange
    RandomVariableFromFloatArray randomVariable = new RandomVariableFromFloatArray(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    double[] actualHistogram =
        RandomVariableDifferentiableAD.of(randomVariable)
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {});

    // Assert
    assertArrayEquals(new double[] {1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange and Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d))
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
   * Test {@link RandomVariableDifferentiableAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations2() {
    // Arrange
    RandomVariableFromFloatArray randomVariable = new RandomVariableFromFloatArray(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)});

    // Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAD.of(randomVariable).getHistogram(10, 10.0d);

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
   * Test {@link RandomVariableDifferentiableAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations_thenReturnArrayLengthIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

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
   * Test {@link RandomVariableDifferentiableAD#cache()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cache()"})
  public void testCache() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cache()}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAD} with randomVariable is {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cache()"})
  public void testCache_thenReturnRandomVariableDifferentiableADWithRandomVariableIsScalar() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cache()}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cache()"})
  public void testCache_thenReturnRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(double)"})
  public void testCapWithCap_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualCapResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertNull(actualCapResult.getRealizations());
    assertNull(actualCapResult.getOperator());
    assertNull(actualCapResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(double)"})
  public void testCapWithCap_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualCapResult = RandomVariableDifferentiableAD.of(randomVariable).cap(10.0d);

    // Assert
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(double)"})
  public void testCapWithCap_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(10.0d);

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.cap(randomVariable));
    verify(randomVariableAAD).cap(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#cap(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_givenScalarWithValueIsTen_thenCallsCap() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariableAAD).cap(10.0d);
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#cap(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenCallsCap() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariableAAD).cap(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.cap(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromFloatArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertEquals(3, actualCapResult.size());
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Variance is sixteen.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnVarianceIsSixteen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(16.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(2.0d, actualCapResult.getMin(), 0.0);
    assertEquals(2.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(21.333333333333332d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(4, actualCapResult.size());
    assertEquals(4.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(6.0d, actualCapResult.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(Scalar.of(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(double)"})
  public void testFloorWithDouble_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertNull(actualFloorResult.getRealizations());
    assertNull(actualFloorResult.getOperator());
    assertNull(actualFloorResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(double)"})
  public void testFloorWithDouble_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAD.of(randomVariable).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(double)"})
  public void testFloorWithDouble_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(10.0d);

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(floor).getValues();
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.floor(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.floor(floor));
    verify(randomVariableAAD).floor(isA(RandomVariable.class));
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.floor(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.floor(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(randomVariableAAD).cap(isA(RandomVariable.class));
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(floor).getValues();
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#floor(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_givenScalarWithValueIsTen_thenCallsFloor() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.floor(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(randomVariableAAD).floor(10.0d);
    verify(floor).getTypePriority();
    verify(floor).getValues();
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.floor(floor));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(floor).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenCallsDoubleValue2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.floor(floor));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(floor).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#floor(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenCallsFloor() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.floor(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(randomVariableAAD).floor(isA(RandomVariable.class));
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromFloatArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(floor).getValues();
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(3, actualFloorResult.size());
    assertFalse(actualFloorResult.isDeterministic());
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD floor = mock(RandomVariableAAD.class);
    when(floor.getValues()).thenReturn(randomVariableAAD);
    when(floor.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    verify(floor).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(floor).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.add(randomVariable));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#add(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_givenScalarWithValueIsTen_thenCallsAdd() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariableAAD).add(10.0d);
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.add(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromFloatArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Min is twelve.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnMinIsTwelve() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(12.0d, actualAddResult.getMin(), 0.0);
    assertEquals(16.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(16.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(2.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(21.333333333333332d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(4, actualAddResult.size());
    assertEquals(4.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {20.0d, 12.0d, 20.0d, 12.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertEquals(3, actualAddResult.size());
    assertArrayEquals(new double[] {20.0d, 20.0d, 20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddResult = ofResult.add(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(double)"})
  public void testAddWithValue_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualAddResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertNull(actualAddResult.getRealizations());
    assertNull(actualAddResult.getOperator());
    assertNull(actualAddResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(double)"})
  public void testAddWithValue_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualAddResult = RandomVariableDifferentiableAD.of(randomVariable).add(10.0d);

    // Assert
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.add(double)"})
  public void testAddWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(10.0d);

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAddResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.bus(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.sub(randomVariable));
    verify(randomVariableAAD).bus(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(RandomVariableDifferentiableAD.of(-1.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).sub(10.0d);
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(-1.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).sub(10.0d);
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_givenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(new RandomVariableFromFloatArray(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.bus(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariableAAD).bus(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.sub(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Average is minus ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnAverageIsMinusTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).sub(10.0d);
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertEquals(-10.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(-10.0d, actualSubResult.getMax(), 0.0);
    assertArrayEquals(new double[] {-10.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnFiltrationTimeIsMinusOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(-1.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.sub(anyDouble())).thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).sub(10.0d);
    verify(randomVariable).getValues();
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertEquals(-1.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertEquals(-2.0d, actualSubResult.getMax(), 0.0);
    assertEquals(-6.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(16.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(2.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(21.333333333333332d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(4, actualSubResult.size());
    assertEquals(4.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {-10.0d, -2.0d, -10.0d, -2.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnFiltrationTimeIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertEquals(3, actualSubResult.size());
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.bus(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariableAAD).bus(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(double)"})
  public void testSubWithValue_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualSubResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.getValues() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
    assertNull(actualSubResult.getOperator());
    assertNull(actualSubResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(double)"})
  public void testSubWithValue_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSubResult = RandomVariableDifferentiableAD.of(randomVariable).sub(10.0d);

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sub(double)"})
  public void testSubWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(10.0d);

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.mult(randomVariable));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#mult(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_givenScalarWithValueIsTen_thenCallsMult() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(randomVariable).getValues();
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.mult(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromFloatArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariable).getValues();
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertEquals(3, actualMultResult.size());
    assertArrayEquals(
        new double[] {100.0d, 100.0d, 100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Variance is {@code 1600.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnVarianceIs16000() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1600.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(20.0d, actualMultResult.getMin(), 0.0);
    assertEquals(20.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(2133.3333333333335d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(4, actualMultResult.size());
    assertEquals(40.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(60.0d, actualMultResult.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {100.0d, 20.0d, 100.0d, 20.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(double)"})
  public void testMultWithValue_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
    assertNull(actualMultResult.getOperator());
    assertNull(actualMultResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(double)"})
  public void testMultWithValue_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMultResult = RandomVariableDifferentiableAD.of(randomVariable).mult(10.0d);

    // Assert
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.mult(double)"})
  public void testMultWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(10.0d);

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMultResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.vid(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.div(randomVariable));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).vid(isA(RandomVariable.class));
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).invert();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {100.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.invert()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).invert();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {100.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.invert()).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).invert();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {100.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_givenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(new RandomVariableFromFloatArray(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.vid(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).vid(isA(RandomVariable.class));
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.div(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.vid(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).vid(isA(RandomVariable.class));
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(3, actualDivResult.size());
    assertArrayEquals(new double[] {1.0d, 1.0d, 1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Variance is {@code 1600.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnVarianceIs16000() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.invert()).thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).invert();
    verify(randomVariable).getValues();
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1600.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(20.0d, actualDivResult.getMin(), 0.0);
    assertEquals(20.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(2133.3333333333335d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(4, actualDivResult.size());
    assertEquals(40.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(60.0d, actualDivResult.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {100.0d, 20.0d, 100.0d, 20.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(Scalar.of(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(double)"})
  public void testDivWithValue_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualDivResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.getValues() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
    assertNull(actualDivResult.getOperator());
    assertNull(actualDivResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(double)"})
  public void testDivWithValue_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualDivResult = RandomVariableDifferentiableAD.of(randomVariable).div(10.0d);

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.div(double)"})
  public void testDivWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(10.0d);

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.vid(randomVariable));
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#div(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_givenScalarWithValueIsTen_thenCallsDiv() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariableAAD).div(10.0d);
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.vid(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromFloatArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertEquals(3, actualVidResult.size());
    assertArrayEquals(new double[] {1.0d, 1.0d, 1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Variance is {@code 0.16}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnVarianceIs016() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertEquals(0.16d, actualVidResult.getVariance(), 0.0);
    assertEquals(0.21333333333333335d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.2d, actualVidResult.getMin(), 0.0);
    assertEquals(0.2d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.4d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.6d, actualVidResult.getAverage(), 0.0);
    assertEquals(4, actualVidResult.size());
    assertArrayEquals(
        new double[] {1.0d, 0.2d, 1.0d, 0.2d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#pow(double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.pow(double)"})
  public void testPow_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualPowResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).pow(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.variance() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.getValues() instanceof Scalar);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualPowResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.pow(double)"})
  public void testPow_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualPowResult = RandomVariableDifferentiableAD.of(randomVariable).pow(10.0d);

    // Assert
    assertTrue(actualPowResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.variance() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualPowResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.pow(double)"})
  public void testPow_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualPowResult = RandomVariableDifferentiableAD.of(randomVariable).pow(10.0d);

    // Assert
    assertTrue(actualPowResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualPowResult).getGradient().size());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.pow(double)"})
  public void testPow_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualPowResult = ofResult.pow(10.0d);

    // Assert
    assertTrue(actualPowResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualPowResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualPowResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualPowResult).getGradient().size());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariable).getValues();
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.bus(randomVariable));
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#sub(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_givenScalarWithValueIsTen_thenCallsSub() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).sub(10.0d);
    verify(randomVariable).getValues();
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.bus(randomVariable));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariable).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenNaNReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromFloatArray(10.0d, 10.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnAverageIs00() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(-0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(-0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(-0.0d, actualBusResult.getMin(), 0.0);
    assertArrayEquals(new double[] {-0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Average is minus four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnAverageIsMinusFour() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(-4.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(-8.0d, actualBusResult.getMin(), 0.0);
    assertEquals(16.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(2.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(21.333333333333332d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(4, actualBusResult.size());
    assertEquals(4.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, -8.0d, 0.0d, -8.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    verify(randomVariable).getValues();
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnSizeIsThree() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariable).getValues();
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertEquals(3, actualBusResult.size());
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.getValues()).thenReturn(randomVariableAAD);
    when(randomVariable.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    verify(randomVariable).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariable).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#average()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.average()"})
  public void testAverage_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAD.of(randomVariable).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAD);
    RandomVariable values = actualAverageResult.getValues();
    assertTrue(values instanceof Scalar);
    assertTrue(actualAverageResult.isNaN() instanceof Scalar);
    assertNull(actualAverageResult.getRealizations());
    assertNull(actualAverageResult.getOperator());
    assertNull(actualAverageResult.getRealizationsStream());
    assertSame(randomVariable, values);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#average()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.average()"})
  public void testAverage_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAD.of(randomVariable).average();

    // Assert
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAverageResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#average()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.average()"})
  public void testAverage_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAverageResult = ofResult.average();

    // Assert
    assertTrue(actualAverageResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAverageResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAverageResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualConditionalExpectation =
        RandomVariableDifferentiableAD.of(randomVariable)
            .getConditionalExpectation(mock(ConditionalExpectationEstimator.class));

    // Assert
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAD);
    RandomVariable values = actualConditionalExpectation.getValues();
    assertTrue(values instanceof Scalar);
    assertTrue(actualConditionalExpectation.isNaN() instanceof Scalar);
    assertNull(actualConditionalExpectation.getRealizations());
    assertNull(actualConditionalExpectation.getOperator());
    assertNull(actualConditionalExpectation.getRealizationsStream());
    assertSame(randomVariable, values);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualConditionalExpectation =
        RandomVariableDifferentiableAD.of(randomVariable)
            .getConditionalExpectation(mock(ConditionalExpectationEstimator.class));

    // Assert
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualConditionalExpectation.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualConditionalExpectation.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualConditionalExpectation.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualConditionalExpectation =
        ofResult.getConditionalExpectation(mock(ConditionalExpectationEstimator.class));

    // Assert
    assertTrue(actualConditionalExpectation.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualConditionalExpectation)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualConditionalExpectation.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualConditionalExpectation.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#squared()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.squared()"})
  public void testSquared_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.getValues() instanceof Scalar);
    assertTrue(actualSquaredResult.isNaN() instanceof Scalar);
    assertNull(actualSquaredResult.getRealizations());
    assertNull(actualSquaredResult.getOperator());
    assertNull(actualSquaredResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#squared()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.squared()"})
  public void testSquared_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAD.of(randomVariable).squared();

    // Assert
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSquaredResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#squared()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.squared()"})
  public void testSquared_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSquaredResult = ofResult.squared();

    // Assert
    assertTrue(actualSquaredResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSquaredResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSquaredResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSquaredResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sqrt()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sqrt()"})
  public void testSqrt_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualSqrtResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.getValues() instanceof Scalar);
    assertTrue(actualSqrtResult.isNaN() instanceof Scalar);
    assertNull(actualSqrtResult.getRealizations());
    assertNull(actualSqrtResult.getOperator());
    assertNull(actualSqrtResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sqrt()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sqrt()"})
  public void testSqrt_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSqrtResult = RandomVariableDifferentiableAD.of(randomVariable).sqrt();

    // Assert
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSqrtResult).getGradient().size());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sqrt()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sqrt()"})
  public void testSqrt_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSqrtResult = ofResult.sqrt();

    // Assert
    assertTrue(actualSqrtResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSqrtResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSqrtResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSqrtResult).getGradient().size());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#exp()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.exp()"})
  public void testExp_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualExpResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.getValues() instanceof Scalar);
    assertTrue(actualExpResult.isNaN() instanceof Scalar);
    assertNull(actualExpResult.getRealizations());
    assertNull(actualExpResult.getOperator());
    assertNull(actualExpResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#exp()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.exp()"})
  public void testExp_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualExpResult = RandomVariableDifferentiableAD.of(randomVariable).exp();

    // Assert
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualExpResult).getGradient().size());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#exp()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.exp()"})
  public void testExp_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualExpResult = ofResult.exp();

    // Assert
    assertTrue(actualExpResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualExpResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualExpResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualExpResult).getGradient().size());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#log()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.log()"})
  public void testLog_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualLogResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.getValues() instanceof Scalar);
    assertTrue(actualLogResult.isNaN() instanceof Scalar);
    assertNull(actualLogResult.getRealizations());
    assertNull(actualLogResult.getOperator());
    assertNull(actualLogResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#log()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.log()"})
  public void testLog_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualLogResult = RandomVariableDifferentiableAD.of(randomVariable).log();

    // Assert
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualLogResult).getGradient().size());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#log()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.log()"})
  public void testLog_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualLogResult = ofResult.log();

    // Assert
    assertTrue(actualLogResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualLogResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualLogResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualLogResult).getGradient().size());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sin()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sin()"})
  public void testSin_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualSinResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.getValues() instanceof Scalar);
    assertTrue(actualSinResult.isNaN() instanceof Scalar);
    assertNull(actualSinResult.getRealizations());
    assertNull(actualSinResult.getOperator());
    assertNull(actualSinResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sin()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sin()"})
  public void testSin_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualSinResult = RandomVariableDifferentiableAD.of(randomVariable).sin();

    // Assert
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSinResult).getGradient().size());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#sin()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.sin()"})
  public void testSin_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSinResult = ofResult.sin();

    // Assert
    assertTrue(actualSinResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSinResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSinResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSinResult).getGradient().size());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cos()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cos()"})
  public void testCos_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualCosResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.getValues() instanceof Scalar);
    assertTrue(actualCosResult.isNaN() instanceof Scalar);
    assertNull(actualCosResult.getRealizations());
    assertNull(actualCosResult.getOperator());
    assertNull(actualCosResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cos()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cos()"})
  public void testCos_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualCosResult = RandomVariableDifferentiableAD.of(randomVariable).cos();

    // Assert
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCosResult).getGradient().size());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#cos()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.cos()"})
  public void testCos_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualCosResult = ofResult.cos();

    // Assert
    assertTrue(actualCosResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCosResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCosResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualCosResult).getGradient().size());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue3() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#getTypePriority()} return one.
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableAADGetTypePriorityReturnOne_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.accrue(rate, 10.0d));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(rate).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#mult(double)} return {@link
   *       Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableAADMultReturnScalarWithValueIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(Scalar.of(1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {20.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#mult(double)} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableAADMultThrowUnsupportedOperationException() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.accrue(rate, 10.0d));
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableDifferentiableADWithValueIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(RandomVariableDifferentiableAD.of(1.0d));
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(rate).getValues();
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableFromDoubleArrayWithValueIsOne() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(rate).getValues();
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return Realizations is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenScalarWithValueIsTen_thenReturnRealizationsIsNull() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(anyDouble())).thenReturn(mock(RandomVariableAAD.class));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD2);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(randomVariableAAD).add(10.0d);
    verify(rate).getTypePriority();
    verify(randomVariableAAD2).mult(100.0d);
    verify(rate).getValues();
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertNull(actualAccrueResult.getRealizations());
    assertNull(actualAccrueResult.getOperator());
    assertNull(actualAccrueResult.isNaN());
    assertEquals(0, actualAccrueResult.size());
    assertEquals(0.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnAverageIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD3);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(randomVariableAAD2).add(1.0d);
    verify(rate).getTypePriority();
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD3).mult(10.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(rate).getValues();
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(10.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(10.0d, actualAccrueResult.getMin(), 0.0);
    assertSame(randomVariableFromDoubleArray, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnFiltrationTimeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(1.0d, 1.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1.0d, actualAccrueResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnFiltrationTimeIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(randomVariableAAD).size();
    verify(rate).getValues();
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualAccrueResult.getFiltrationTime(), 0.0);
    assertEquals(3, actualAccrueResult.size());
    assertArrayEquals(
        new double[] {1010.0d, 1010.0d, 1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAD.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {20.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_whenScalarWithValueIsOne_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount3() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#getTypePriority()} return one.
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableAADGetTypePriorityReturnOne_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.discount(rate, 10.0d));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(rate).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#mult(double)} return {@link
   *       Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableAADMultReturnScalarWithValueIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(Scalar.of(1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {5.0d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#mult(double)} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableAADMultThrowUnsupportedOperationException() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.discount(rate, 10.0d));
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableDifferentiableADWithValueIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(RandomVariableDifferentiableAD.of(1.0d));
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(rate).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableFromDoubleArrayWithValueIsOne() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(rate).getValues();
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return Realizations is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenScalarWithValueIsTen_thenReturnRealizationsIsNull() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.invert()).thenReturn(mock(RandomVariableAAD.class));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD3);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(randomVariableAAD2).add(0.1d);
    verify(rate).getTypePriority();
    verify(randomVariableAAD).invert();
    verify(randomVariableAAD3).mult(1.0d);
    verify(rate).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertNull(actualDiscountResult.getRealizations());
    assertNull(actualDiscountResult.getOperator());
    assertNull(actualDiscountResult.isNaN());
    assertEquals(0, actualDiscountResult.size());
    assertEquals(0.0d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.09900990099009903}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs009900990099009903() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(randomVariableAAD).size();
    verify(rate).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertEquals(0.09900990099009903d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(1.3877787807814457E-17d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertEquals(1.9259299443872359E-34d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(10.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertEquals(2.8888949165808538E-34d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(3, actualDiscountResult.size());
    assertEquals(8.012344526598184E-18d, actualDiscountResult.getStandardError(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d, 0.09900990099009901d, 0.09900990099009901d},
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.add(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(anyDouble())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD4);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(randomVariableAAD3).add(1.0d);
    verify(rate).getTypePriority();
    verify(randomVariableAAD4).getTypePriority();
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD4).mult(10.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(rate).getValues();
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(10.0d, actualDiscountResult.getMax(), 0.0);
    assertEquals(10.0d, actualDiscountResult.getMin(), 0.0);
    assertSame(randomVariableFromDoubleArray, actualDiscountResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnFiltrationTimeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(1.0d, 1.0d, 3));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAD.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(randomVariable);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(rate).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD rate = mock(RandomVariableAAD.class);
    when(rate.getValues()).thenReturn(randomVariableAAD);
    when(rate.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    verify(rate).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(rate).getValues();
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {5.0d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.discount(RandomVariable, double)"
  })
  public void testDiscount_whenScalarWithValueIsOne_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNonNegative.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD valueIfTriggerNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNegative.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, valueIfTriggerNegative);

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    verify(valueIfTriggerNegative).getValues();
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableDifferentiableADWithValueIsMinusOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(-1.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNonNegative.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD valueIfTriggerNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNegative.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, valueIfTriggerNegative);

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    verify(valueIfTriggerNegative).getValues();
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then MaxAsRandomVariableAAD return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenMaxAsRandomVariableAADReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNonNegative.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then MaxAsRandomVariableAAD return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenMaxAsRandomVariableAADReturnRandomVariableDifferentiableAD2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNonNegative.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD valueIfTriggerNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNegative.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, valueIfTriggerNegative);

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    verify(valueIfTriggerNegative).getValues();
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableDifferentiableAD valueIfTriggerNonNegative =
        RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAD ofResult2 = RandomVariableDifferentiableAD.of(10.0d);
    when(valueIfTriggerNonNegative.getValues()).thenReturn(ofResult2);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    RandomVariable values = actualChooseResult.getValues();
    assertTrue(values instanceof RandomVariableDifferentiableAD);
    assertSame(ofResult2, values);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNonNegative.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, RandomVariableDifferentiableAD.of(10.0d));

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD valueIfTriggerNonNegative = mock(RandomVariableAAD.class);
    when(valueIfTriggerNonNegative.getValues())
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    verify(valueIfTriggerNonNegative).getValues();
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAD);
    RandomVariable values = actualChooseResult.getValues();
    assertTrue(values instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
    assertNull(actualChooseResult.getRealizations());
    assertNull(actualChooseResult.getOperator());
    assertNull(actualChooseResult.getRealizationsStream());
    assertSame(valueIfTriggerNonNegative, values);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#invert()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.invert()"})
  public void testInvert_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.getValues() instanceof Scalar);
    assertTrue(actualInvertResult.isNaN() instanceof Scalar);
    assertNull(actualInvertResult.getRealizations());
    assertNull(actualInvertResult.getOperator());
    assertNull(actualInvertResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#invert()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.invert()"})
  public void testInvert_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});

    // Act
    RandomVariable actualInvertResult = RandomVariableDifferentiableAD.of(randomVariable).invert();

    // Assert
    assertTrue(actualInvertResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualInvertResult).getGradient().size());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#invert()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.invert()"})
  public void testInvert_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualInvertResult = ofResult.invert();

    // Assert
    assertTrue(actualInvertResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualInvertResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualInvertResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualInvertResult).getGradient().size());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#abs()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.abs()"})
  public void testAbs_givenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariable actualAbsResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.getValues() instanceof Scalar);
    assertTrue(actualAbsResult.isNaN() instanceof Scalar);
    assertNull(actualAbsResult.getRealizations());
    assertNull(actualAbsResult.getOperator());
    assertNull(actualAbsResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#abs()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.abs()"})
  public void testAbs_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualAbsResult = RandomVariableDifferentiableAD.of(randomVariable).abs();

    // Assert
    assertTrue(actualAbsResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAbsResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#abs()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.abs()"})
  public void testAbs_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAbsResult = ofResult.abs();

    // Assert
    assertTrue(actualAbsResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualAbsResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAbsResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAbsResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues())
        .thenReturn(RandomVariableDifferentiableAD.of(Double.NEGATIVE_INFINITY));
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, 10.0d));
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(factor1).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(Scalar.of(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor1).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble8() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor1).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble9() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 3);
    when(randomVariableAAD.getValues()).thenReturn(randomVariableFromDoubleArray);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor1).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble10() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues())
        .thenReturn(RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor1).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble11() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2).mult(10.0d);
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble12() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor1).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#add(double)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_givenScalarWithValueIsTen_thenCallsAdd() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(randomVariableAAD).add(10.0d);
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).mult(10.0d);
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#add(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenCallsAdd() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2).mult(10.0d);
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, 10.0d));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(factor1).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return Average is twenty.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnAverageIsTwenty() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).mult(10.0d);
    verify(factor1).getValues();
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(20.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddProductResult.getMin(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnFiltrationTimeIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(randomVariableAAD).size();
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualAddProductResult.getFiltrationTime(), 0.0);
    assertEquals(3, actualAddProductResult.size());
    assertArrayEquals(
        new double[] {110.0d, 110.0d, 110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(RandomVariableDifferentiableAD.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableDifferentiableAD ofResult =
        RandomVariableDifferentiableAD.of(RandomVariableDifferentiableAD.of(10.0d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    when(randomVariableAAD.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, 10.0d);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor1).getValues();
    verify(randomVariableAAD).getValues();
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(4, actualAddProductResult.size());
    assertEquals(Double.NaN, actualAddProductResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY, 110.0d, Double.NEGATIVE_INFINITY, 110.0d},
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(Scalar.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(factor1).getTypePriority();
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(factor1.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(factor1).getTypePriority();
    verify(factor1).getValues();
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(factor1).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.addProduct(factor1, RandomVariableDifferentiableAD.of(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(factor1).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(factor2.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, factor2));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(factor1).getValues();
    verify(factor2).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(factor2.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, factor2));
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable9() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable10() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable11() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable12() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD2.isDeterministic()).thenReturn(true);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD2);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(randomVariableAAD2).doubleValue();
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD, atLeast(1)).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD, atLeast(1)).getTypePriority();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(randomVariableAAD).size();
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {110.0d, 110.0d, 110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable13() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD2.isDeterministic()).thenReturn(true);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD2);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, factor2));
    verify(randomVariableAAD2).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(factor1).getValues();
    verify(factor2).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable14() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getTypePriority()).thenThrow(new UnsupportedOperationException());

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.isDeterministic()).thenReturn(false);
    when(randomVariableAAD3.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD3.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD3);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, factor2));
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD3).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD3).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable15() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.isDeterministic()).thenReturn(false);
    when(randomVariableAAD3.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD3.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD3);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD3).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable16() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.isDeterministic()).thenReturn(false);
    when(randomVariableAAD3.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD3.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD3);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD3).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).size();
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable17() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getTypePriority()).thenReturn(3);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD2);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.isDeterministic()).thenReturn(false);
    when(randomVariableAAD3.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD3.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD3);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD3).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD3).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(factor1).getValues();
    verify(factor2).getValues();
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable18() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor2 = mock(RandomVariableAAD.class);
    when(factor2.getValues()).thenReturn(randomVariableAAD2);
    when(factor2.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, factor2));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(factor2).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD, atLeast(1)).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).isDeterministic();
    verify(factor1).getValues();
    verify(factor2).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableDifferentiableAD factor1 = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.getValues()).thenReturn(randomVariableAAD);
    when(factor1.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.addProduct(factor1, Scalar.of(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(factor1).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(factor1).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#div(RandomVariable)} return
   *       {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_givenRandomVariableAADDivReturnScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#div(RandomVariable)} throw
   *       {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_givenRandomVariableAADDivThrowUnsupportedOperationException() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.addRatio(numerator, denominator));
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(numerator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).getTypePriority();
    verify(numerator).getValues();
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_givenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.addRatio(numerator, denominator));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
    verify(denominator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnAverageIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD2);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualAddRatioResult.getAverage(), 0.0);
    assertEquals(10.0d, actualAddRatioResult.getMax(), 0.0);
    assertEquals(10.0d, actualAddRatioResult.getMin(), 0.0);
    assertSame(randomVariableFromDoubleArray, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableDifferentiableAD numerator = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithEleven() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndEleven() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.size()).thenReturn(3);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(randomVariableAAD2);
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD2, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariableAAD2).size();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndEleven2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.size()).thenReturn(3);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(randomVariableAAD2);
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD2, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariableAAD2).size();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(numerator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).getTypePriority();
    verify(numerator).getValues();
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualAddRatioResult = ofResult.addRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.addRatio(numerator, RandomVariableDifferentiableAD.of(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.addRatio(numerator, Scalar.of(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#div(RandomVariable)} return
   *       {@link Scalar} with value is minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_givenRandomVariableAADDivReturnScalarWithValueIsMinusOne() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(-1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#div(RandomVariable)} throw
   *       {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_givenRandomVariableAADDivThrowUnsupportedOperationException() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.subRatio(numerator, denominator));
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(numerator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).getTypePriority();
    verify(numerator).getValues();
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_givenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.subRatio(numerator, denominator));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
    verify(denominator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnAverageIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD3);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD3).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD2).mult(-1.0d);
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(10.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(10.0d, actualSubRatioResult.getMin(), 0.0);
    assertSame(randomVariableFromDoubleArray, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnAverageIsZero() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(0.0d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(0.0d, actualSubRatioResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableDifferentiableAD numerator = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(2, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNine() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNineAndNine() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.size()).thenReturn(3);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(randomVariableAAD2);
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD2, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariableAAD2).size();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d, 9.0d, 9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNineAndNine2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.size()).thenReturn(3);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.get(anyInt())).thenReturn(10.0d);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.size()).thenReturn(3);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(randomVariableAAD2);
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableAAD2, atLeast(1)).get(anyInt());
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD).size();
    verify(randomVariableAAD2).size();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d, 9.0d, 9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableDifferentiableAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(numerator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).getTypePriority();
    verify(numerator).getValues();
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(-1.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    RandomVariableAAD denominator = mock(RandomVariableAAD.class);
    when(denominator.getValues()).thenReturn(RandomVariableDifferentiableAD.of(10.0d));
    when(denominator.getTypePriority()).thenReturn(1);

    // Act
    RandomVariable actualSubRatioResult = ofResult.subRatio(numerator, denominator);

    // Assert
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(denominator).getTypePriority();
    verify(numerator).getValues();
    verify(denominator).getValues();
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.subRatio(numerator, RandomVariableDifferentiableAD.of(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAD);
    assertEquals(1, ((RandomVariableDifferentiableAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then calls {@link RandomVariableAAD#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenCallsDoubleValue() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new UnsupportedOperationException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.getValues()).thenReturn(randomVariableAAD);
    when(numerator.getTypePriority()).thenReturn(1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.subRatio(numerator, Scalar.of(10.0d)));
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(numerator).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(numerator).getValues();
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.isNaN()"})
  public void testIsNaN() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualIsNaNResult = RandomVariableDifferentiableAD.of(randomVariable).isNaN();

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
   * Test {@link RandomVariableDifferentiableAD#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.isNaN()"})
  public void testIsNaN_givenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

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
   * Test {@link RandomVariableDifferentiableAD#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.isNaN()"})
  public void testIsNaN_givenScalarWithValueIsTen_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).isNaN();

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
   * Test {@link RandomVariableDifferentiableAD#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.isNaN()"})
  public void testIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = RandomVariableDifferentiableAD.of(Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getRealizationsStream()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getRealizationsStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.stream.DoubleStream RandomVariableDifferentiableAD.getRealizationsStream()"
  })
  public void testGetRealizationsStream_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.apply(mock(DoubleUnaryOperator.class)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD = ofResult.getVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualVarianceAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(randomVariable).getVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualVarianceAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getVarianceAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualVarianceAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualVarianceAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        ofResult.getSampleVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(randomVariable).getSampleVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getSampleVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        ofResult.getStandardDeviationAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(randomVariable).getStandardDeviationAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d))
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        ofResult.getStandardErrorAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD2() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(randomVariable).getStandardErrorAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getStandardErrorAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAD#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAD.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getStandardErrorAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAD) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getMinAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.getMinAsRandomVariableAAD()"})
  public void testGetMinAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(randomVariable).getMinAsRandomVariableAAD();

    // Assert
    assertTrue(actualMinAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getMinAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.getMinAsRandomVariableAAD()"})
  public void testGetMinAsRandomVariableAAD_givenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualMinAsRandomVariableAAD = ofResult.getMinAsRandomVariableAAD();

    // Assert
    assertTrue(actualMinAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getMinAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.getMinAsRandomVariableAAD()"})
  public void testGetMinAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getMinAsRandomVariableAAD();

    // Assert
    assertTrue(actualMinAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAD) actualMinAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getMaxAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.getMaxAsRandomVariableAAD()"})
  public void testGetMaxAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAD randomVariable = RandomVariableDifferentiableAD.of(10.0d);
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(randomVariable).getMaxAsRandomVariableAAD();

    // Assert
    assertTrue(actualMaxAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getMaxAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.getMaxAsRandomVariableAAD()"})
  public void testGetMaxAsRandomVariableAAD_givenRandomVariableDifferentiableADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAD ofResult = RandomVariableDifferentiableAD.of(10.0d);

    // Act
    RandomVariable actualMaxAsRandomVariableAAD = ofResult.getMaxAsRandomVariableAAD();

    // Assert
    assertTrue(actualMaxAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAD#getMaxAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAD#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAD.getMaxAsRandomVariableAAD()"})
  public void testGetMaxAsRandomVariableAAD_givenScalarWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAD.of(Scalar.of(10.0d)).getMaxAsRandomVariableAAD();

    // Assert
    assertTrue(actualMaxAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAD) actualMaxAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }
}
