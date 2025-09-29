package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableUniqueVariable;
import net.finmath.stochastic.ConditionalExpectationEstimator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class RandomVariableFromDoubleArrayDiffblueTest {
  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double)"})
  public void testNewRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromDoubleArray.getTypePriority());
    assertEquals(1, actualRandomVariableFromDoubleArray.size());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertTrue(actualRandomVariableFromDoubleArray.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, double)"})
  public void testNewRandomVariableFromDoubleArray2() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, 10.0d);

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromDoubleArray.getTypePriority());
    assertEquals(1, actualRandomVariableFromDoubleArray.size());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertTrue(actualRandomVariableFromDoubleArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double, int)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, double, int)"})
  public void testNewRandomVariableFromDoubleArray3() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, 10.0d, 1);

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromDoubleArray.getTypePriority());
    assertEquals(1, actualRandomVariableFromDoubleArray.size());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertTrue(actualRandomVariableFromDoubleArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromDoubleArray4() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, realizations, 3);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromDoubleArray5() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, realizations, 3, 1);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromDoubleArray6() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(-1.7976931348623157E308d, realizations, 3, 1);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(
        -1.7976931348623157E308d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double[])}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, double[])"})
  public void testNewRandomVariableFromDoubleArray7() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(
            10.0d, new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualRandomVariableFromDoubleArray.getTypePriority());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMax(), 0.0);
    assertEquals(4, actualRandomVariableFromDoubleArray.size());
    assertFalse(actualRandomVariableFromDoubleArray.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double[],
   * int)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, double[], int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, double[], int)"})
  public void testNewRandomVariableFromDoubleArray8() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(
            10.0d,
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            1);

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualRandomVariableFromDoubleArray.getTypePriority());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMax(), 0.0);
    assertEquals(4, actualRandomVariableFromDoubleArray.size());
    assertFalse(actualRandomVariableFromDoubleArray.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromDoubleArray9() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromDoubleArray_givenTen_thenReturnSizeIsOne() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, realizations, 1, 1);

    // Assert
    verify(realizations).applyAsDouble(0);
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualRandomVariableFromDoubleArray.size());
    assertTrue(actualRandomVariableFromDoubleArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When one.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromDoubleArray_givenTen_whenOne_thenReturnSizeIsOne() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, realizations, 1);

    // Assert
    verify(realizations).applyAsDouble(0);
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualRandomVariableFromDoubleArray.size());
    assertTrue(actualRandomVariableFromDoubleArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, int, double)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, int, double)"})
  public void testNewRandomVariableFromDoubleArray_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, 10, 10.0d);

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromDoubleArray.getTypePriority());
    assertEquals(10, actualRandomVariableFromDoubleArray.size());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertFalse(actualRandomVariableFromDoubleArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromDoubleArray_thenAbsReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray value = new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Min is minus one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromDoubleArray_thenReturnMinIsMinusOne() {
    // Arrange
    RandomVariableUniqueVariable value =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(value);

    // Assert
    assertEquals(-1.0d, actualRandomVariableFromDoubleArray.getMin(), 0.0);
    assertEquals(2.75d, actualRandomVariableFromDoubleArray.getStandardError(), 0.0);
    assertEquals(30.25d, actualRandomVariableFromDoubleArray.getVariance(), 0.0);
    assertEquals(4, actualRandomVariableFromDoubleArray.size());
    assertEquals(4.5d, actualRandomVariableFromDoubleArray.getAverage(), 0.0);
    assertEquals(40.333333333333336d, actualRandomVariableFromDoubleArray.getSampleVariance(), 0.0);
    assertEquals(5.5d, actualRandomVariableFromDoubleArray.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromDoubleArray_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualRandomVariableFromDoubleArray.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromDoubleArray_thenReturnSizeIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation value = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(value, function);

    // Assert
    verify(function, atLeast(1)).applyAsDouble(10.0d);
    assertEquals(10, actualRandomVariableFromDoubleArray.size());
    assertEquals(10.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertFalse(actualRandomVariableFromDoubleArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromDoubleArray_thenThrowRuntimeException() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> new RandomVariableFromDoubleArray(10.0d, realizations, 1));
    verify(realizations).applyAsDouble(0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromDoubleArray_thenThrowRuntimeException2() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> new RandomVariableFromDoubleArray(10.0d, realizations, 1, 1));
    verify(realizations).applyAsDouble(0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromDoubleArray_thenThrowRuntimeException3() {
    // Arrange
    RandomVariableFromDoubleArray value = new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new RandomVariableFromDoubleArray(value, function));
    verify(function).applyAsDouble(10.0d);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return FiltrationTime is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double, IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromDoubleArray_whenOne_thenReturnFiltrationTimeIsOne() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d, realizations, 3);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualRandomVariableFromDoubleArray.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromDoubleArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromDoubleArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromDoubleArray_whenScalarWithValueIsTen() {
    // Arrange
    Scalar value = Scalar.of(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(value);

    // Assert
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(RandomVariable,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromDoubleArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromDoubleArray_whenScalarWithValueIsTen2() {
    // Arrange
    Scalar value = Scalar.of(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromDoubleArray actualRandomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(actualRandomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromDoubleArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableFromDoubleArray.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromDoubleArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromDoubleArray.equals(
            (RandomVariable) new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromDoubleArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromDoubleArray.equals(
            (RandomVariable)
                new RandomVariableLazyEvaluation(
                    Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromDoubleArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromDoubleArray.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromDoubleArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertFalse(
        randomVariableFromDoubleArray.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableFromDoubleArray#getFiltrationTime()}
   *   <li>{@link RandomVariableFromDoubleArray#getTypePriority()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableFromDoubleArray.getFiltrationTime()",
    "int RandomVariableFromDoubleArray.getTypePriority()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    double actualFiltrationTime = randomVariableFromDoubleArray.getFiltrationTime();

    // Assert
    assertEquals(1, randomVariableFromDoubleArray.getTypePriority());
    assertEquals(Double.NEGATIVE_INFINITY, actualFiltrationTime, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#get(int)}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.get(int)"})
  public void testGet() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromDoubleArray(10.0d).get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#size()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableFromDoubleArray.size()"})
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(1, new RandomVariableFromDoubleArray(10.0d).size());
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getMin()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getMin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getMin()"})
  public void testGetMin() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromDoubleArray(10.0d).getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getMax()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getMax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getMax()"})
  public void testGetMax() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromDoubleArray(10.0d).getMax(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getAverage()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getAverage()"})
  public void testGetAverage() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromDoubleArray(10.0d).getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    double actualAverage =
        randomVariableFromDoubleArray.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertEquals(100.0d, randomVariableFromDoubleArray.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getVariance()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getVariance()"})
  public void testGetVariance() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromDoubleArray(10.0d).getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    double actualVariance =
        randomVariableFromDoubleArray.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getSampleVariance()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getSampleVariance()"})
  public void testGetSampleVariance() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromDoubleArray(10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getStandardDeviation()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getStandardDeviation()"})
  public void testGetStandardDeviation() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromDoubleArray(10.0d).getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    double actualStandardDeviation =
        randomVariableFromDoubleArray.getStandardDeviation(
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getStandardError()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getStandardError()"})
  public void testGetStandardError() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromDoubleArray(10.0d).getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    double actualStandardError =
        randomVariableFromDoubleArray.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getQuantile(double)} with {@code quantile}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getQuantile(double)"})
  public void testGetQuantileWithQuantile() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromDoubleArray(10.0d).getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        randomVariableFromDoubleArray.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when05() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableFromDoubleArray(10.0d).getQuantileExpectation(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code -1.7976931348623157E308}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when17976931348623157e308() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableFromDoubleArray(10.0d)
            .getQuantileExpectation(-1.7976931348623157E308d, 10.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenNegative_infinity() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableFromDoubleArray(10.0d)
            .getQuantileExpectation(Double.NEGATIVE_INFINITY, 10.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromDoubleArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableFromDoubleArray(10.0d).getQuantileExpectation(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableFromDoubleArray.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndOne() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableFromDoubleArray(10.0d)
            .getHistogram(
                new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableFromDoubleArray.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndZero() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)
            .getHistogram(
                new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableFromDoubleArray.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations_thenReturnArrayLengthIsTwo() {
    // Arrange and Act
    double[][] actualHistogram = new RandomVariableFromDoubleArray(10.0d).getHistogram(10, 10.0d);

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
   * Test {@link RandomVariableFromDoubleArray#isDeterministic()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromDoubleArray.isDeterministic()"})
  public void testIsDeterministic() {
    // Arrange, Act and Assert
    assertTrue(new RandomVariableFromDoubleArray(10.0d).isDeterministic());
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cache()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cache()"})
  public void testCache() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCacheResult = randomVariableFromDoubleArray.cache();

    // Assert
    assertSame(randomVariableFromDoubleArray, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableFromDoubleArray.getRealizations()"})
  public void testGetRealizations() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d}, new RandomVariableFromDoubleArray(10.0d).getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#doubleValue()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableFromDoubleArray.doubleValue()"})
  public void testDoubleValue() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromDoubleArray(10.0d).doubleValue().doubleValue(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#getOperator()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#getOperator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IntToDoubleFunction RandomVariableFromDoubleArray.getOperator()"})
  public void testGetOperator() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableFromDoubleArray(10.0d).getOperator().applyAsDouble(1), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualApplyResult.size());
    assertFalse(actualApplyResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument22() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromFloatArray argument1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument23() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operator, argument1, new RandomVariableFromFloatArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableFromDoubleArray.apply(
                operator, new RandomVariableFromDoubleArray(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_givenRuntimeException_thenThrowRuntimeException2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableFromDoubleArray.apply(
                operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnRealizationsIsArrayOfDoubleWithTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(operator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableUniqueVariable argument =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualApplyResult = randomVariableFromDoubleArray.apply(operator, argument);

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(eq(10.0d), anyDouble());
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(4, actualApplyResult.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualApplyResult.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(operator, Scalar.of(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument2_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operatorOuter, atLeast(1)).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner, atLeast(1)).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualApplyResult.size());
    assertEquals(10.0d, actualApplyResult.getFiltrationTime(), 0.0);
    assertFalse(actualApplyResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument22() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromFloatArray argument1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator, DoubleBinaryOperator,
   * RandomVariable, RandomVariable)} with {@code operatorOuter}, {@code operatorInner}, {@code
   * argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleBinaryOperator,
   * DoubleBinaryOperator, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.apply(DoubleBinaryOperator, DoubleBinaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorOuterOperatorInnerArgument1Argument23() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleBinaryOperator operatorOuter = mock(DoubleBinaryOperator.class);
    when(operatorOuter.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    DoubleBinaryOperator operatorInner = mock(DoubleBinaryOperator.class);
    when(operatorInner.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromDoubleArray.apply(
            operatorOuter, operatorInner, argument1, new RandomVariableFromFloatArray(10.0d));

    // Assert
    verify(operatorOuter).applyAsDouble(10.0d, 10.0d);
    verify(operatorInner).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> randomVariableFromDoubleArray.apply(operator));
    verify(operator).applyAsDouble(10.0d);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_givenTen_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult = randomVariableFromDoubleArray.apply(operator);

    // Assert
    verify(operator).applyAsDouble(10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyResult.getVariance(), 0.0);
    assertEquals(1, actualApplyResult.getTypePriority());
    assertEquals(1, actualApplyResult.size());
    assertEquals(10.0d, actualApplyResult.getAverage(), 0.0);
    assertEquals(10.0d, actualApplyResult.getMax(), 0.0);
    assertEquals(10.0d, actualApplyResult.getMin(), 0.0);
    assertTrue(actualApplyResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cap(double)} with {@code cap}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cap(double)"})
  public void testCapWithCap() {
    // Arrange and Act
    RandomVariable actualCapResult = new RandomVariableFromDoubleArray(10.0d).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(1, actualCapResult.getTypePriority());
    assertEquals(1, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCapResult.getMax(), 0.0);
    assertEquals(10.0d, actualCapResult.getMin(), 0.0);
    assertTrue(actualCapResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCapResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromDoubleArray.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromDoubleArray.cap(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(1, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCapResult.getMax(), 0.0);
    assertEquals(10.0d, actualCapResult.getMin(), 0.0);
    assertEquals(3, actualCapResult.getTypePriority());
    assertTrue(actualCapResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCapResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualCapResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromDoubleArray.cap(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getFiltrationTime(), 0.0);
    assertFalse(actualCapResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromDoubleArray.cap(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCapResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(1, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCapResult.getMax(), 0.0);
    assertEquals(10.0d, actualCapResult.getMin(), 0.0);
    assertEquals(3, actualCapResult.getTypePriority());
    assertTrue(actualCapResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCapResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCapResult = randomVariableFromDoubleArray.cap(Scalar.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#floor(double)} with {@code floor}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.floor(double)"})
  public void testFloorWithFloor() {
    // Arrange and Act
    RandomVariable actualFloorResult = new RandomVariableFromDoubleArray(10.0d).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualFloorResult.getVariance(), 0.0);
    assertEquals(1, actualFloorResult.getTypePriority());
    assertEquals(1, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getAverage(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMax(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMin(), 0.0);
    assertTrue(actualFloorResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualFloorResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromDoubleArray.floor(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualFloorResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualFloorResult.getVariance(), 0.0);
    assertEquals(1, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getAverage(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMax(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMin(), 0.0);
    assertEquals(3, actualFloorResult.getTypePriority());
    assertTrue(actualFloorResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualFloorResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualFloorResult.getValues());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromDoubleArray.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromDoubleArray.floor(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getFiltrationTime(), 0.0);
    assertFalse(actualFloorResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromDoubleArray.floor(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualFloorResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualFloorResult.getVariance(), 0.0);
    assertEquals(1, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getAverage(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMax(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMin(), 0.0);
    assertEquals(3, actualFloorResult.getTypePriority());
    assertTrue(actualFloorResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualFloorResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualFloorResult = randomVariableFromDoubleArray.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromDoubleArray.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromDoubleArray.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(1, actualAddResult.size());
    assertEquals(20.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddResult.getMin(), 0.0);
    assertEquals(3, actualAddResult.getTypePriority());
    assertTrue(actualAddResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualAddResult.getValues());
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromDoubleArray.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualAddResult.size());
    assertEquals(10.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddResult.isDeterministic());
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromDoubleArray.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(1, actualAddResult.size());
    assertEquals(20.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddResult.getMin(), 0.0);
    assertEquals(3, actualAddResult.getTypePriority());
    assertTrue(actualAddResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddResult = randomVariableFromDoubleArray.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#add(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.add(double)"})
  public void testAddWithValue() {
    // Arrange and Act
    RandomVariable actualAddResult = new RandomVariableFromDoubleArray(10.0d).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(1, actualAddResult.getTypePriority());
    assertEquals(1, actualAddResult.size());
    assertEquals(20.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddResult.getMin(), 0.0);
    assertTrue(actualAddResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromDoubleArray.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromDoubleArray.sub(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSubResult.getMax(), 0.0);
    assertEquals(0.0d, actualSubResult.getMin(), 0.0);
    assertEquals(0.0d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(1, actualSubResult.size());
    assertEquals(3, actualSubResult.getTypePriority());
    assertTrue(actualSubResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualSubResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromDoubleArray.sub(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualSubResult.size());
    assertEquals(10.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertFalse(actualSubResult.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromDoubleArray.sub(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSubResult.getMax(), 0.0);
    assertEquals(0.0d, actualSubResult.getMin(), 0.0);
    assertEquals(0.0d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(1, actualSubResult.size());
    assertEquals(3, actualSubResult.getTypePriority());
    assertTrue(actualSubResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubResult = randomVariableFromDoubleArray.sub(Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sub(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sub(double)"})
  public void testSubWithValue() {
    // Arrange and Act
    RandomVariable actualSubResult = new RandomVariableFromDoubleArray(10.0d).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSubResult.getMax(), 0.0);
    assertEquals(0.0d, actualSubResult.getMin(), 0.0);
    assertEquals(0.0d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(1, actualSubResult.getTypePriority());
    assertEquals(1, actualSubResult.size());
    assertTrue(actualSubResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromDoubleArray.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromDoubleArray.bus(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualBusResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(0.0d, actualBusResult.getMin(), 0.0);
    assertEquals(0.0d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(1, actualBusResult.size());
    assertEquals(3, actualBusResult.getTypePriority());
    assertTrue(actualBusResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBusResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualBusResult.getValues());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromDoubleArray.bus(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualBusResult.size());
    assertEquals(10.0d, actualBusResult.getFiltrationTime(), 0.0);
    assertFalse(actualBusResult.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromDoubleArray.bus(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBusResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBusResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(-0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(-0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(-0.0d, actualBusResult.getMin(), 0.0);
    assertEquals(0.0d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(1, actualBusResult.size());
    assertEquals(3, actualBusResult.getTypePriority());
    assertTrue(actualBusResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBusResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {-0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBusResult = randomVariableFromDoubleArray.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#bus(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#bus(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.bus(double)"})
  public void testBusWithValue() {
    // Arrange and Act
    RandomVariable actualBusResult = new RandomVariableFromDoubleArray(10.0d).bus(10.0d);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(0.0d, actualBusResult.getMin(), 0.0);
    assertEquals(0.0d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(1, actualBusResult.getTypePriority());
    assertEquals(1, actualBusResult.size());
    assertTrue(actualBusResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBusResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromDoubleArray.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromDoubleArray.mult(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualMultResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(1, actualMultResult.size());
    assertEquals(100.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(100.0d, actualMultResult.getMax(), 0.0);
    assertEquals(100.0d, actualMultResult.getMin(), 0.0);
    assertEquals(3, actualMultResult.getTypePriority());
    assertTrue(actualMultResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMultResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualMultResult.getValues());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromDoubleArray.mult(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromDoubleArray);
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
   * Test {@link RandomVariableFromDoubleArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromDoubleArray.mult(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualMultResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(1, actualMultResult.size());
    assertEquals(100.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(100.0d, actualMultResult.getMax(), 0.0);
    assertEquals(100.0d, actualMultResult.getMin(), 0.0);
    assertEquals(3, actualMultResult.getTypePriority());
    assertTrue(actualMultResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMultResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualMultResult = randomVariableFromDoubleArray.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#mult(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.mult(double)"})
  public void testMultWithValue() {
    // Arrange and Act
    RandomVariable actualMultResult = new RandomVariableFromDoubleArray(10.0d).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(1, actualMultResult.getTypePriority());
    assertEquals(1, actualMultResult.size());
    assertEquals(100.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(100.0d, actualMultResult.getMax(), 0.0);
    assertEquals(100.0d, actualMultResult.getMin(), 0.0);
    assertTrue(actualMultResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMultResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromDoubleArray.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromDoubleArray.div(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(1, actualDivResult.size());
    assertEquals(1.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(1.0d, actualDivResult.getMax(), 0.0);
    assertEquals(1.0d, actualDivResult.getMin(), 0.0);
    assertEquals(3, actualDivResult.getTypePriority());
    assertTrue(actualDivResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDivResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualDivResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromDoubleArray.div(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualDivResult.size());
    assertEquals(10.0d, actualDivResult.getFiltrationTime(), 0.0);
    assertFalse(actualDivResult.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromDoubleArray.div(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDivResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(1, actualDivResult.size());
    assertEquals(1.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(1.0d, actualDivResult.getMax(), 0.0);
    assertEquals(1.0d, actualDivResult.getMin(), 0.0);
    assertEquals(3, actualDivResult.getTypePriority());
    assertTrue(actualDivResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDivResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDivResult = randomVariableFromDoubleArray.div(Scalar.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#div(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.div(double)"})
  public void testDivWithValue() {
    // Arrange and Act
    RandomVariable actualDivResult = new RandomVariableFromDoubleArray(10.0d).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(1, actualDivResult.getTypePriority());
    assertEquals(1, actualDivResult.size());
    assertEquals(1.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(1.0d, actualDivResult.getMax(), 0.0);
    assertEquals(1.0d, actualDivResult.getMin(), 0.0);
    assertTrue(actualDivResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDivResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromDoubleArray.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromDoubleArray.vid(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVidResult.getVariance(), 0.0);
    assertEquals(1, actualVidResult.size());
    assertEquals(1.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(1.0d, actualVidResult.getMax(), 0.0);
    assertEquals(1.0d, actualVidResult.getMin(), 0.0);
    assertEquals(3, actualVidResult.getTypePriority());
    assertTrue(actualVidResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVidResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualVidResult.getValues());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromDoubleArray.vid(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualVidResult.size());
    assertEquals(10.0d, actualVidResult.getFiltrationTime(), 0.0);
    assertFalse(actualVidResult.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromDoubleArray.vid(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVidResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVidResult.getVariance(), 0.0);
    assertEquals(1, actualVidResult.size());
    assertEquals(1.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(1.0d, actualVidResult.getMax(), 0.0);
    assertEquals(1.0d, actualVidResult.getMin(), 0.0);
    assertEquals(3, actualVidResult.getTypePriority());
    assertTrue(actualVidResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVidResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualVidResult = randomVariableFromDoubleArray.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#vid(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#vid(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.vid(double)"})
  public void testVidWithValue() {
    // Arrange and Act
    RandomVariable actualVidResult = new RandomVariableFromDoubleArray(10.0d).vid(10.0d);

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVidResult.getVariance(), 0.0);
    assertEquals(1, actualVidResult.getTypePriority());
    assertEquals(1, actualVidResult.size());
    assertEquals(1.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(1.0d, actualVidResult.getMax(), 0.0);
    assertEquals(1.0d, actualVidResult.getMin(), 0.0);
    assertTrue(actualVidResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVidResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#pow(double)}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.pow(double)"})
  public void testPow() {
    // Arrange and Act
    RandomVariable actualPowResult = new RandomVariableFromDoubleArray(10.0d).pow(10.0d);

    // Assert
    assertTrue(actualPowResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualPowResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualPowResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualPowResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualPowResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualPowResult.getVariance(), 0.0);
    assertEquals(1, actualPowResult.getTypePriority());
    assertEquals(1, actualPowResult.size());
    assertEquals(1.0E10d, actualPowResult.getAverage(), 0.0);
    assertEquals(1.0E10d, actualPowResult.getMax(), 0.0);
    assertEquals(1.0E10d, actualPowResult.getMin(), 0.0);
    assertTrue(actualPowResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualPowResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0E10d}, actualPowResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#average()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.average()"})
  public void testAverage() {
    // Arrange and Act
    RandomVariable actualAverageResult = new RandomVariableFromDoubleArray(10.0d).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAverageResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAverageResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAverageResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAverageResult.getVariance(), 0.0);
    assertEquals(1, actualAverageResult.getTypePriority());
    assertEquals(1, actualAverageResult.size());
    assertEquals(10.0d, actualAverageResult.getAverage(), 0.0);
    assertEquals(10.0d, actualAverageResult.getMax(), 0.0);
    assertEquals(10.0d, actualAverageResult.getMin(), 0.0);
    assertTrue(actualAverageResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAverageResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualAverageResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * RandomVariableFromDoubleArray#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ConditionalExpectationEstimator conditionalExpectationOperator =
        mock(ConditionalExpectationEstimator.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(conditionalExpectationOperator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray2);

    // Act
    RandomVariable actualConditionalExpectation =
        randomVariableFromDoubleArray.getConditionalExpectation(conditionalExpectationOperator);

    // Assert
    verify(conditionalExpectationOperator).getConditionalExpectation(isA(RandomVariable.class));
    assertSame(randomVariableFromDoubleArray2, actualConditionalExpectation);
  }

  /**
   * Test {@link
   * RandomVariableFromDoubleArray#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromDoubleArray#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ConditionalExpectationEstimator conditionalExpectationOperator =
        mock(ConditionalExpectationEstimator.class);
    when(conditionalExpectationOperator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableFromDoubleArray.getConditionalExpectation(
                conditionalExpectationOperator));
    verify(conditionalExpectationOperator).getConditionalExpectation(isA(RandomVariable.class));
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#squared()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.squared()"})
  public void testSquared() {
    // Arrange and Act
    RandomVariable actualSquaredResult = new RandomVariableFromDoubleArray(10.0d).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualSquaredResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getVariance(), 0.0);
    assertEquals(1, actualSquaredResult.getTypePriority());
    assertEquals(1, actualSquaredResult.size());
    assertEquals(100.0d, actualSquaredResult.getAverage(), 0.0);
    assertEquals(100.0d, actualSquaredResult.getMax(), 0.0);
    assertEquals(100.0d, actualSquaredResult.getMin(), 0.0);
    assertTrue(actualSquaredResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSquaredResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sqrt()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sqrt()"})
  public void testSqrt() {
    // Arrange and Act
    RandomVariable actualSqrtResult = new RandomVariableFromDoubleArray(10.0d).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualSqrtResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getVariance(), 0.0);
    assertEquals(1, actualSqrtResult.getTypePriority());
    assertEquals(1, actualSqrtResult.size());
    assertEquals(3.1622776601683795d, actualSqrtResult.getAverage(), 0.0);
    assertEquals(3.1622776601683795d, actualSqrtResult.getMax(), 0.0);
    assertEquals(3.1622776601683795d, actualSqrtResult.getMin(), 0.0);
    assertTrue(actualSqrtResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSqrtResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {3.1622776601683795d}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#invert()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.invert()"})
  public void testInvert() {
    // Arrange and Act
    RandomVariable actualInvertResult = new RandomVariableFromDoubleArray(10.0d).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInvertResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualInvertResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualInvertResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualInvertResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualInvertResult.getVariance(), 0.0);
    assertEquals(0.1d, actualInvertResult.getAverage(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMax(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMin(), 0.0);
    assertEquals(1, actualInvertResult.getTypePriority());
    assertEquals(1, actualInvertResult.size());
    assertTrue(actualInvertResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualInvertResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.1d}, actualInvertResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#abs()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.abs()"})
  public void testAbs() {
    // Arrange and Act
    RandomVariable actualAbsResult = new RandomVariableFromDoubleArray(10.0d).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAbsResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAbsResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAbsResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAbsResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAbsResult.getVariance(), 0.0);
    assertEquals(1, actualAbsResult.getTypePriority());
    assertEquals(1, actualAbsResult.size());
    assertEquals(10.0d, actualAbsResult.getAverage(), 0.0);
    assertEquals(10.0d, actualAbsResult.getMax(), 0.0);
    assertEquals(10.0d, actualAbsResult.getMin(), 0.0);
    assertTrue(actualAbsResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAbsResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualAbsResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#exp()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableFromDoubleArray RandomVariableFromDoubleArray.exp()"})
  public void testExp() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualExpResult = new RandomVariableFromDoubleArray(10.0d).exp();

    // Assert
    assertTrue(actualExpResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualExpResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpResult.getVariance(), 0.0);
    assertEquals(1, actualExpResult.getTypePriority());
    assertEquals(1, actualExpResult.size());
    assertEquals(22026.465794806718d, actualExpResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualExpResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualExpResult.getMin(), 0.0);
    assertTrue(actualExpResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualExpResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {22026.465794806718d}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#expm1()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#expm1()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableFromDoubleArray RandomVariableFromDoubleArray.expm1()"})
  public void testExpm1() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualExpm1Result =
        new RandomVariableFromDoubleArray(10.0d).expm1();

    // Assert
    assertTrue(actualExpm1Result.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpm1Result.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualExpm1Result.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getVariance(), 0.0);
    assertEquals(1, actualExpm1Result.getTypePriority());
    assertEquals(1, actualExpm1Result.size());
    assertEquals(22025.465794806718d, actualExpm1Result.getAverage(), 0.0);
    assertEquals(22025.465794806718d, actualExpm1Result.getMax(), 0.0);
    assertEquals(22025.465794806718d, actualExpm1Result.getMin(), 0.0);
    assertTrue(actualExpm1Result.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualExpm1Result.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {22025.465794806718d}, actualExpm1Result.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#log()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableFromDoubleArray RandomVariableFromDoubleArray.log()"})
  public void testLog() {
    // Arrange and Act
    RandomVariableFromDoubleArray actualLogResult = new RandomVariableFromDoubleArray(10.0d).log();

    // Assert
    assertTrue(actualLogResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualLogResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLogResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLogResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualLogResult.getVariance(), 0.0);
    assertEquals(1, actualLogResult.getTypePriority());
    assertEquals(1, actualLogResult.size());
    assertEquals(2.302585092994046d, actualLogResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualLogResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualLogResult.getMin(), 0.0);
    assertTrue(actualLogResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLogResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {2.302585092994046d}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#sin()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.sin()"})
  public void testSin() {
    // Arrange and Act
    RandomVariable actualSinResult = new RandomVariableFromDoubleArray(10.0d).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.5440211108893698d, actualSinResult.getAverage(), 0.0);
    assertEquals(-0.5440211108893698d, actualSinResult.getMax(), 0.0);
    assertEquals(-0.5440211108893698d, actualSinResult.getMin(), 0.0);
    assertEquals(0.0d, actualSinResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSinResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSinResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSinResult.getVariance(), 0.0);
    assertEquals(1, actualSinResult.getTypePriority());
    assertEquals(1, actualSinResult.size());
    assertTrue(actualSinResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSinResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {-0.5440211108893698d}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#cos()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.cos()"})
  public void testCos() {
    // Arrange and Act
    RandomVariable actualCosResult = new RandomVariableFromDoubleArray(10.0d).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.8390715290764524d, actualCosResult.getAverage(), 0.0);
    assertEquals(-0.8390715290764524d, actualCosResult.getMax(), 0.0);
    assertEquals(-0.8390715290764524d, actualCosResult.getMin(), 0.0);
    assertEquals(0.0d, actualCosResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCosResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCosResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCosResult.getVariance(), 0.0);
    assertEquals(1, actualCosResult.getTypePriority());
    assertEquals(1, actualCosResult.size());
    assertTrue(actualCosResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCosResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {-0.8390715290764524d}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromDoubleArray.accrue(
            RandomVariableDifferentiableAADPathwise.of(1.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAccrueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getVariance(), 0.0);
    assertEquals(1, actualAccrueResult.size());
    assertEquals(110.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(110.0d, actualAccrueResult.getMin(), 0.0);
    assertEquals(3, actualAccrueResult.getTypePriority());
    assertTrue(actualAccrueResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAccrueResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualAccrueResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1010.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnAverageIs10100() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromDoubleArray.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnFiltrationTimeIsOne() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromDoubleArray.accrue(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualAccrueResult.getFiltrationTime(), 0.0);
    assertEquals(10, actualAccrueResult.size());
    assertFalse(actualAccrueResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAccrueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromDoubleArray.accrue(RandomVariableDifferentiableAAD.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAccrueResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getVariance(), 0.0);
    assertEquals(1, actualAccrueResult.size());
    assertEquals(110.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(110.0d, actualAccrueResult.getMin(), 0.0);
    assertEquals(3, actualAccrueResult.getTypePriority());
    assertTrue(actualAccrueResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAccrueResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.accrue(RandomVariable, double)"})
  public void testAccrue_whenScalarWithValueIsOne_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromDoubleArray.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromDoubleArray.discount(
            RandomVariableDifferentiableAADPathwise.of(1.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualDiscountResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(0.9090909090909092d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909090909092d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909090909092d, actualDiscountResult.getMin(), 0.0);
    assertEquals(1, actualDiscountResult.size());
    assertEquals(3, actualDiscountResult.getTypePriority());
    assertTrue(actualDiscountResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualDiscountResult.getValues());
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.909090909090909}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs0909090909090909() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromDoubleArray.discount(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.909090909090909d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(1.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertEquals(1.1102230246251565E-16d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertEquals(1.232595164407831E-32d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(1.3695501826753678E-32d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(10, actualDiscountResult.size());
    assertEquals(3.510833468576701E-17d, actualDiscountResult.getStandardError(), 0.0);
    assertFalse(actualDiscountResult.isDeterministic());
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
   * Test {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.09900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs009900990099009901() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromDoubleArray.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromDoubleArray.discount(RandomVariableDifferentiableAAD.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDiscountResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(0.9090909090909092d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909090909092d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909090909092d, actualDiscountResult.getMin(), 0.0);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualDiscountResult).getGradient().size());
    assertEquals(1, actualDiscountResult.size());
    assertEquals(3, actualDiscountResult.getTypePriority());
    assertTrue(actualDiscountResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.9090909090909092d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.discount(RandomVariable, double)"
  })
  public void testDiscount_whenScalarWithValueIsOne_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromDoubleArray.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.9090909090909091d, actualDiscountResult.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableFromDoubleArrayWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromDoubleArray.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromDoubleArray.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromDoubleArray.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

    // Assert
    assertTrue(actualChooseResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromDoubleArray.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertSame(valueIfTriggerNonNegative, actualChooseResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getVariance(), 0.0);
    assertEquals(1, actualAddProductResult.size());
    assertEquals(110.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMin(), 0.0);
    assertEquals(3, actualAddProductResult.getTypePriority());
    assertTrue(actualAddProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddProductResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            RandomVariableDifferentiableAADPathwise.of(10.0d), 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getVariance(), 0.0);
    assertEquals(1, actualAddProductResult.size());
    assertEquals(110.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMin(), 0.0);
    assertEquals(3, actualAddProductResult.getTypePriority());
    assertTrue(actualAddProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddProductResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualAddProductResult.size());
    assertEquals(10.0d, actualAddProductResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddProductResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable9() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable10() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable11() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable12() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable13() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable14() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable15() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable16() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddProductResult.getValues());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable17() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddProductResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable18() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(10, actualAddProductResult.size());
    assertEquals(10.0d, actualAddProductResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddProductResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualAddProductResult.getOperator());
    assertEquals(0.0d, actualAddProductResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualAddProductResult.size());
    assertTrue(actualAddProductResult.isDeterministic());
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromDoubleArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10, actualAddProductResult.size());
    assertEquals(10.0d, actualAddProductResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddProductResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, new ArrayList<>());

    // Assert
    assertSame(randomVariableFromDoubleArray, actualAddSumProductResult);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult).getRandomVariable()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList5() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult).getRandomVariable()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList6() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList7() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult).getRandomVariable()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList8() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList9() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1810.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnAverageIs18100() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1810.0d, actualAddSumProductResult.getAverage(), 0.0);
    assertEquals(1810.0d, actualAddSumProductResult.getMax(), 0.0);
    assertEquals(1810.0d, actualAddSumProductResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1810.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10, actualAddSumProductResult.size());
    assertEquals(10.0d, actualAddSumProductResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddSumProductResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then average return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenAverageReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualAddRatioResult.getOperator());
    assertEquals(0.0d, actualAddRatioResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualAddRatioResult.size());
    assertTrue(actualAddRatioResult.isDeterministic());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10, actualAddRatioResult.size());
    assertEquals(10.0d, actualAddRatioResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddRatioResult.isDeterministic());
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(10, actualAddRatioResult.size());
    assertEquals(10.0d, actualAddRatioResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddRatioResult.isDeterministic());
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(
            numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualAddRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualAddRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualAddRatioResult.getValues());
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromDoubleArray.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then average return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenAverageReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualSubRatioResult.getOperator());
    assertEquals(0.0d, actualSubRatioResult.getFiltrationTime(), 0.0);
    assertEquals(1, actualSubRatioResult.size());
    assertTrue(actualSubRatioResult.isDeterministic());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10, actualSubRatioResult.size());
    assertEquals(10.0d, actualSubRatioResult.getFiltrationTime(), 0.0);
    assertFalse(actualSubRatioResult.isDeterministic());
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSubRatioResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(10, actualSubRatioResult.size());
    assertEquals(10.0d, actualSubRatioResult.getFiltrationTime(), 0.0);
    assertFalse(actualSubRatioResult.isDeterministic());
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(
            numerator, RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSubRatioResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualSubRatioResult.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertSame(randomVariable, actualSubRatioResult.getValues());
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromDoubleArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromDoubleArray.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.isNaN()"})
  public void testIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableFromDoubleArray(Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromDoubleArray.isNaN()"})
  public void testIsNaN_thenReturnAverageIsZero() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableFromDoubleArray(10.0d).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromDoubleArray#toString()}.
   *
   * <p>Method under test: {@link RandomVariableFromDoubleArray#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String RandomVariableFromDoubleArray.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true, filtrationTime=-Infinity,"
            + " typePriority=1]",
        new RandomVariableFromDoubleArray(10.0d).toString());
  }
}
