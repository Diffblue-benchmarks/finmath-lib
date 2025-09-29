package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableUniqueVariable;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableLazyEvaluationDiffblueTest {
  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(double)"})
  public void testNewRandomVariableLazyEvaluation() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualRandomVariableLazyEvaluation.getOperator());
    assertEquals(0, actualRandomVariableLazyEvaluation.getTypePriority());
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableLazyEvaluation.size());
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMin(), 0.0);
    assertTrue(actualRandomVariableLazyEvaluation.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(double, double)"})
  public void testNewRandomVariableLazyEvaluation2() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10.0d);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualRandomVariableLazyEvaluation.getOperator());
    assertEquals(0, actualRandomVariableLazyEvaluation.getTypePriority());
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableLazyEvaluation.size());
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMin(), 0.0);
    assertTrue(actualRandomVariableLazyEvaluation.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double, int, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double,
   * int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(double, int, double)"})
  public void testNewRandomVariableLazyEvaluation3() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualRandomVariableLazyEvaluation.getTypePriority());
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getVariance(), 0.0);
    assertEquals(10, actualRandomVariableLazyEvaluation.size());
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMin(), 0.0);
    assertFalse(actualRandomVariableLazyEvaluation.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double,
   * IntToDoubleFunction, int)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double,
   * IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableLazyEvaluation4() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, mock(IntToDoubleFunction.class), 3);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualRandomVariableLazyEvaluation.getTypePriority());
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getAverage(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getMax(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getMin(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getVariance(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertEquals(3, actualRandomVariableLazyEvaluation.size());
    assertFalse(actualRandomVariableLazyEvaluation.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double, double[])}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(double, double[])"})
  public void testNewRandomVariableLazyEvaluation5() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualRandomVariableLazyEvaluation.getTypePriority());
    assertEquals(1.0d, actualRandomVariableLazyEvaluation.getMin(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableLazyEvaluation.getMax(), 0.0);
    assertEquals(2.25d, actualRandomVariableLazyEvaluation.getStandardError(), 0.0);
    assertEquals(20.25d, actualRandomVariableLazyEvaluation.getVariance(), 0.0);
    assertEquals(27.0d, actualRandomVariableLazyEvaluation.getSampleVariance(), 0.0);
    assertEquals(4, actualRandomVariableLazyEvaluation.size());
    assertEquals(4.5d, actualRandomVariableLazyEvaluation.getStandardDeviation(), 0.0);
    assertEquals(5.5d, actualRandomVariableLazyEvaluation.getAverage(), 0.0);
    assertFalse(actualRandomVariableLazyEvaluation.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        actualRandomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(RandomVariable)"})
  public void testNewRandomVariableLazyEvaluation6() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableLazyEvaluation.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableLazyEvaluation7() {
    // Arrange
    RandomVariableFromDoubleArray value = new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(RandomVariable)"})
  public void testNewRandomVariableLazyEvaluation_thenReturnFiltrationTimeIsZero() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableLazyEvaluation.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableLazyEvaluation_thenReturnFiltrationTimeIsZero2() {
    // Arrange
    RandomVariableLazyEvaluation value = new RandomVariableLazyEvaluation(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(0.0d, actualRandomVariableLazyEvaluation.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Min is minus one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(RandomVariable)"})
  public void testNewRandomVariableLazyEvaluation_thenReturnMinIsMinusOne() {
    // Arrange
    RandomVariableUniqueVariable value =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value);

    // Assert
    assertEquals(-1.0d, actualRandomVariableLazyEvaluation.getMin(), 0.0);
    assertEquals(2.75d, actualRandomVariableLazyEvaluation.getStandardError(), 0.0);
    assertEquals(30.25d, actualRandomVariableLazyEvaluation.getVariance(), 0.0);
    assertEquals(4, actualRandomVariableLazyEvaluation.size());
    assertEquals(4.5d, actualRandomVariableLazyEvaluation.getAverage(), 0.0);
    assertEquals(40.333333333333336d, actualRandomVariableLazyEvaluation.getSampleVariance(), 0.0);
    assertEquals(5.5d, actualRandomVariableLazyEvaluation.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableLazyEvaluation.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableLazyEvaluation_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableUniqueVariable value =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value, function);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(4, actualRandomVariableLazyEvaluation.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(RandomVariable)"})
  public void testNewRandomVariableLazyEvaluation_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualRandomVariableLazyEvaluation.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableLazyEvaluation.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableLazyEvaluation_thenReturnSizeIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation value = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value, function);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualRandomVariableLazyEvaluation.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableLazyEvaluation.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableLazyEvaluation_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromDoubleArray value = new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new RandomVariableLazyEvaluation(value, function));
    verify(function).applyAsDouble(10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableLazyEvaluation.<init>(RandomVariable)"})
  public void testNewRandomVariableLazyEvaluation_whenScalarWithValueIsTen() {
    // Arrange
    Scalar value = Scalar.of(10.0d);

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value);

    // Assert
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableLazyEvaluation.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableLazyEvaluation_whenScalarWithValueIsTen2() {
    // Arrange
    Scalar value = Scalar.of(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableLazyEvaluation actualRandomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(
        actualRandomVariableLazyEvaluation.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualRandomVariableLazyEvaluation.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualRandomVariableLazyEvaluation.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableLazyEvaluation.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable) new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, 10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable)
                new RandomVariableLazyEvaluation(
                    Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable)
                new RandomVariableLazyEvaluation(
                    Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    // Act and Assert
    assertTrue(
        randomVariableLazyEvaluation.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableLazyEvaluation.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act and Assert
    assertFalse(
        randomVariableLazyEvaluation.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY);

    // Act and Assert
    assertFalse(
        randomVariableLazyEvaluation.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableLazyEvaluation#getFiltrationTime()}
   *   <li>{@link RandomVariableLazyEvaluation#getOperator()}
   *   <li>{@link RandomVariableLazyEvaluation#getTypePriority()}
   *   <li>{@link RandomVariableLazyEvaluation#size()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableLazyEvaluation.getFiltrationTime()",
    "IntToDoubleFunction RandomVariableLazyEvaluation.getOperator()",
    "int RandomVariableLazyEvaluation.getTypePriority()",
    "int RandomVariableLazyEvaluation.size()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualFiltrationTime = randomVariableLazyEvaluation.getFiltrationTime();
    IntToDoubleFunction actualOperator = randomVariableLazyEvaluation.getOperator();
    int actualTypePriority = randomVariableLazyEvaluation.getTypePriority();

    // Assert
    assertNull(actualOperator);
    assertEquals(0, actualTypePriority);
    assertEquals(0.0d, actualFiltrationTime, 0.0);
    assertEquals(1, randomVariableLazyEvaluation.size());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#get(int)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.get(int)"})
  public void testGet() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#get(int)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.get(int)"})
  public void testGet2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);
    randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(10.0d, randomVariableLazyEvaluation.get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#get(int)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.get(int)"})
  public void testGet_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d).get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getMin()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getMin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getMin()"})
  public void testGetMin() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getMin()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getMin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getMin()"})
  public void testGetMin_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d).getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getMax()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getMax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getMax()"})
  public void testGetMax() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getMax(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getMax()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getMax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getMax()"})
  public void testGetMax2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.add(2.0d);

    // Act and Assert
    assertEquals(10.0d, randomVariableLazyEvaluation.getMax(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getMax()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getMax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getMax()"})
  public void testGetMax_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d).getMax(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage()"})
  public void testGetAverage() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage()"})
  public void testGetAverage2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.add(1.0d);

    // Act and Assert
    assertEquals(10.0d, randomVariableLazyEvaluation.getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(-0.5d, 10, 10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(1000.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertEquals(1000.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertEquals(Double.NaN, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_thenReturn00() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableLazyEvaluation(10.0d, -1, 10.0d));

    // Assert
    assertEquals(-0.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_thenReturnOneHundred() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_thenReturnOneThousand() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(1000.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_thenReturnZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, -1, 10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualAverage =
        randomVariableLazyEvaluation.getAverage(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertEquals(1000.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act and Assert
    assertEquals(100.0d, randomVariableLazyEvaluation.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneThousand() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act and Assert
    assertEquals(1000.0d, randomVariableLazyEvaluation.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage()"})
  public void testGetAverage_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d).getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage()}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage()"})
  public void testGetAverage_thenReturn00() {
    // Arrange, Act and Assert
    assertEquals(-0.0d, new RandomVariableLazyEvaluation(10.0d, -1, 10.0d).getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getAverage()}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getAverage()"})
  public void testGetAverage_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance()"})
  public void testGetVariance() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance()"})
  public void testGetVariance2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.add(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(0.0d, randomVariableLazyEvaluation.getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.addProduct(new RandomVariableFromDoubleArray(10.0d), 0.0d);

    // Act
    double actualVariance =
        randomVariableLazyEvaluation.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(-990000.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code -990000.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_thenReturn9900000() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualVariance =
        randomVariableLazyEvaluation.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(-990000.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_thenReturnNaN() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    double actualVariance =
        randomVariableLazyEvaluation.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_thenReturnZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualVariance =
        randomVariableLazyEvaluation.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualVariance =
        randomVariableLazyEvaluation.getVariance(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertEquals(-990000.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@code -990000.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable_whenScalarWithValueIsTen_thenReturn9900000() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act and Assert
    assertEquals(-990000.0d, randomVariableLazyEvaluation.getVariance(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance()"})
  public void testGetVariance_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d).getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance()}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance()"})
  public void testGetVariance_thenReturn00() {
    // Arrange, Act and Assert
    assertEquals(-0.0d, new RandomVariableLazyEvaluation(10.0d, -1, 10.0d).getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getVariance()}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getVariance()"})
  public void testGetVariance_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getSampleVariance()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getSampleVariance()"})
  public void testGetSampleVariance() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getSampleVariance()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getSampleVariance()"})
  public void testGetSampleVariance2() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d, 1, 10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getSampleVariance()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getSampleVariance()"})
  public void testGetSampleVariance3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.add(0.0d);

    // Act and Assert
    assertEquals(0.0d, randomVariableLazyEvaluation.getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getSampleVariance()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getSampleVariance()"})
  public void testGetSampleVariance_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getSampleVariance()}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getSampleVariance()"})
  public void testGetSampleVariance_thenReturn00() {
    // Arrange, Act and Assert
    assertEquals(
        -0.0d, new RandomVariableLazyEvaluation(10.0d, -1, 10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getSampleVariance()}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getSampleVariance()"})
  public void testGetSampleVariance_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation()"})
  public void testGetStandardDeviation() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation()"})
  public void testGetStandardDeviation2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.add(0.5d);

    // Act and Assert
    assertEquals(0.0d, randomVariableLazyEvaluation.getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualStandardDeviation =
        randomVariableLazyEvaluation.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    double actualStandardDeviation =
        randomVariableLazyEvaluation.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualStandardDeviation =
        randomVariableLazyEvaluation.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualStandardDeviation =
        randomVariableLazyEvaluation.getStandardDeviation(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable_thenReturnZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualStandardDeviation =
        randomVariableLazyEvaluation.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN, randomVariableLazyEvaluation.getStandardDeviation(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation()"})
  public void testGetStandardDeviation_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d).getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation()}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation()"})
  public void testGetStandardDeviation_thenReturn00() {
    // Arrange, Act and Assert
    assertEquals(
        -0.0d, new RandomVariableLazyEvaluation(10.0d, -1, 10.0d).getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardDeviation()}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardDeviation()"})
  public void testGetStandardDeviation_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError()"})
  public void testGetStandardError() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError()"})
  public void testGetStandardError2() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError()"})
  public void testGetStandardError3() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN, new RandomVariableLazyEvaluation(10.0d, -1, 10.0d).getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError()"})
  public void testGetStandardError4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);
    randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(0.0d, randomVariableLazyEvaluation.getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualStandardError =
        randomVariableLazyEvaluation.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    double actualStandardError =
        randomVariableLazyEvaluation.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);
    randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualStandardError =
        randomVariableLazyEvaluation.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    double actualStandardError =
        randomVariableLazyEvaluation.getStandardError(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertEquals(Double.NaN, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable_thenReturnZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    double actualStandardError =
        randomVariableLazyEvaluation.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act and Assert
    assertEquals(Double.NaN, randomVariableLazyEvaluation.getStandardError(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getStandardError()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getStandardError()"})
  public void testGetStandardError_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableLazyEvaluation(10.0d).getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantile(double)} with {@code quantile}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantile(double)"})
  public void testGetQuantileWithQuantile() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities_thenReturnNaN() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        randomVariableLazyEvaluation.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities_thenReturnTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        randomVariableLazyEvaluation.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities_thenThrowRuntimeException() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.getQuantile(
                10.0d, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantile(double)} with {@code quantile}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantile(double)"})
  public void testGetQuantileWithQuantile_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d).getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantile(double)} with {@code quantile}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantile(double)"})
  public void testGetQuantileWithQuantile_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getQuantileExpectation(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);
    randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(10.0d, randomVariableLazyEvaluation.getQuantileExpectation(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation3() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getQuantileExpectation(10.0d, 1.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableLazyEvaluation(10.0d).getQuantileExpectation(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN,
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getQuantileExpectation(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when05() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableLazyEvaluation(10.0d).getQuantileExpectation(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenOne() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableLazyEvaluation(10.0d).getQuantileExpectation(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableLazyEvaluation.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenTwo() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableLazyEvaluation(10.0d).getQuantileExpectation(2.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableLazyEvaluation(1.0d)
            .getHistogram(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints2() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d)
            .getHistogram(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);
    randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Act
    double[] actualHistogram =
        randomVariableLazyEvaluation.getHistogram(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithOneAndZero() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d)
            .getHistogram(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndOne() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableLazyEvaluation(10.0d)
            .getHistogram(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>When array of {@code double} with one and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_whenArrayOfDoubleWithOneAndOne() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d)
            .getHistogram(new double[] {1.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 1.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableLazyEvaluation.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange and Act
    double[][] actualHistogram = new RandomVariableLazyEvaluation(10.0d).getHistogram(10, 10.0d);

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
   * Test {@link RandomVariableLazyEvaluation#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableLazyEvaluation.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations2() {
    // Arrange and Act
    double[][] actualHistogram =
        new RandomVariableLazyEvaluation(2.0d, 10, 2.0d).getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableLazyEvaluation.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations3() {
    // Arrange and Act
    double[][] actualHistogram =
        new RandomVariableLazyEvaluation(2.0d, 0, 2.0d).getHistogram(10, 10.0d);

    // Assert
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
   * Test {@link RandomVariableLazyEvaluation#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableLazyEvaluation.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations4() {
    // Arrange and Act
    double[][] actualHistogram =
        new RandomVariableLazyEvaluation(2.0d, -1, 2.0d).getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -0.0d},
        actualHistogram[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableLazyEvaluation.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations5() {
    // Arrange and Act
    double[][] actualHistogram =
        new RandomVariableLazyEvaluation(2.0d, 10, 2.0d).getHistogram(1, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(new double[] {0.0d, 1.0d}, actualHistogram[1], 0.0);
    assertArrayEquals(new double[] {Double.NaN, Double.NaN}, actualHistogram[0], 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isDeterministic()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.isDeterministic()"})
  public void testIsDeterministic_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isDeterministic()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableLazyEvaluation.isDeterministic()"})
  public void testIsDeterministic_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new RandomVariableLazyEvaluation(10.0d).isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#expand(int)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#expand(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.expand(int)"})
  public void testExpand() {
    // Arrange and Act
    RandomVariable actualExpandResult =
        new RandomVariableLazyEvaluation(Double.NaN, 10, Double.NaN).expand(10);

    // Assert
    assertTrue(actualExpandResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.variance() instanceof RandomVariableFromDoubleArray);
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
          Double.NaN
        },
        actualExpandResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#expand(int)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#expand(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.expand(int)"})
  public void testExpand_thenReturnFiltrationTimeIsOne() {
    // Arrange and Act
    RandomVariable actualExpandResult =
        new RandomVariableLazyEvaluation(1.0d, 10, Double.NaN).expand(10);

    // Assert
    assertTrue(actualExpandResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpandResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualExpandResult.getFiltrationTime(), 0.0);
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
          Double.NaN
        },
        actualExpandResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#expand(int)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#expand(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.expand(int)"})
  public void testExpand_thenReturnFiltrationTimeIsZero() {
    // Arrange and Act
    RandomVariable actualExpandResult = new RandomVariableLazyEvaluation(10.0d).expand(10);

    // Assert
    assertTrue(actualExpandResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualExpandResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualExpandResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpandResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpandResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpandResult.getVariance(), 0.0);
    assertEquals(10.0d, actualExpandResult.getAverage(), 0.0);
    assertEquals(10.0d, actualExpandResult.getMax(), 0.0);
    assertEquals(10.0d, actualExpandResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualExpandResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#expand(int)}.
   *
   * <ul>
   *   <li>Then return Max is {@code -1.7976931348623157E308}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#expand(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.expand(int)"})
  public void testExpand_thenReturnMaxIs17976931348623157e308() {
    // Arrange and Act
    RandomVariable actualExpandResult =
        new RandomVariableLazyEvaluation(Double.NaN, 0, Double.NaN).expand(10);

    // Assert
    assertTrue(actualExpandResult instanceof RandomVariableFromDoubleArray);
    assertEquals(-1.7976931348623157E308d, actualExpandResult.getMax(), 0.0);
    assertEquals(0, actualExpandResult.size());
    assertEquals(Double.MAX_VALUE, actualExpandResult.getMin(), 0.0);
    assertArrayEquals(new double[] {}, actualExpandResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getRealizations()"})
  public void testGetRealizations() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getRealizations()"})
  public void testGetRealizations2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        randomVariableLazyEvaluation.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRealizations()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getRealizations()"})
  public void testGetRealizations_thenReturnArrayOfDoubleWithTen() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d}, new RandomVariableLazyEvaluation(10.0d).getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRealizations()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableLazyEvaluation.getRealizations()"})
  public void testGetRealizations_thenReturnEmptyArrayOfDouble() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {}, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#doubleValue()}.
   *
   * <ul>
   *   <li>Then return doubleValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableLazyEvaluation.doubleValue()"})
  public void testDoubleValue_thenReturnDoubleValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableLazyEvaluation(10.0d).doubleValue().doubleValue(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#doubleValue()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableLazyEvaluation.doubleValue()"})
  public void testDoubleValue_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).doubleValue());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRandomVariable()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRandomVariable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray RandomVariableLazyEvaluation.getRandomVariable()"
  })
  public void testGetRandomVariable() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariable =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).getRandomVariable();

    // Assert
    assertTrue(actualRandomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRandomVariable()}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRandomVariable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray RandomVariableLazyEvaluation.getRandomVariable()"
  })
  public void testGetRandomVariable_thenReturnFiltrationTimeIs05() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariable =
        new RandomVariableLazyEvaluation(0.5d, 10, 10.0d).getRandomVariable();

    // Assert
    assertTrue(actualRandomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualRandomVariable.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRandomVariable()}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRandomVariable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray RandomVariableLazyEvaluation.getRandomVariable()"
  })
  public void testGetRandomVariable_thenReturnFiltrationTimeIsZero() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariable =
        new RandomVariableLazyEvaluation(10.0d).getRandomVariable();

    // Assert
    assertEquals(0.0d, actualRandomVariable.getFiltrationTime(), 0.0);
    assertEquals(1, actualRandomVariable.size());
    assertTrue(actualRandomVariable.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualRandomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#getRandomVariable()}.
   *
   * <ul>
   *   <li>Then return Max is {@code -1.7976931348623157E308}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#getRandomVariable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray RandomVariableLazyEvaluation.getRandomVariable()"
  })
  public void testGetRandomVariable_thenReturnMaxIs17976931348623157e308() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariable =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).getRandomVariable();

    // Assert
    assertEquals(-1.7976931348623157E308d, actualRandomVariable.getMax(), 0.0);
    assertEquals(0, actualRandomVariable.size());
    assertEquals(Double.MAX_VALUE, actualRandomVariable.getMin(), 0.0);
    assertEquals(Double.NaN, actualRandomVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, actualRandomVariable.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualRandomVariable.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualRandomVariable.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualRandomVariable.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualRandomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_givenTen_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualApplyResult.getOperator());
    assertEquals(0.0d, actualApplyResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualApplyResult.size());
    assertTrue(actualApplyResult.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableUniqueVariable argument1 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualApplyResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result3 = expm1Result2.expm1();
    assertTrue(expm1Result3 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result4 = expm1Result3.expm1();
    assertTrue(expm1Result4 instanceof RandomVariableLazyEvaluation);
    assertEquals(4, actualApplyResult.size());
    assertEquals(4, expm1Result.size());
    assertEquals(4, expm1Result2.size());
    assertEquals(4, expm1Result3.size());
    assertEquals(4, expm1Result4.size());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_thenReturnSizeIsFour2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableUniqueVariable argument2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, argument1, argument2);

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualApplyResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result3 = expm1Result2.expm1();
    assertTrue(expm1Result3 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result4 = expm1Result3.expm1();
    assertTrue(expm1Result4 instanceof RandomVariableLazyEvaluation);
    assertEquals(4, actualApplyResult.size());
    assertEquals(4, expm1Result.size());
    assertEquals(4, expm1Result2.size());
    assertEquals(4, expm1Result3.size());
    assertEquals(4, expm1Result4.size());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_thenThrowRuntimeException() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new RuntimeException());
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(
                operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new RuntimeException());
    Scalar argument1 = Scalar.of(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(
                operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new RuntimeException());
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> randomVariableLazyEvaluation.apply(operator, argument1, Scalar.of(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_whenScalarWithValueIsTen3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    Scalar argument1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_whenScalarWithValueIsTen4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, argument1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument22() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new RuntimeException());
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(
                operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument23() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualApplyResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result3 = expm1Result2.expm1();
    assertTrue(expm1Result3 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result4 = expm1Result3.expm1();
    assertTrue(expm1Result4 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result5 = expm1Result4.expm1();
    assertTrue(expm1Result5 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result5.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(10, expm1Result2.size());
    assertEquals(10, expm1Result3.size());
    assertEquals(10, expm1Result4.size());
    assertEquals(10, expm1Result5.size());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument24() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new RuntimeException());
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(
                operator, argument1, new RandomVariableLazyEvaluation(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument25() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualApplyResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result3 = expm1Result2.expm1();
    assertTrue(expm1Result3 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result4 = expm1Result3.expm1();
    assertTrue(expm1Result4 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result5 = expm1Result4.expm1();
    assertTrue(expm1Result5 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result5.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(10, expm1Result2.size());
    assertEquals(10, expm1Result3.size());
    assertEquals(10, expm1Result4.size());
    assertEquals(10, expm1Result5.size());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument26() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument27() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument28() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument29() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, argument1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then expm1 return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenExpm1ReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualApplyResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result3 = expm1Result2.expm1();
    assertTrue(expm1Result3 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result3.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, expm1Result2.getTypePriority());
    assertEquals(0, expm1Result3.getTypePriority());
    assertEquals(10, expm1Result2.size());
    assertEquals(10, expm1Result3.size());
    assertEquals(10.0d, expm1Result2.getFiltrationTime(), 0.0);
    assertEquals(10.0d, expm1Result3.getFiltrationTime(), 0.0);
    assertFalse(expm1Result2.isDeterministic());
    assertFalse(expm1Result3.isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then expm1 return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenExpm1ReturnRandomVariableLazyEvaluation2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    RandomVariableUniqueVariable argument =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualApplyResult = randomVariableLazyEvaluation.apply(operator, argument);

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualApplyResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result3 = expm1Result2.expm1();
    assertTrue(expm1Result3 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result3.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, expm1Result2.getTypePriority());
    assertEquals(0, expm1Result3.getTypePriority());
    assertEquals(10, expm1Result2.size());
    assertEquals(10, expm1Result3.size());
    assertEquals(10.0d, expm1Result2.getFiltrationTime(), 0.0);
    assertEquals(10.0d, expm1Result3.getFiltrationTime(), 0.0);
    assertFalse(expm1Result2.isDeterministic());
    assertFalse(expm1Result3.isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableUniqueVariable argument =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualApplyResult = randomVariableLazyEvaluation.apply(operator, argument);

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(4, actualApplyResult.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operator, new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualApplyResult.size());
    assertEquals(Double.NaN, actualApplyResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualApplyResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualApplyResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualApplyResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualApplyResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, Scalar.of(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(operator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument22() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(
                operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d)));
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument23() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument24() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    Scalar argument1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument25() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument26() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument27() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, Scalar.of(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument28() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument29() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableLazyEvaluation.apply(
            operatorOuter,
            operatorInner,
            argument1,
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument210() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableLazyEvaluation.apply(
                operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d)));
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> randomVariableLazyEvaluation.apply(operator));
    verify(operator).applyAsDouble(10.0d);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult = randomVariableLazyEvaluation.apply(operator);

    // Assert
    verify(operator).applyAsDouble(10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualApplyResult.getOperator());
    assertEquals(0.0d, actualApplyResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualApplyResult.size());
    assertTrue(actualApplyResult.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult = randomVariableLazyEvaluation.apply(operator);

    // Assert
    assertTrue(actualApplyResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualApplyResult.size());
    assertEquals(10.0d, actualApplyResult.getFiltrationTime(), 0.0);
    assertFalse(actualApplyResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(double)"})
  public void testCapWithDouble_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualCapResult = new RandomVariableLazyEvaluation(10.0d).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualCapResult.getOperator());
    assertEquals(0.0d, actualCapResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualCapResult.size());
    assertTrue(actualCapResult.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(double)"})
  public void testCapWithDouble_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualCapResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getFiltrationTime(), 0.0);
    assertFalse(actualCapResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualCapResult.size());
    assertEquals(Double.NaN, actualCapResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualCapResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualCapResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableLazyEvaluation.cap(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualCapResult = randomVariableLazyEvaluation.cap(Scalar.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualCapResult = randomVariableLazyEvaluation.cap(Scalar.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCapResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(double)"})
  public void testFloorWithDouble_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualFloorResult = new RandomVariableLazyEvaluation(10.0d).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualFloorResult.getOperator());
    assertEquals(0.0d, actualFloorResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualFloorResult.size());
    assertTrue(actualFloorResult.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(double)"})
  public void testFloorWithDouble_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualFloorResult =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getFiltrationTime(), 0.0);
    assertFalse(actualFloorResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(Double.NaN);
    randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(Double.NaN));

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualFloorResult.size());
    assertEquals(Double.NaN, actualFloorResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualFloorResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualFloorResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableLazyEvaluation.floor(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualFloorResult = randomVariableLazyEvaluation.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualFloorResult = randomVariableLazyEvaluation.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cache()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cache()"})
  public void testCache() {
    // Arrange and Act
    RandomVariable actualCacheResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).cache();

    // Assert
    assertTrue(actualCacheResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCacheResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cache()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cache()"})
  public void testCache2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    RandomVariable actualCacheResult = randomVariableLazyEvaluation.cache();

    // Assert
    assertSame(randomVariableLazyEvaluation, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cache()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cache()"})
  public void testCache3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    randomVariableLazyEvaluation.addProduct(new RandomVariableFromDoubleArray(10.0d), 1.0d);

    // Act
    RandomVariable actualCacheResult = randomVariableLazyEvaluation.cache();

    // Assert
    assertTrue(actualCacheResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCacheResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCacheResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualAddResult.size());
    assertEquals(Double.NaN, actualAddResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualAddResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualAddResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableLazyEvaluation.add(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddResult = randomVariableLazyEvaluation.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddResult = randomVariableLazyEvaluation.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(double)"})
  public void testAddWithValue_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualAddResult = new RandomVariableLazyEvaluation(10.0d).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualAddResult.getOperator());
    assertEquals(0.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualAddResult.size());
    assertTrue(actualAddResult.isDeterministic());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#add(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.add(double)"})
  public void testAddWithValue_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualAddResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualAddResult.size());
    assertEquals(10.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddResult.isDeterministic());
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnFiltrationTimeIsNaN() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableLazyEvaluation(Double.NaN, 10, 10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(Double.NaN, actualSubResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualSubResult.size());
    assertEquals(Double.NaN, actualSubResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualSubResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualSubResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableLazyEvaluation.sub(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubResult = randomVariableLazyEvaluation.sub(Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubResult = randomVariableLazyEvaluation.sub(Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(double)"})
  public void testSubWithValue_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualSubResult = new RandomVariableLazyEvaluation(10.0d).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualSubResult.getOperator());
    assertEquals(0.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualSubResult.size());
    assertTrue(actualSubResult.isDeterministic());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sub(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sub(double)"})
  public void testSubWithValue_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualSubResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualSubResult.size());
    assertEquals(10.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertFalse(actualSubResult.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualMultResult.size());
    assertEquals(Double.NaN, actualMultResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualMultResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualMultResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableLazyEvaluation.mult(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualMultResult = randomVariableLazyEvaluation.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualMultResult = randomVariableLazyEvaluation.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(double)"})
  public void testMultWithValue_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualMultResult = new RandomVariableLazyEvaluation(10.0d).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualMultResult.getOperator());
    assertEquals(0.0d, actualMultResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualMultResult.size());
    assertTrue(actualMultResult.isDeterministic());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#mult(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.mult(double)"})
  public void testMultWithValue_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualMultResult =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualMultResult.size());
    assertEquals(10.0d, actualMultResult.getFiltrationTime(), 0.0);
    assertFalse(actualMultResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualDivResult.size());
    assertEquals(Double.NaN, actualDivResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualDivResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualDivResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableLazyEvaluation.div(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDivResult = randomVariableLazyEvaluation.div(Scalar.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualDivResult = randomVariableLazyEvaluation.div(Scalar.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(double)"})
  public void testDivWithValue_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualDivResult = new RandomVariableLazyEvaluation(10.0d).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualDivResult.getOperator());
    assertEquals(0.0d, actualDivResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualDivResult.size());
    assertTrue(actualDivResult.isDeterministic());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#div(double)} with {@code value}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.div(double)"})
  public void testDivWithValue_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualDivResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualDivResult.size());
    assertEquals(10.0d, actualDivResult.getFiltrationTime(), 0.0);
    assertFalse(actualDivResult.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualVidResult.size());
    assertEquals(Double.NaN, actualVidResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualVidResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualVidResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableLazyEvaluation.vid(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualVidResult = randomVariableLazyEvaluation.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualVidResult = randomVariableLazyEvaluation.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#pow(double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.pow(double)"})
  public void testPow_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualPowResult = new RandomVariableLazyEvaluation(10.0d).pow(10.0d);

    // Assert
    assertTrue(actualPowResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualPowResult.getOperator());
    assertEquals(0.0d, actualPowResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualPowResult.size());
    assertTrue(actualPowResult.isDeterministic());
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#pow(double)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.pow(double)"})
  public void testPow_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualPowResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).pow(10.0d);

    // Assert
    assertTrue(actualPowResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualPowResult.size());
    assertEquals(10.0d, actualPowResult.getFiltrationTime(), 0.0);
    assertFalse(actualPowResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d
        },
        actualPowResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableLazyEvaluation(10.0d, 0, 10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualBusResult.size());
    assertEquals(Double.NaN, actualBusResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualBusResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualBusResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableLazyEvaluation.bus(new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualBusResult = randomVariableLazyEvaluation.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualBusResult = randomVariableLazyEvaluation.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#average()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.average()"})
  public void testAverage() {
    // Arrange and Act
    RandomVariable actualAverageResult =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#average()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.average()"})
  public void testAverage2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);
    randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAverageResult = randomVariableLazyEvaluation.average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#average()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.average()"})
  public void testAverage_givenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange and Act
    RandomVariable actualAverageResult = new RandomVariableLazyEvaluation(10.0d).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#average()}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.average()"})
  public void testAverage_thenReturnAverageIs00() {
    // Arrange and Act
    RandomVariable actualAverageResult =
        new RandomVariableLazyEvaluation(10.0d, -1, 10.0d).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableLazyEvaluation);
    assertEquals(-0.0d, actualAverageResult.getAverage(), 0.0);
    assertEquals(-0.0d, actualAverageResult.getMax(), 0.0);
    assertEquals(-0.0d, actualAverageResult.getMin(), 0.0);
    assertArrayEquals(new double[] {-0.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#average()}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.average()"})
  public void testAverage_thenReturnAverageIsNaN() {
    // Arrange and Act
    RandomVariable actualAverageResult =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableLazyEvaluation);
    assertEquals(Double.NaN, actualAverageResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualAverageResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualAverageResult.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#squared()}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.squared()"})
  public void testSquared_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualSquaredResult = new RandomVariableLazyEvaluation(10.0d).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualSquaredResult.getOperator());
    assertEquals(0.0d, actualSquaredResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualSquaredResult.size());
    assertTrue(actualSquaredResult.isDeterministic());
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#squared()}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.squared()"})
  public void testSquared_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualSquaredResult.size());
    assertEquals(10.0d, actualSquaredResult.getFiltrationTime(), 0.0);
    assertFalse(actualSquaredResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d, 100.0d
        },
        actualSquaredResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sqrt()}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sqrt()"})
  public void testSqrt_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualSqrtResult = new RandomVariableLazyEvaluation(10.0d).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualSqrtResult.getOperator());
    assertEquals(0.0d, actualSqrtResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getVariance(), 0.0);
    assertEquals(1, actualSqrtResult.size());
    assertTrue(actualSqrtResult.isDeterministic());
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sqrt()}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sqrt()"})
  public void testSqrt_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualSqrtResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualSqrtResult.size());
    assertEquals(10.0d, actualSqrtResult.getFiltrationTime(), 0.0);
    assertEquals(2.308477965716968E-8d, actualSqrtResult.getStandardError(), 0.0);
    assertEquals(5.329070518200751E-15d, actualSqrtResult.getVariance(), 0.0);
    assertEquals(5.921189464667502E-15d, actualSqrtResult.getSampleVariance(), 0.0);
    assertEquals(7.300048299977713E-8d, actualSqrtResult.getStandardDeviation(), 0.0);
    assertFalse(actualSqrtResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d,
          3.1622776601683795d
        },
        actualSqrtResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#exp()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.exp()"})
  public void testExp_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualExpResult = new RandomVariableLazyEvaluation(10.0d).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualExpResult.getOperator());
    assertEquals(0.0d, actualExpResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualExpResult.size());
    assertTrue(actualExpResult.isDeterministic());
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#exp()}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.exp()"})
  public void testExp_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualExpResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualExpResult.size());
    assertEquals(10.0d, actualExpResult.getFiltrationTime(), 0.0);
    assertFalse(actualExpResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d,
          22026.465794806718d
        },
        actualExpResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#log()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.log()"})
  public void testLog_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualLogResult = new RandomVariableLazyEvaluation(10.0d).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualLogResult.getOperator());
    assertEquals(0.0d, actualLogResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualLogResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLogResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLogResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualLogResult.getVariance(), 0.0);
    assertEquals(1, actualLogResult.size());
    assertTrue(actualLogResult.isDeterministic());
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#log()}.
   *
   * <ul>
   *   <li>Then return Variance is {@code -2.6645352591003757E-15}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.log()"})
  public void testLog_thenReturnVarianceIs26645352591003757e15() {
    // Arrange and Act
    RandomVariable actualLogResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableLazyEvaluation);
    assertEquals(-2.6645352591003757E-15d, actualLogResult.getVariance(), 0.0);
    assertEquals(-2.960594732333751E-15d, actualLogResult.getSampleVariance(), 0.0);
    assertEquals(10, actualLogResult.size());
    assertEquals(10.0d, actualLogResult.getFiltrationTime(), 0.0);
    assertFalse(actualLogResult.isDeterministic());
    assertEquals(Double.NaN, actualLogResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualLogResult.getStandardError(), 0.0);
    assertArrayEquals(
        new double[] {
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d,
          2.302585092994046d
        },
        actualLogResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sin()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sin()"})
  public void testSin_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualSinResult = new RandomVariableLazyEvaluation(10.0d).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualSinResult.getOperator());
    assertEquals(0.0d, actualSinResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualSinResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSinResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSinResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSinResult.getVariance(), 0.0);
    assertEquals(1, actualSinResult.size());
    assertTrue(actualSinResult.isDeterministic());
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#sin()}.
   *
   * <ul>
   *   <li>Then return StandardDeviation is {@code 1.2904784139758924E-8}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.sin()"})
  public void testSin_thenReturnStandardDeviationIs12904784139758924e8() {
    // Arrange and Act
    RandomVariable actualSinResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableLazyEvaluation);
    assertEquals(1.2904784139758924E-8d, actualSinResult.getStandardDeviation(), 0.0);
    assertEquals(1.6653345369377348E-16d, actualSinResult.getVariance(), 0.0);
    assertEquals(1.8503717077085943E-16d, actualSinResult.getSampleVariance(), 0.0);
    assertEquals(10, actualSinResult.size());
    assertEquals(10.0d, actualSinResult.getFiltrationTime(), 0.0);
    assertEquals(4.080851059445486E-9d, actualSinResult.getStandardError(), 0.0);
    assertFalse(actualSinResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d,
          -0.5440211108893698d
        },
        actualSinResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cos()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cos()"})
  public void testCos_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualCosResult = new RandomVariableLazyEvaluation(10.0d).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualCosResult.getOperator());
    assertEquals(-0.8390715290764524d, actualCosResult.getAverage(), 0.0);
    assertEquals(0.0d, actualCosResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualCosResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCosResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCosResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCosResult.getVariance(), 0.0);
    assertEquals(1, actualCosResult.size());
    assertTrue(actualCosResult.isDeterministic());
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#cos()}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.8390715290764525}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.cos()"})
  public void testCos_thenReturnAverageIs08390715290764525() {
    // Arrange and Act
    RandomVariable actualCosResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableLazyEvaluation);
    assertEquals(-0.8390715290764525d, actualCosResult.getAverage(), 0.0);
    assertEquals(1.4901161193847656E-8d, actualCosResult.getStandardDeviation(), 0.0);
    assertEquals(10, actualCosResult.size());
    assertEquals(10.0d, actualCosResult.getFiltrationTime(), 0.0);
    assertEquals(2.220446049250313E-16d, actualCosResult.getVariance(), 0.0);
    assertEquals(2.4671622769447924E-16d, actualCosResult.getSampleVariance(), 0.0);
    assertEquals(4.712160915387242E-9d, actualCosResult.getStandardError(), 0.0);
    assertFalse(actualCosResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d,
          -0.8390715290764524d
        },
        actualCosResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double, double)}
   *       with time is one and value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_givenRandomVariableLazyEvaluationWithTimeIsOneAndValueIsOne() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 1.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1010.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnAverageIs10100() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is one hundred one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnAverageIsOneHundredOne() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertEquals(101.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(101.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(101.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          101.0d, 101.0d, 101.0d, 101.0d, 101.0d, 101.0d, 101.0d, 101.0d, 101.0d, 101.0d
        },
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnRealizationsIsArrayOfDoubleWithOneHundredTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAccrueResult = randomVariableLazyEvaluation.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnRealizationsIsArrayOfDoubleWithOneHundredTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(new RandomVariableLazyEvaluation(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(new RandomVariableLazyEvaluation(1.0d, 0, 1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualAccrueResult.size());
    assertEquals(Double.NaN, actualAccrueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualAccrueResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualAccrueResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_whenRandomVariableLazyEvaluationWithValueIsOne() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableLazyEvaluation.accrue(new RandomVariableLazyEvaluation(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.accrue(RandomVariable, double)"})
  public void testAccrue_whenScalarWithValueIsOne() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualAccrueResult = randomVariableLazyEvaluation.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.09900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs009900990099009901() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.009900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs0009900990099009901() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0.009900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.009900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.009900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertEquals(1.6463612699567982E-10d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertEquals(2.710505431213761E-20d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(3.0116727013486236E-20d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(5.206251464550825E-11d, actualDiscountResult.getStandardError(), 0.0);
    assertArrayEquals(
        new double[] {
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d,
          0.009900990099009901d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnFiltrationTimeIsTwo() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(
            new RandomVariableLazyEvaluation(2.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(2.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d,
          0.9090909090909091d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 0.9090909090909091}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnRealizationsIsArrayOfDoubleWith09090909090909091() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 0.9090909090909091}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnRealizationsIsArrayOfDoubleWith090909090909090912() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(new RandomVariableLazyEvaluation(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(
            new RandomVariableLazyEvaluation(1.0d, 0, 1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualDiscountResult.size());
    assertEquals(Double.NaN, actualDiscountResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualDiscountResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_whenRandomVariableLazyEvaluationWithValueIsOne() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(new RandomVariableLazyEvaluation(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.discount(RandomVariable, double)"
  })
  public void testDiscount_whenScalarWithValueIsOne() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableLazyEvaluation.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d,
          0.09090909090909091d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose6() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableLazyEvaluationWithValueIs05() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(-0.5d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableLazyEvaluationWithValueIs052() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(-0.5d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then expm1 return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenExpm1ReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualChooseResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result2.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, expm1Result2.getTypePriority());
    assertEquals(0.0d, expm1Result2.getSampleVariance(), 0.0);
    assertEquals(1, expm1Result2.size());
    assertEquals(10.0d, expm1Result2.getFiltrationTime(), 0.0);
    assertFalse(expm1Result2.isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnRealizationsIsArrayOfDoubleWithTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnRealizationsIsArrayOfDoubleWithTen3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableLazyEvaluationWithValueIsTen3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableLazyEvaluationWithValueIsTen4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation valueIfTriggerNonNegative =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualChooseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnRealizationsIsArrayOfDoubleWithTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableLazyEvaluation.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#invert()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.invert()"})
  public void testInvert_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualInvertResult = new RandomVariableLazyEvaluation(1.0d, 10, 1.0d).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableLazyEvaluation);
    assertEquals(1.0d, actualInvertResult.getAverage(), 0.0);
    assertEquals(1.0d, actualInvertResult.getFiltrationTime(), 0.0);
    assertEquals(1.0d, actualInvertResult.getMax(), 0.0);
    assertEquals(1.0d, actualInvertResult.getMin(), 0.0);
    assertEquals(10, actualInvertResult.size());
    assertFalse(actualInvertResult.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualInvertResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#invert()}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.invert()"})
  public void testInvert_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualInvertResult = new RandomVariableLazyEvaluation(10.0d).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualInvertResult.getOperator());
    assertEquals(0.0d, actualInvertResult.getFiltrationTime(), 0.0);
    assertEquals(0.1d, actualInvertResult.getAverage(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMax(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMin(), 0.0);
    assertEquals(1, actualInvertResult.size());
    assertTrue(actualInvertResult.isDeterministic());
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#abs()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with
   *       value is ten.
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.abs()"})
  public void testAbs_givenRandomVariableLazyEvaluationWithValueIsTen_thenReturnOperatorIsNull() {
    // Arrange and Act
    RandomVariable actualAbsResult = new RandomVariableLazyEvaluation(10.0d).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualAbsResult.getOperator());
    assertEquals(0.0d, actualAbsResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualAbsResult.size());
    assertTrue(actualAbsResult.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#abs()}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.abs()"})
  public void testAbs_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariable actualAbsResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualAbsResult.size());
    assertEquals(10.0d, actualAbsResult.getFiltrationTime(), 0.0);
    assertFalse(actualAbsResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualAbsResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(new RandomVariableLazyEvaluation(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(new RandomVariableLazyEvaluation(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble6() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble7() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnSizeIsZero() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            new RandomVariableLazyEvaluation(10.0d, 0, 10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualAddProductResult.size());
    assertEquals(Double.NaN, actualAddProductResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualAddProductResult.getVariance(), 0.0);
    assertArrayEquals(new double[] {}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable9() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable10() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable11() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable12() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable13() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable14() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualAddProductResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result2.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, expm1Result2.getTypePriority());
    assertEquals(0.0d, expm1Result2.getSampleVariance(), 0.0);
    assertEquals(1, expm1Result2.size());
    assertEquals(10.0d, expm1Result2.getFiltrationTime(), 0.0);
    assertFalse(expm1Result2.isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable15() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable16() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableLazyEvaluation.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio6() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio7() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio8() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then expm1 return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenExpm1ReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualAddRatioResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result2.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, expm1Result2.getTypePriority());
    assertEquals(0.0d, expm1Result2.getSampleVariance(), 0.0);
    assertEquals(1, expm1Result2.size());
    assertEquals(10.0d, expm1Result2.getFiltrationTime(), 0.0);
    assertFalse(expm1Result2.isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithEleven() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithEleven2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithEleven3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithEleven4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithEleven5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableLazyEvaluation.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio6() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio7() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio8() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then expm1 return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenExpm1ReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 0, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result = actualSubRatioResult.expm1();
    assertTrue(expm1Result instanceof RandomVariableLazyEvaluation);
    RandomVariable expm1Result2 = expm1Result.expm1();
    assertTrue(expm1Result2 instanceof RandomVariableLazyEvaluation);
    assertTrue(expm1Result2.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, expm1Result2.getTypePriority());
    assertEquals(0.0d, expm1Result2.getSampleVariance(), 0.0);
    assertEquals(1, expm1Result2.size());
    assertEquals(10.0d, expm1Result2.getFiltrationTime(), 0.0);
    assertFalse(expm1Result2.isDeterministic());
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNine() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNine2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNine3() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNine4() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNine5() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableLazyEvaluationWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableLazyEvaluation#RandomVariableLazyEvaluation(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableLazyEvaluationWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, new RandomVariableLazyEvaluation(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluation.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableLazyEvaluation.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.isNaN()"})
  public void testIsNaN() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualIsNaNResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isNaN()}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.isNaN()"})
  public void testIsNaN2() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableLazyEvaluation(0.0d, 10, 10.0d).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualIsNaNResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isNaN()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.isNaN()"})
  public void testIsNaN_thenReturnRealizationsIsArrayOfDoubleWithOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableLazyEvaluation(Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {1.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isNaN()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.isNaN()"})
  public void testIsNaN_thenReturnRealizationsIsArrayOfDoubleWithOneAndOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        new RandomVariableLazyEvaluation(10.0d, 10, Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualIsNaNResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluation#isNaN()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluation#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableLazyEvaluation.isNaN()"})
  public void testIsNaN_thenReturnRealizationsIsArrayOfDoubleWithZero() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableLazyEvaluation(10.0d).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }
}
