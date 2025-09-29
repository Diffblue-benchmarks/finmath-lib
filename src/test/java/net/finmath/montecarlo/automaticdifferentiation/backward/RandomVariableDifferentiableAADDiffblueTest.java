package net.finmath.montecarlo.automaticdifferentiation.backward;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.RandomVariableDifferentiable;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory.DiracDeltaApproximationMethod;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableUniqueVariable;
import net.finmath.stochastic.ConditionalExpectationEstimator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class RandomVariableDifferentiableAADDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAAD.<init>(double)"})
  public void testNewRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariableDifferentiableAAD actualRandomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(10.0d);

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAAD.getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualRandomVariableDifferentiableAAD.getValues() instanceof Scalar);
    assertTrue(actualRandomVariableDifferentiableAAD.isNaN() instanceof Scalar);
    assertNull(actualRandomVariableDifferentiableAAD.getRealizations());
    assertNull(actualRandomVariableDifferentiableAAD.getOperator());
    assertNull(actualRandomVariableDifferentiableAAD.getRealizationsStream());
    assertEquals(0.0d, actualRandomVariableDifferentiableAAD.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAAD.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAAD.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableDifferentiableAAD.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableDifferentiableAAD.getGradient().size());
    assertEquals(1, actualRandomVariableDifferentiableAAD.size());
    assertEquals(10.0d, actualRandomVariableDifferentiableAAD.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAAD.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAAD.getMin(), 0.0);
    assertEquals(3, actualRandomVariableDifferentiableAAD.getTypePriority());
    assertTrue(actualRandomVariableDifferentiableAAD.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableDifferentiableAAD.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAAD.<init>(RandomVariable)"})
  public void testNewRandomVariableDifferentiableAAD2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAAD actualRandomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(randomVariable);

    // Assert
    RandomVariable values = actualRandomVariableDifferentiableAAD.getValues();
    assertTrue(values instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualRandomVariableDifferentiableAAD.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAAD.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableDifferentiableAAD.getMin(), 0.0);
    assertSame(randomVariable, values);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAAD.<init>(RandomVariable)"})
  public void testNewRandomVariableDifferentiableAAD3() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(0.05d);

    // Act
    RandomVariableDifferentiableAAD actualRandomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(randomVariable);

    // Assert
    RandomVariable values = actualRandomVariableDifferentiableAAD.getValues();
    assertTrue(values instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualRandomVariableDifferentiableAAD.isNaN() instanceof Scalar);
    assertNull(actualRandomVariableDifferentiableAAD.getRealizations());
    assertNull(actualRandomVariableDifferentiableAAD.getOperator());
    assertNull(actualRandomVariableDifferentiableAAD.getRealizationsStream());
    assertEquals(0.05d, actualRandomVariableDifferentiableAAD.getAverage(), 0.0);
    assertEquals(0.05d, actualRandomVariableDifferentiableAAD.getMax(), 0.0);
    assertEquals(0.05d, actualRandomVariableDifferentiableAAD.getMin(), 0.0);
    assertSame(randomVariable, values);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable,
   * RandomVariableDifferentiableAADFactory)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable,
   * RandomVariableDifferentiableAADFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableDifferentiableAAD.<init>(RandomVariable, RandomVariableDifferentiableAADFactory)"
  })
  public void testNewRandomVariableDifferentiableAAD4() {
    // Arrange
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    // Act
    RandomVariableDifferentiableAAD actualRandomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualRandomVariableDifferentiableAAD.getGradient().size());
    assertSame(factory, actualRandomVariableDifferentiableAAD.getFactory());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable,
   * RandomVariableDifferentiableAADFactory)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#RandomVariableDifferentiableAAD(RandomVariable,
   * RandomVariableDifferentiableAADFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableDifferentiableAAD.<init>(RandomVariable, RandomVariableDifferentiableAADFactory)"
  })
  public void testNewRandomVariableDifferentiableAAD_whenNull() {
    // Arrange and Act
    RandomVariableDifferentiableAAD actualRandomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(new RandomVariableFromDoubleArray(10.0d), null);

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualRandomVariableDifferentiableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualRandomVariableDifferentiableAAD.getGradient().size());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableDifferentiableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#of(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#of(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAAD RandomVariableDifferentiableAAD.of(RandomVariable)"
  })
  public void testOfWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(0.05d);

    // Act
    RandomVariableDifferentiableAAD actualOfResult =
        RandomVariableDifferentiableAAD.of(randomVariable);

    // Assert
    RandomVariable values = actualOfResult.getValues();
    assertTrue(values instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualOfResult.isNaN() instanceof Scalar);
    assertNull(actualOfResult.getRealizations());
    assertNull(actualOfResult.getOperator());
    assertNull(actualOfResult.getRealizationsStream());
    assertEquals(0.05d, actualOfResult.getAverage(), 0.0);
    assertEquals(0.05d, actualOfResult.getMax(), 0.0);
    assertEquals(0.05d, actualOfResult.getMin(), 0.0);
    assertSame(randomVariable, values);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#of(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#of(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAAD RandomVariableDifferentiableAAD.of(RandomVariable)"
  })
  public void testOfWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableDifferentiableAAD actualOfResult =
        RandomVariableDifferentiableAAD.of(randomVariable);

    // Assert
    RandomVariable values = actualOfResult.getValues();
    assertTrue(values instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualOfResult.getAverage(), 0.0);
    assertEquals(10.0d, actualOfResult.getMax(), 0.0);
    assertEquals(10.0d, actualOfResult.getMin(), 0.0);
    assertSame(randomVariable, values);
    assertArrayEquals(new double[] {10.0d}, actualOfResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#of(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#of(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableDifferentiableAAD RandomVariableDifferentiableAAD.of(double)"})
  public void testOfWithValue() {
    // Arrange and Act
    RandomVariableDifferentiableAAD actualOfResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Assert
    assertTrue(actualOfResult.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualOfResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualOfResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualOfResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualOfResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualOfResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualOfResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualOfResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualOfResult.getValues() instanceof Scalar);
    assertTrue(actualOfResult.isNaN() instanceof Scalar);
    assertNull(actualOfResult.getRealizations());
    assertNull(actualOfResult.getOperator());
    assertNull(actualOfResult.getRealizationsStream());
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
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableDifferentiableAAD#toString()}
   *   <li>{@link RandomVariableDifferentiableAAD#getFactory()}
   *   <li>{@link RandomVariableDifferentiableAAD#getOperatorTreeNode()}
   *   <li>{@link RandomVariableDifferentiableAAD#getTypePriority()}
   *   <li>{@link RandomVariableDifferentiableAAD#getValues()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiableAADFactory RandomVariableDifferentiableAAD.getFactory()",
    "RandomVariableDifferentiableAAD.OperatorTreeNode RandomVariableDifferentiableAAD.getOperatorTreeNode()",
    "int RandomVariableDifferentiableAAD.getTypePriority()",
    "RandomVariable RandomVariableDifferentiableAAD.getValues()",
    "java.lang.String RandomVariableDifferentiableAAD.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    ofResult.toString();
    RandomVariableDifferentiableAADFactory actualFactory = ofResult.getFactory();
    ofResult.getOperatorTreeNode();
    int actualTypePriority = ofResult.getTypePriority();

    // Assert
    assertTrue(ofResult.getValues() instanceof Scalar);
    assertEquals(0.05d, actualFactory.getBarrierDiracWidth(), 0.0);
    assertEquals(0.05d, actualFactory.getDiracDeltaApproximationWidthPerStdDev(), 0.0);
    assertEquals(
        0.5d, actualFactory.getDiracDeltaApproximationDensityRegressionWidthPerStdDev(), 0.0);
    assertEquals(3, actualTypePriority);
    assertEquals(
        DiracDeltaApproximationMethod.DISCRETE_DELTA,
        actualFactory.getDiracDeltaApproximationMethod());
    assertTrue(actualFactory.isGradientRetainsLeafNodesOnly());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getGradient(Set)"})
  public void testGetGradientWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getGradient(Set)"})
  public void testGetGradientWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    HashSet<Long> independentIDs = new HashSet<>();
    independentIDs.add(0L);
    independentIDs.add(1L);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(independentIDs).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getGradient(Set)"})
  public void testGetGradientWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(new HashSet<>()).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getGradient(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getGradient(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getGradient(Set)"})
  public void testGetGradientWithSet_whenNull() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertEquals(1, ofResult.getGradient(null).size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashSet#HashSet()} add one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getTangents(Set)"})
  public void testGetTangentsWithSet_givenOne_whenHashSetAddOne() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link HashSet#HashSet()} add zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getTangents(Set)"})
  public void testGetTangentsWithSet_givenZero_whenHashSetAddZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    HashSet<Long> dependentIDs = new HashSet<>();
    dependentIDs.add(0L);
    dependentIDs.add(1L);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(dependentIDs));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getTangents(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getTangents(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RandomVariableDifferentiableAAD.getTangents(Set)"})
  public void testGetTangentsWithSet_whenHashSet() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getTangents(new HashSet<>()));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    boolean actualEqualsResult = ofResult.equals((RandomVariable) randomVariable);

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY));

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d, 10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NEGATIVE_INFINITY, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD randomVariable2 =
        RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    ofResult.equals((RandomVariable) randomVariable2);

    // Assert
    assertEquals(1, randomVariable2.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsTen_thenReturnTrue() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertTrue(ofResult.equals((RandomVariable) Scalar.of(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#equals(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsTen_thenReturnTrue2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertTrue(ofResult.equals((RandomVariable) Scalar.of(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#get(int)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.get(int)"})
  public void testGet_thenReturnMinusOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act and Assert
    assertEquals(-1.0d, ofResult.get(1), 0.0);
    assertEquals(1, ofResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#size()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableDifferentiableAAD.size()"})
  public void testSize() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    int actualSizeResult = ofResult.size();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(4, actualSizeResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#isDeterministic()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableDifferentiableAAD.isDeterministic()"})
  public void testIsDeterministic() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    boolean actualIsDeterministicResult = ofResult.isDeterministic();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertFalse(actualIsDeterministicResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getRealizations()"})
  public void testGetRealizations() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    double[] actualRealizations = ofResult.getRealizations();

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertArrayEquals(new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualRealizations, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getRealizations()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getRealizations()"})
  public void testGetRealizations_thenReturnArrayOfDoubleWithTen() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d},
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d))
            .getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(100.0d, ofResult.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertEquals(100.0d, ofResult.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getQuantile(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getQuantile(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getQuantile(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAAD.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints() {
    // Arrange and Act
    double[] actualHistogram =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d))
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints2() {
    // Arrange and Act
    double[] actualHistogram =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)))
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    double[] actualHistogram = ofResult.getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints4() {
    // Arrange and Act
    double[] actualHistogram =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d))
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(double[])} with {@code
   * intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableDifferentiableAAD.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndZero() {
    // Arrange and Act
    double[] actualHistogram =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(-1.0d))
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange and Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d))
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
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations2() {
    // Arrange and Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)))
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
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(2.0d, new double[] {2.0d, 1.0d, 2.0d, 1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    double[][] actualHistogram = ofResult.getHistogram(10, 10.0d);

    // Assert
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {
          -4.055555555555555d,
          -2.9444444444444446d,
          -1.8333333333333333d,
          -0.7222222222222221d,
          0.38888888888888884d,
          1.4999999999999998d,
          2.6111111111111107d,
          3.7222222222222223d,
          4.833333333333334d,
          5.944444444444445d,
          7.055555555555555d
        },
        actualHistogram[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations4() {
    // Arrange and Act
    double[][] actualHistogram =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(2.0d))
            .getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableDifferentiableAAD.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations5() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(2.0d, new double[] {});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

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
   * Test {@link RandomVariableDifferentiableAAD#cache()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cache()"})
  public void testCache() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cache()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cache()"})
  public void testCache2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cache()}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cache()"})
  public void testCache_thenReturnRandomVariableDifferentiableAADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(double)"})
  public void testCapWithCap_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(double)"})
  public void testCapWithCap_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d))
            .cap(10.0d);

    // Assert
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(double)"})
  public void testCapWithCap_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualCapResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).cap(10.0d);

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(double)} with {@code cap}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(double)"})
  public void testCapWithCap_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.getValues() instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualCapResult = ofResult.cap(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualCapResult).getCloneIndependent();
    assertTrue(cloneIndependent.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    RandomVariable values = actualCapResult.getValues();
    assertTrue(
        ((RandomVariableDifferentiableAAD) values).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(values instanceof RandomVariableDifferentiableAAD);
    assertNull(values.getValues());
    assertEquals(1, cloneIndependent.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCapResult = ofResult.cap(randomVariable);

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_givenRandomVariableDifferentiableAADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));

    // Act
    RandomVariable actualCapResult =
        ofResult.cap(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnFiltrationTimeIsZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualCapResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualCapResult = ofResult.cap(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCapResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.getValues() instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(double)"})
  public void testFloorWithDouble_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(double)"})
  public void testFloorWithDouble_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d))
            .floor(10.0d);

    // Assert
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(double)"})
  public void testFloorWithDouble_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(double)"})
  public void testFloorWithDouble_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));
    RandomVariableUniqueVariable floor =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    RandomVariable values = actualFloorResult.getValues();
    assertTrue(
        ((RandomVariableDifferentiableAAD) values).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(values instanceof RandomVariableDifferentiableAAD);
    assertNull(values.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));
    RandomVariableUniqueVariable floor =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualFloorResult).getCloneIndependent();
    RandomVariableDifferentiable cloneIndependent2 = cloneIndependent.getCloneIndependent();
    assertTrue(cloneIndependent2.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent2 instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    RandomVariable values = actualFloorResult.getValues();
    assertTrue(values instanceof RandomVariableDifferentiableAADPathwise);
    assertNull(((RandomVariableDifferentiableAADPathwise) values).getRandomVariable());
    assertNull(values.getValues());
    assertEquals(1, cloneIndependent2.getGradient().size());
    assertEquals(1, cloneIndependent.getGradient().size());
    assertSame(values, cloneIndependent2.getValues());
    assertSame(values, cloneIndependent.getValues());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable8() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    RandomVariableUniqueVariable floor =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable9() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable floor =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_givenRandomVariableDifferentiableAADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));

    // Act
    RandomVariable actualFloorResult =
        ofResult.floor(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenNaNReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualFloorResult = ofResult.floor(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnFiltrationTimeIsZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualFloorResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualFloorResult = ofResult.floor(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualFloorResult = ofResult.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualFloorResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddResult =
        ofResult.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {20.0d, 9.0d, 20.0d, 9.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenNaNReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddResult = ofResult.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    RandomVariable actualAddResult = ofResult.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {20.0d, 9.0d, 20.0d, 9.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(double)"})
  public void testAddWithValue_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(double)"})
  public void testAddWithValue_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d))
            .add(10.0d);

    // Assert
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(double)"})
  public void testAddWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualAddResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).add(10.0d);

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.add(double)"})
  public void testAddWithValue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.getValues() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubResult =
        ofResult.sub(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSubResult = ofResult.sub(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubResult).getGradient().size());
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(Scalar.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(double)"})
  public void testSubWithValue_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).sub(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(double)"})
  public void testSubWithValue_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d))
            .sub(10.0d);

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(double)"})
  public void testSubWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSubResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).sub(10.0d);

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sub(double)"})
  public void testSubWithValue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMultResult =
        ofResult.mult(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable5() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable2);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {100.0d, 1.0d, 100.0d, 1.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnFiltrationTimeIsZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualMultResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualMultResult = ofResult.mult(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualMultResult).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualMultResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    RandomVariable actualMultResult = ofResult.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(double)"})
  public void testMultWithValue_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).mult(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(double)"})
  public void testMultWithValue_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d))
            .mult(10.0d);

    // Assert
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(double)"})
  public void testMultWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualMultResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).mult(10.0d);

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.mult(double)"})
  public void testMultWithValue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualDivResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDivResult =
        ofResult.div(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualDivResult = ofResult.div(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDivResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(Scalar.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(double)"})
  public void testDivWithValue_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(double)"})
  public void testDivWithValue_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange and Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d))
            .div(10.0d);

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(double)"})
  public void testDivWithValue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualDivResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).div(10.0d);

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.div(double)"})
  public void testDivWithValue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualVidResult =
        ofResult.vid(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {1.0d, -10.0d, 1.0d, -10.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    RandomVariable actualVidResult = ofResult.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {1.0d, -10.0d, 1.0d, -10.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {1.0d, -0.1d, 1.0d, -0.1d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualVidResult = ofResult.vid(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.275}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnStandardErrorIs0275() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.275d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.30250000000000005d, actualVidResult.getVariance(), 0.0);
    assertEquals(0.4033333333333334d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.55d, actualVidResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, -0.1d, 1.0d, -0.1d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.27499999999999997}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnStandardErrorIs027499999999999997() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.27499999999999997d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.30249999999999994d, actualVidResult.getVariance(), 0.0);
    assertEquals(0.40333333333333327d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.5499999999999999d, actualVidResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, -0.1d, 1.0d, -0.1d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {1.0d, -0.1d, 1.0d, -0.1d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualVidResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof Scalar);
    assertTrue(actualVidResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof Scalar);
    assertTrue(actualVidResult.isNaN() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualVidResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.pow(double)"})
  public void testPow_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).pow(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.pow(double)"})
  public void testPow_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualPowResult = ofResult.pow(10.0d);

    // Assert
    assertTrue(actualPowResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.pow(double)"})
  public void testPow_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualPowResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).pow(10.0d);

    // Assert
    assertTrue(actualPowResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#pow(double)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.pow(double)"})
  public void testPow_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualPowResult = ofResult.pow(10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualPowResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualPowResult.getValues() instanceof Scalar);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBusResult =
        ofResult.bus(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnFiltrationTimeIsZero() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBusResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualBusResult = ofResult.bus(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualBusResult).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code -0.0} and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWith00AndEleven() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    RandomVariable actualBusResult = ofResult.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {-0.0d, 11.0d, -0.0d, 11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithZeroAndEleven() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {0.0d, 11.0d, 0.0d, 11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithZeroAndZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable2);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableDifferentiableAAD2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = ofResult.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof Scalar);
    assertTrue(actualBusResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualBusResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.getValues() instanceof Scalar);
    assertTrue(actualBusResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#average()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.average()"})
  public void testAverage_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAverageResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAverageResult.isNaN() instanceof Scalar);
    assertEquals(Double.NEGATIVE_INFINITY, actualAverageResult.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualAverageResult.getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#average()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.average()"})
  public void testAverage_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualAverageResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).average();

    // Assert
    assertTrue(actualAverageResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAverageResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAverageResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#average()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.average()"})
  public void testAverage_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAverageResult = ofResult.average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAverageResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAverageResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAverageResult.getValues() instanceof Scalar);
    assertTrue(actualAverageResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    ConditionalExpectationEstimator estimator = mock(ConditionalExpectationEstimator.class);
    when(estimator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualConditionalExpectation = ofResult.getConditionalExpectation(estimator);

    // Assert
    verify(estimator).getConditionalExpectation(isA(RandomVariable.class));
    assertTrue(actualConditionalExpectation.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualConditionalExpectation.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_givenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));

    ConditionalExpectationEstimator estimator = mock(ConditionalExpectationEstimator.class);
    when(estimator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualConditionalExpectation = ofResult.getConditionalExpectation(estimator);

    // Assert
    verify(estimator).getConditionalExpectation(isA(RandomVariable.class));
    assertTrue(actualConditionalExpectation.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualConditionalExpectation.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    ConditionalExpectationEstimator estimator = mock(ConditionalExpectationEstimator.class);
    when(estimator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualConditionalExpectation = ofResult.getConditionalExpectation(estimator);

    // Assert
    verify(estimator).getConditionalExpectation(isA(RandomVariable.class));
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualConditionalExpectation.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    ConditionalExpectationEstimator estimator = mock(ConditionalExpectationEstimator.class);
    when(estimator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualConditionalExpectation = ofResult.getConditionalExpectation(estimator);

    // Assert
    verify(estimator).getConditionalExpectation(isA(RandomVariable.class));
    assertTrue(actualConditionalExpectation.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualConditionalExpectation)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualConditionalExpectation.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualConditionalExpectation).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#squared()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.squared()"})
  public void testSquared_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).squared();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#squared()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.squared()"})
  public void testSquared_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).squared();

    // Assert
    assertTrue(actualSquaredResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#squared()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.squared()"})
  public void testSquared_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualSquaredResult = ofResult.squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSquaredResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getMax(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getMin(), 0.0);
    assertEquals(10.0d, actualSquaredResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSquaredResult.size());
    assertFalse(actualSquaredResult.isDeterministic());
    assertArrayEquals(
        new double[] {100.0d, 1.0d, 100.0d, 1.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#squared()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.squared()"})
  public void testSquared_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSquaredResult = ofResult.squared();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSquaredResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSquaredResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSquaredResult.getValues() instanceof Scalar);
    assertTrue(actualSquaredResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sqrt()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sqrt()"})
  public void testSqrt_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).sqrt();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sqrt()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sqrt()"})
  public void testSqrt_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSqrtResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).sqrt();

    // Assert
    assertTrue(actualSqrtResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sqrt()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sqrt()"})
  public void testSqrt_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualSqrtResult = ofResult.sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSqrtResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getMax(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getMin(), 0.0);
    assertEquals(10.0d, actualSqrtResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSqrtResult.size());
    assertFalse(actualSqrtResult.isDeterministic());
    assertArrayEquals(
        new double[] {3.1622776601683795d, Double.NaN, 3.1622776601683795d, Double.NaN},
        actualSqrtResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sqrt()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sqrt()"})
  public void testSqrt_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSqrtResult = ofResult.sqrt();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSqrtResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSqrtResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSqrtResult.getValues() instanceof Scalar);
    assertTrue(actualSqrtResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#exp()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.exp()"})
  public void testExp_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualExpResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualExpResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#exp()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.exp()"})
  public void testExp_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualExpResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).exp();

    // Assert
    assertTrue(actualExpResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#exp()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.exp()"})
  public void testExp_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualExpResult = ofResult.exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualExpResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualExpResult.getAverage(), 0.0);
    assertEquals(0.0d, actualExpResult.getMax(), 0.0);
    assertEquals(0.0d, actualExpResult.getMin(), 0.0);
    assertEquals(10.0d, actualExpResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualExpResult.size());
    assertFalse(actualExpResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          22026.465794806718d, 0.36787944117144233d, 22026.465794806718d, 0.36787944117144233d
        },
        actualExpResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#exp()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.exp()"})
  public void testExp_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualExpResult = ofResult.exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualExpResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualExpResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualExpResult.getValues() instanceof Scalar);
    assertTrue(actualExpResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#log()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.log()"})
  public void testLog_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).log();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#log()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.log()"})
  public void testLog_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualLogResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).log();

    // Assert
    assertTrue(actualLogResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#log()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.log()"})
  public void testLog_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualLogResult = ofResult.log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualLogResult.getAverage(), 0.0);
    assertEquals(0.0d, actualLogResult.getMax(), 0.0);
    assertEquals(0.0d, actualLogResult.getMin(), 0.0);
    assertEquals(10.0d, actualLogResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualLogResult.size());
    assertFalse(actualLogResult.isDeterministic());
    assertArrayEquals(
        new double[] {2.302585092994046d, Double.NaN, 2.302585092994046d, Double.NaN},
        actualLogResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#log()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.log()"})
  public void testLog_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualLogResult = ofResult.log();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualLogResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualLogResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualLogResult.getValues() instanceof Scalar);
    assertTrue(actualLogResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sin()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sin()"})
  public void testSin_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).sin();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sin()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sin()"})
  public void testSin_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSinResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).sin();

    // Assert
    assertTrue(actualSinResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sin()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sin()"})
  public void testSin_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualSinResult = ofResult.sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSinResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSinResult.getMax(), 0.0);
    assertEquals(0.0d, actualSinResult.getMin(), 0.0);
    assertEquals(10.0d, actualSinResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualSinResult.size());
    assertFalse(actualSinResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.5440211108893698d, -0.8414709848078965d, -0.5440211108893698d, -0.8414709848078965d
        },
        actualSinResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#sin()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.sin()"})
  public void testSin_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSinResult = ofResult.sin();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSinResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSinResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSinResult.getValues() instanceof Scalar);
    assertTrue(actualSinResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cos()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cos()"})
  public void testCos_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCosResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCosResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cos()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cos()"})
  public void testCos_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualCosResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).cos();

    // Assert
    assertTrue(actualCosResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cos()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cos()"})
  public void testCos_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualCosResult = ofResult.cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCosResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualCosResult.getAverage(), 0.0);
    assertEquals(0.0d, actualCosResult.getMax(), 0.0);
    assertEquals(0.0d, actualCosResult.getMin(), 0.0);
    assertEquals(10.0d, actualCosResult.getFiltrationTime(), 0.0);
    assertEquals(4, actualCosResult.size());
    assertFalse(actualCosResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.8390715290764524d, 0.5403023058681398d, -0.8390715290764524d, 0.5403023058681398d
        },
        actualCosResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#cos()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.cos()"})
  public void testCos_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualCosResult = ofResult.cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCosResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCosResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCosResult.getValues() instanceof Scalar);
    assertTrue(actualCosResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    RandomVariableFromDoubleArray rate =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 3);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenRandomVariableDifferentiableAADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAADPathwise.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then MaxAsRandomVariableAAD return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenMaxAsRandomVariableAADReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(1.0d));

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is one hundred one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnAverageIsOneHundredOne() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(1.0d));

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(101.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(101.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {101.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Min is minus nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnMinIsMinusNine() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(1.0d, 1.0d, 3));
    RandomVariableUniqueVariable rate =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(-9.0d, actualAccrueResult.getMin(), 0.0);
    assertEquals(27.5d, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(3025.0d, actualAccrueResult.getVariance(), 0.0);
    assertEquals(4033.3333333333335d, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(46.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(55.0d, actualAccrueResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {101.0d, -9.0d, 101.0d, -9.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Min is minus ninety.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnMinIsMinusNinety() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable rate =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(-90.0d, actualAccrueResult.getMin(), 0.0);
    assertEquals(275.0d, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(302500.0d, actualAccrueResult.getVariance(), 0.0);
    assertEquals(403333.3333333333d, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(460.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(550.0d, actualAccrueResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {1010.0d, -90.0d, 1010.0d, -90.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link
   *       Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnRealizationsIsArrayOfDoubleWithNegative_infinity() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link
   *       Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenReturnRealizationsIsArrayOfDoubleWithPositive_infinity() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));
    RandomVariableFromDoubleArray rate =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 3);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(rate, 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAccrueResult).getGradient().size());
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAccrueResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.accrue(RandomVariable, double)"
  })
  public void testAccrue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.getValues() instanceof Scalar);
    assertTrue(actualAccrueResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAAD} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenRandomVariableDifferentiableAADWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_givenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAADPathwise.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then MaxAsRandomVariableAAD return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenMaxAsRandomVariableAADReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(1.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.506050605060506}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs0506050605060506() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable rate =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(-0.506050605060506d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(-1.1111111111111112d, actualDiscountResult.getMin(), 0.0);
    assertEquals(0.30253025302530256d, actualDiscountResult.getStandardError(), 0.0);
    assertEquals(0.3660982159822144d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(0.4881309546429526d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(0.6050605060506051d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          0.09900990099009901d, -1.1111111111111112d, 0.09900990099009901d, -1.1111111111111112d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.050605060506050605}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs0050605060506050605() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(1.0d, 1.0d, 3));
    RandomVariableUniqueVariable rate =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(rate, 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(-0.050605060506050605d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(-0.1111111111111111d, actualDiscountResult.getMin(), 0.0);
    assertEquals(0.0036609821598221426d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(0.0048813095464295235d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(0.030253025302530254d, actualDiscountResult.getStandardError(), 0.0);
    assertEquals(0.06050605060506051d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          0.009900990099009901d, -0.1111111111111111d, 0.009900990099009901d, -0.1111111111111111d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 0.009900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnRealizationsIsArrayOfDoubleWith0009900990099009901() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(1.0d));

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.009900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.getValues() instanceof Scalar);
    assertTrue(actualDiscountResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableFromDoubleArrayWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then CloneIndependent return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenCloneIndependentReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.getValues() instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then CloneIndependent return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenCloneIndependentReturnRandomVariableDifferentiableAAD2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD valueIfTriggerNonNegative =
        RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.getValues() instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then CloneIndependent return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenCloneIndependentReturnRandomVariableDifferentiableAAD3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableDifferentiableAAD valueIfTriggerNonNegative =
        RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.getValues() instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(10, actualChooseResult.size());
    assertEquals(10.0d, actualChooseResult.getFiltrationTime(), 0.0);
    assertFalse(actualChooseResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenValuesReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAADPathwise.of(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualChooseResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableDifferentiableAADPathwiseWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(
            valueIfTriggerNonNegative, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualChooseResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualChooseResult.getValues() instanceof Scalar);
    assertTrue(actualChooseResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableDifferentiableAADPathwiseWithValueIsTen2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAADPathwise valueIfTriggerNonNegative =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    assertTrue(actualChooseResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualChooseResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#invert()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.invert()"})
  public void testInvert_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(1.0d)).invert();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualInvertResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualInvertResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#invert()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.invert()"})
  public void testInvert_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualInvertResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(1.0d)).invert();

    // Assert
    assertTrue(actualInvertResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {1.0d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#invert()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.invert()"})
  public void testInvert_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualInvertResult = ofResult.invert();

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualInvertResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualInvertResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualInvertResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualInvertResult.getValues() instanceof Scalar);
    assertEquals(0.1d, actualInvertResult.getMax(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#abs()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.abs()"})
  public void testAbs_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange and Act
    RandomVariable actualAbsResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAbsResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAbsResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#abs()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.abs()"})
  public void testAbs_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualAbsResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).abs();

    // Assert
    assertTrue(actualAbsResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAbsResult).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#abs()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.abs()"})
  public void testAbs_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAbsResult = ofResult.abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAbsResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAbsResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAbsResult.getValues() instanceof Scalar);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAbsResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.getValues() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
    assertEquals(
        2, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(RandomVariableDifferentiableAADPathwise.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        2, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.getValues() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        2, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent();
    assertTrue(cloneIndependent.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, cloneIndependent.getGradient().size());
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable factor2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent();
    assertTrue(cloneIndependent.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, cloneIndependent.getGradient().size());
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableUniqueVariable factor1 =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true);
    RandomVariableUniqueVariable factor2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent();
    assertTrue(cloneIndependent.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, cloneIndependent.getGradient().size());
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(
            factor1, RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        2, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Gradient {@code 240682} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnGradient240682IsNull() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD factor1 =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableUniqueVariable factor2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, factor2);

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent();
    assertTrue(cloneIndependent.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    Map<Long, RandomVariable> gradient =
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient();
    assertEquals(2, gradient.size());
    assertNull(gradient.get(240682L));
    assertEquals(1, cloneIndependent.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.getValues() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenValuesReturnScalar2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.getValues() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
    assertEquals(
        2, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenValuesReturnScalar3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.getValues() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
    assertEquals(
        2, ((RandomVariableDifferentiableAAD) actualAddProductResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d, 10.0d, 3);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio5() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariableDifferentiable cloneIndependent =
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getCloneIndependent();
    assertTrue(cloneIndependent.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(cloneIndependent instanceof RandomVariableDifferentiableAAD);
    assertNull(actualAddRatioResult.getValues());
    assertNull(cloneIndependent.getValues());
    assertEquals(1, cloneIndependent.getGradient().size());
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsTwo2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsTwo3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnGradientSizeIsTwo4() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and {@code 9.9}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAnd99() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(
            RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)));
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.2749999999999999}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnStandardErrorIs02749999999999999() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d, 10.0d, 3));
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.2749999999999999d, actualAddRatioResult.getStandardError(), 0.0);
    assertEquals(0.30249999999999977d, actualAddRatioResult.getVariance(), 0.0);
    assertEquals(0.40333333333333304d, actualAddRatioResult.getSampleVariance(), 0.0);
    assertEquals(0.5499999999999998d, actualAddRatioResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.27500000000000413}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnStandardErrorIs027500000000000413() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.27500000000000413d, actualAddRatioResult.getStandardError(), 0.0);
    assertEquals(0.3025000000000091d, actualAddRatioResult.getVariance(), 0.0);
    assertEquals(0.4033333333333455d, actualAddRatioResult.getSampleVariance(), 0.0);
    assertEquals(0.5500000000000083d, actualAddRatioResult.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableDifferentiableAAD2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableDifferentiableAAD3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAddRatioResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableUniqueVariable}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableUniqueVariable() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    RandomVariableUniqueVariable numerator =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableUniqueVariable);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(
        new double[] {11.0d, 9.9d, 11.0d, 9.9d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.getValues() instanceof Scalar);
    assertTrue(actualAddRatioResult.isNaN() instanceof Scalar);
    assertNull(actualAddRatioResult.getRealizations());
    assertNull(actualAddRatioResult.getRealizationsStream());
    assertEquals(3, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableDifferentiableAADPathwiseWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualAddRatioResult).getGradient().size());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(-1.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(-1.0d, -1.0d, 3));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Gradient size is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnGradientSizeIsTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(-1.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAAD.of(-1.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Max is minus two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnMaxIsMinusTwo() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(-1.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(-2.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(-2.0d, actualSubRatioResult.getMin(), 0.0);
    assertArrayEquals(new double[] {-2.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code -0.9}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWith09() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(-1.0d));
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(-1.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {-0.9d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code -0.9}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWith092() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(-1.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(-1.0d, -1.0d, 3);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {-0.9d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 10.1}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWith101() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(-1.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {10.1d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 10.1}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWith1012() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(-1.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {10.1d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with twenty.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithTwenty() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAAD.of(-1.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with twenty.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithTwenty2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(-1.0d, -1.0d, 3));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with twenty.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithTwenty3() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAAD.of(-1.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {20.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromFloatArray(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableLazyEvaluation(10.0d));
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    ofResult.addSumProduct(factor1, new ArrayList<>());
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualSubRatioResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(-1.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, RandomVariableDifferentiableAAD.of(-1.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.getValues() instanceof Scalar);
    assertTrue(actualSubRatioResult.isNaN() instanceof Scalar);
    assertNull(actualSubRatioResult.getRealizations());
    assertNull(actualSubRatioResult.getRealizationsStream());
    assertEquals(3, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableDifferentiableAADPathwise} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableDifferentiableAADPathwiseWithValueIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(Scalar.of(10.0d));
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getGradient().size());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.isNaN()"})
  public void testIsNaN() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAAD.of(RandomVariableDifferentiableAAD.of(10.0d)).isNaN();

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
    RandomVariable actualExpectationResult = actualIsNaNResult.expectation();
    assertSame(actualIsNaNResult, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableDifferentiableAAD} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.isNaN()"})
  public void testIsNaN_givenRandomVariableDifferentiableAADWithValueIsTen_thenReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualIsNaNResult = ofResult.isNaN();

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
    RandomVariable actualExpectationResult = actualIsNaNResult.expectation();
    assertSame(actualIsNaNResult, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.isNaN()"})
  public void testIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(Double.NaN)).isNaN();

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
   * Test {@link RandomVariableDifferentiableAAD#isNaN()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.isNaN()"})
  public void testIsNaN_thenReturnRealizationsIsArrayOfDoubleWithZero() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        RandomVariableDifferentiableAAD.of(new RandomVariableFromDoubleArray(10.0d)).isNaN();

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
   * Test {@link RandomVariableDifferentiableAAD#isNaN()}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.isNaN()"})
  public void testIsNaN_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act
    RandomVariable actualIsNaNResult = RandomVariableDifferentiableAAD.of(randomVariable).isNaN();

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
   * Test {@link RandomVariableDifferentiableAAD#isNaN()}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.17677669529663687}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.isNaN()"})
  public void testIsNaN_thenReturnStandardErrorIs017677669529663687() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {
              Double.NaN, 10.0d, Double.NaN, 10.0d, Double.NaN, 10.0d, Double.NaN, 10.0d
            });

    // Act
    RandomVariable actualIsNaNResult = RandomVariableDifferentiableAAD.of(randomVariable).isNaN();

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
   * Test {@link RandomVariableDifferentiableAAD#getOperator()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getOperator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IntToDoubleFunction RandomVariableDifferentiableAAD.getOperator()"})
  public void testGetOperator() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    IntToDoubleFunction actualOperator = ofResult.getOperator();

    // Assert
    assertTrue(ofResult.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getSampleVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardErrorAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getValues() instanceof Scalar);
    assertTrue(ofResult.isNaN() instanceof Scalar);
    assertNull(ofResult.getRealizations());
    assertNull(actualOperator);
    assertNull(ofResult.getRealizationsStream());
    assertEquals(0.0d, ofResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, ofResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, ofResult.getStandardError(), 0.0);
    assertEquals(0.0d, ofResult.getVariance(), 0.0);
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(1, ofResult.size());
    assertEquals(10.0d, ofResult.getAverage(), 0.0);
    assertEquals(10.0d, ofResult.getMax(), 0.0);
    assertEquals(10.0d, ofResult.getMin(), 0.0);
    assertEquals(3, ofResult.getTypePriority());
    assertTrue(ofResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, ofResult.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getOperator()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getOperator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IntToDoubleFunction RandomVariableDifferentiableAAD.getOperator()"})
  public void testGetOperator2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    ofResult.getOperator();

    // Assert that nothing has changed
    RandomVariable values = ofResult.getValues();
    assertTrue(values instanceof RandomVariableFromDoubleArray);
    assertTrue(ofResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(ofResult.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getSampleVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardErrorAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, ofResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, ofResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, ofResult.getStandardError(), 0.0);
    assertEquals(0.0d, ofResult.getVariance(), 0.0);
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(1, ofResult.size());
    assertEquals(10.0d, ofResult.getAverage(), 0.0);
    assertEquals(10.0d, ofResult.getMax(), 0.0);
    assertEquals(10.0d, ofResult.getMin(), 0.0);
    assertEquals(3, ofResult.getTypePriority());
    assertTrue(ofResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, ofResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, values);
    assertArrayEquals(new double[] {10.0d}, ofResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getOperator()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getOperator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IntToDoubleFunction RandomVariableDifferentiableAAD.getOperator()"})
  public void testGetOperator3() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    IntToDoubleFunction actualOperator = ofResult.getOperator();

    // Assert
    assertTrue(ofResult.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getSampleVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardErrorAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    RandomVariable values = ofResult.getValues();
    assertTrue(values instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.isNaN() instanceof Scalar);
    assertNull(ofResult.getRealizations());
    assertNull(actualOperator);
    assertNull(ofResult.getRealizationsStream());
    assertEquals(0.0d, ofResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, ofResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, ofResult.getStandardError(), 0.0);
    assertEquals(0.0d, ofResult.getVariance(), 0.0);
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(1, ofResult.size());
    assertEquals(10.0d, ofResult.getAverage(), 0.0);
    assertEquals(10.0d, ofResult.getMax(), 0.0);
    assertEquals(10.0d, ofResult.getMin(), 0.0);
    assertEquals(3, ofResult.getTypePriority());
    assertTrue(ofResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, ofResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, values);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getOperator()}.
   *
   * <ul>
   *   <li>Then return applyAsDouble one is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getOperator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IntToDoubleFunction RandomVariableDifferentiableAAD.getOperator()"})
  public void testGetOperator_thenReturnApplyAsDoubleOneIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(randomVariable);

    // Act
    double actualApplyAsDoubleResult = ofResult.getOperator().applyAsDouble(1);

    // Assert
    RandomVariable values = ofResult.getValues();
    assertTrue(values instanceof RandomVariableFromDoubleArray);
    assertTrue(ofResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(ofResult.getCloneIndependent() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMaxAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.getMinAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getSampleVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getStandardErrorAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ofResult.getVarianceAsRandomVariableAAD() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(ofResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, ofResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, ofResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, ofResult.getStandardError(), 0.0);
    assertEquals(0.0d, ofResult.getVariance(), 0.0);
    assertEquals(1, ofResult.getGradient().size());
    assertEquals(1, ofResult.size());
    assertEquals(10.0d, actualApplyAsDoubleResult, 0.0);
    assertEquals(10.0d, ofResult.getAverage(), 0.0);
    assertEquals(10.0d, ofResult.getMax(), 0.0);
    assertEquals(10.0d, ofResult.getMin(), 0.0);
    assertEquals(3, ofResult.getTypePriority());
    assertTrue(ofResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, ofResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, values);
    assertArrayEquals(new double[] {10.0d}, ofResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> ofResult.apply(mock(DoubleUnaryOperator.class)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.apply(operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualVarianceAsRandomVariableAAD = ofResult.getVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualVarianceAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD2() {
    // Arrange and Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY))
            .getVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualVarianceAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getVarianceAsRandomVariableAAD()"
  })
  public void testGetVarianceAsRandomVariableAAD3() {
    // Arrange and Act
    RandomVariable actualVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .getVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualVarianceAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualVarianceAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualVarianceAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {0.0d}, actualVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

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
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD2() {
    // Arrange and Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY))
            .getSampleVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getSampleVarianceAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getSampleVarianceAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getSampleVarianceAsRandomVariableAAD()"
  })
  public void testGetSampleVarianceAsRandomVariableAAD3() {
    // Arrange and Act
    RandomVariable actualSampleVarianceAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .getSampleVarianceAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSampleVarianceAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSampleVarianceAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualSampleVarianceAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualSampleVarianceAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

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
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD2() {
    // Arrange and Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY))
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardDeviationAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardDeviationAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getStandardDeviationAsRandomVariableAAD()"
  })
  public void testGetStandardDeviationAsRandomVariableAAD3() {
    // Arrange and Act
    RandomVariable actualStandardDeviationAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .getStandardDeviationAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardDeviationAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualStandardDeviationAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardDeviationAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

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
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD2() {
    // Arrange and Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY))
            .getStandardErrorAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getStandardErrorAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAAD#getStandardErrorAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableDifferentiableAAD.getStandardErrorAsRandomVariableAAD()"
  })
  public void testGetStandardErrorAsRandomVariableAAD3() {
    // Arrange and Act
    RandomVariable actualStandardErrorAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .getStandardErrorAsRandomVariableAAD();

    // Assert
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualStandardErrorAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualStandardErrorAsRandomVariableAAD.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualStandardErrorAsRandomVariableAAD)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {0.0d}, actualStandardErrorAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getMinAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.getMinAsRandomVariableAAD()"})
  public void testGetMinAsRandomVariableAAD() {
    // Arrange and Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .getMinAsRandomVariableAAD();

    // Assert
    assertTrue(actualMinAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY},
        actualMinAsRandomVariableAAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getMinAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.getMinAsRandomVariableAAD()"})
  public void testGetMinAsRandomVariableAAD_thenReturnAverageIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMinAsRandomVariableAAD = ofResult.getMinAsRandomVariableAAD();

    // Assert
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertEquals(10.0d, actualMinAsRandomVariableAAD.getAverage(), 0.0);
    assertEquals(10.0d, actualMinAsRandomVariableAAD.getMax(), 0.0);
    assertEquals(10.0d, actualMinAsRandomVariableAAD.getMin(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualMinAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getMinAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getMinAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.getMinAsRandomVariableAAD()"})
  public void testGetMinAsRandomVariableAAD_thenReturnGradientSizeIsOne() {
    // Arrange and Act
    RandomVariable actualMinAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY))
            .getMinAsRandomVariableAAD();

    // Assert
    assertTrue(actualMinAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMinAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMinAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualMinAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMinAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMinAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualMinAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY},
        actualMinAsRandomVariableAAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getMaxAsRandomVariableAAD()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.getMaxAsRandomVariableAAD()"})
  public void testGetMaxAsRandomVariableAAD() {
    // Arrange and Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY))
            .getMaxAsRandomVariableAAD();

    // Assert
    assertTrue(actualMaxAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY},
        actualMaxAsRandomVariableAAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getMaxAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.getMaxAsRandomVariableAAD()"})
  public void testGetMaxAsRandomVariableAAD_thenReturnAverageIsTen() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualMaxAsRandomVariableAAD = ofResult.getMaxAsRandomVariableAAD();

    // Assert
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertEquals(10.0d, actualMaxAsRandomVariableAAD.getAverage(), 0.0);
    assertEquals(10.0d, actualMaxAsRandomVariableAAD.getMax(), 0.0);
    assertEquals(10.0d, actualMaxAsRandomVariableAAD.getMin(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualMaxAsRandomVariableAAD.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getMaxAsRandomVariableAAD()}.
   *
   * <ul>
   *   <li>Then return Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getMaxAsRandomVariableAAD()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableDifferentiableAAD.getMaxAsRandomVariableAAD()"})
  public void testGetMaxAsRandomVariableAAD_thenReturnGradientSizeIsOne() {
    // Arrange and Act
    RandomVariable actualMaxAsRandomVariableAAD =
        RandomVariableDifferentiableAAD.of(
                new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY))
            .getMaxAsRandomVariableAAD();

    // Assert
    assertTrue(actualMaxAsRandomVariableAAD.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMaxAsRandomVariableAAD.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMaxAsRandomVariableAAD instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualMaxAsRandomVariableAAD.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMaxAsRandomVariableAAD.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMaxAsRandomVariableAAD.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualMaxAsRandomVariableAAD).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY},
        actualMaxAsRandomVariableAAD.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getCloneIndependent()}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getCloneIndependent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAAD.getCloneIndependent()"
  })
  public void testGetCloneIndependent_thenValuesReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAAD randomVariable = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariableDifferentiable actualCloneIndependent =
        RandomVariableDifferentiableAAD.of(randomVariable).getCloneIndependent();

    // Assert
    assertTrue(actualCloneIndependent instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.expm1() instanceof RandomVariableDifferentiableAAD);
    RandomVariable values = actualCloneIndependent.getValues();
    assertTrue(values instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.isNaN() instanceof Scalar);
    assertEquals(1, actualCloneIndependent.getGradient().size());
    assertSame(randomVariable, values);
  }

  /**
   * Test {@link RandomVariableDifferentiableAAD#getCloneIndependent()}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAAD#getCloneIndependent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAAD.getCloneIndependent()"
  })
  public void testGetCloneIndependent_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariableDifferentiable actualCloneIndependent = ofResult.getCloneIndependent();

    // Assert
    assertTrue(actualCloneIndependent instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCloneIndependent).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCloneIndependent.getValues() instanceof Scalar);
    assertTrue(actualCloneIndependent.isNaN() instanceof Scalar);
    assertEquals(1, actualCloneIndependent.getGradient().size());
  }
}
