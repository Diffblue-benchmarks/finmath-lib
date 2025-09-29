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

public class RandomVariableFromFloatArrayDiffblueTest {
  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double)"})
  public void testNewRandomVariableFromFloatArray() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(1, actualRandomVariableFromFloatArray.size());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertTrue(actualRandomVariableFromFloatArray.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double, double)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, double)"})
  public void testNewRandomVariableFromFloatArray2() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10.0d);

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(1, actualRandomVariableFromFloatArray.size());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertTrue(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double, double, int)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, double, int)"})
  public void testNewRandomVariableFromFloatArray3() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10.0d, 1);

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(1, actualRandomVariableFromFloatArray.size());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertTrue(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromFloatArray4() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, realizations, 3);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromFloatArray5() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, realizations, 3, 1);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double, double[])}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, double[])"})
  public void testNewRandomVariableFromFloatArray6() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(
            10.0d, new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(4, actualRandomVariableFromFloatArray.size());
    assertFalse(actualRandomVariableFromFloatArray.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double, float[])}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * float[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, float[])"})
  public void testNewRandomVariableFromFloatArray7() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, new float[] {10.0f, 0.5f, 10.0f, 0.5f});

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.5d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(2.375d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(22.5625d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(30.083333333333332d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(4, actualRandomVariableFromFloatArray.size());
    assertEquals(4.75d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertFalse(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double, float[], int)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * float[], int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, float[], int)"})
  public void testNewRandomVariableFromFloatArray8() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, new float[] {10.0f, 0.5f, 10.0f, 0.5f}, 1);

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.5d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(2.375d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(22.5625d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(30.083333333333332d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(4, actualRandomVariableFromFloatArray.size());
    assertEquals(4.75d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertFalse(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromFloatArray9() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromFloatArray10() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromFloatArray11() {
    // Arrange
    RandomVariableFromDoubleArray value = new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromFloatArray12() {
    // Arrange
    RandomVariableFromFloatArray value = new RandomVariableFromFloatArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <ul>
   *   <li>Given {@code -0.5}.
   *   <li>Then return Average is {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromFloatArray_given05_thenReturnAverageIs05() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(-0.5d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, realizations, 3, 1);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertEquals(-0.5d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(-0.5d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(-0.5d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertArrayEquals(
        new double[] {-0.5d, -0.5d, -0.5d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromFloatArray_givenTen_thenReturnSizeIsOne() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, realizations, 1, 1);

    // Assert
    verify(realizations).applyAsDouble(0);
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualRandomVariableFromFloatArray.size());
    assertTrue(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When one.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromFloatArray_givenTen_whenOne_thenReturnSizeIsOne() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, realizations, 1);

    // Assert
    verify(realizations).applyAsDouble(0);
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualRandomVariableFromFloatArray.size());
    assertTrue(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double, int, double)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, int, double)"})
  public void testNewRandomVariableFromFloatArray_thenAbsReturnRandomVariableFromFloatArray() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10, 10.0d);

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableFromFloatArray.getTypePriority());
    assertEquals(10, actualRandomVariableFromFloatArray.size());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertFalse(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Min is minus one.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromFloatArray_thenReturnMinIsMinusOne() {
    // Arrange
    RandomVariableUniqueVariable value =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(value);

    // Assert
    assertEquals(-1.0d, actualRandomVariableFromFloatArray.getMin(), 0.0);
    assertEquals(2.75d, actualRandomVariableFromFloatArray.getStandardError(), 0.0);
    assertEquals(30.25d, actualRandomVariableFromFloatArray.getVariance(), 0.0);
    assertEquals(4, actualRandomVariableFromFloatArray.size());
    assertEquals(4.5d, actualRandomVariableFromFloatArray.getAverage(), 0.0);
    assertEquals(40.333333333333336d, actualRandomVariableFromFloatArray.getSampleVariance(), 0.0);
    assertEquals(5.5d, actualRandomVariableFromFloatArray.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromFloatArray_thenReturnSizeIsTen() {
    // Arrange and Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualRandomVariableFromFloatArray.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromFloatArray_thenReturnSizeIsTen2() {
    // Arrange
    RandomVariableLazyEvaluation value = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(value, function);

    // Assert
    verify(function, atLeast(1)).applyAsDouble(10.0d);
    assertEquals(10, actualRandomVariableFromFloatArray.size());
    assertEquals(10.0d, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertFalse(actualRandomVariableFromFloatArray.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromFloatArray_thenThrowRuntimeException() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> new RandomVariableFromFloatArray(10.0d, realizations, 1));
    verify(realizations).applyAsDouble(0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int, int)"
  })
  public void testNewRandomVariableFromFloatArray_thenThrowRuntimeException2() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> new RandomVariableFromFloatArray(10.0d, realizations, 1, 1));
    verify(realizations).applyAsDouble(0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromFloatArray_thenThrowRuntimeException3() {
    // Arrange
    RandomVariableFromDoubleArray value = new RandomVariableFromDoubleArray(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new RandomVariableFromFloatArray(value, function));
    verify(function).applyAsDouble(10.0d);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return FiltrationTime is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double,
   * IntToDoubleFunction, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(double, IntToDoubleFunction, int)"})
  public void testNewRandomVariableFromFloatArray_whenNaN_thenReturnFiltrationTimeIsNaN() {
    // Arrange
    IntToDoubleFunction realizations = mock(IntToDoubleFunction.class);
    when(realizations.applyAsDouble(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(Double.NaN, realizations, 3);

    // Assert
    verify(realizations, atLeast(1)).applyAsDouble(anyInt());
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(Double.NaN, actualRandomVariableFromFloatArray.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualRandomVariableFromFloatArray.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableFromFloatArray.<init>(RandomVariable)"})
  public void testNewRandomVariableFromFloatArray_whenScalarWithValueIsTen() {
    // Arrange
    Scalar value = Scalar.of(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(value);

    // Assert
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable,
   * DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#RandomVariableFromFloatArray(RandomVariable, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromFloatArray.<init>(RandomVariable, DoubleUnaryOperator)"
  })
  public void testNewRandomVariableFromFloatArray_whenScalarWithValueIsTen2() {
    // Arrange
    Scalar value = Scalar.of(10.0d);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariableFromFloatArray actualRandomVariableFromFloatArray =
        new RandomVariableFromFloatArray(value, function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    assertTrue(actualRandomVariableFromFloatArray.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableFromFloatArray.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableFromFloatArray.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableFromFloatArray.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY);

    // Act
    boolean actualEqualsResult =
        randomVariableFromFloatArray.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromFloatArray.equals(
            (RandomVariable) new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromFloatArray.equals(
            (RandomVariable) new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromFloatArray.equals(
            (RandomVariable)
                new RandomVariableLazyEvaluation(
                    Double.NEGATIVE_INFINITY, 10, Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_thenReturnTrue() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    boolean actualEqualsResult =
        randomVariableFromFloatArray.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act and Assert
    assertFalse(
        randomVariableFromFloatArray.equals((RandomVariable) Scalar.of(Double.NEGATIVE_INFINITY)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableFromFloatArray#getFiltrationTime()}
   *   <li>{@link RandomVariableFromFloatArray#getTypePriority()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableFromFloatArray.getFiltrationTime()",
    "int RandomVariableFromFloatArray.getTypePriority()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    double actualFiltrationTime = randomVariableFromFloatArray.getFiltrationTime();

    // Assert
    assertEquals(1, randomVariableFromFloatArray.getTypePriority());
    assertEquals(Double.NEGATIVE_INFINITY, actualFiltrationTime, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#get(int)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.get(int)"})
  public void testGet() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromFloatArray(10.0d).get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#size()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableFromFloatArray.size()"})
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(1, new RandomVariableFromFloatArray(10.0d).size());
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getMin()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getMin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getMin()"})
  public void testGetMin() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromFloatArray(10.0d).getMin(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getMax()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getMax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getMax()"})
  public void testGetMax() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromFloatArray(10.0d).getMax(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getAverage()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getAverage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getAverage()"})
  public void testGetAverage() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromFloatArray(10.0d).getAverage(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    double actualAverage =
        randomVariableFromFloatArray.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    double actualAverage =
        randomVariableFromFloatArray.getAverage(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act and Assert
    assertEquals(100.0d, randomVariableFromFloatArray.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getVariance()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getVariance()"})
  public void testGetVariance() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromFloatArray(10.0d).getVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    double actualVariance =
        randomVariableFromFloatArray.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getSampleVariance()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getSampleVariance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getSampleVariance()"})
  public void testGetSampleVariance() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromFloatArray(10.0d).getSampleVariance(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getStandardDeviation()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getStandardDeviation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getStandardDeviation()"})
  public void testGetStandardDeviation() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromFloatArray(10.0d).getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    double actualStandardDeviation =
        randomVariableFromFloatArray.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getStandardError()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getStandardError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getStandardError()"})
  public void testGetStandardError() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new RandomVariableFromFloatArray(10.0d).getStandardError(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    double actualStandardError =
        randomVariableFromFloatArray.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getQuantile(double)} with {@code quantile}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getQuantile(double)"})
  public void testGetQuantileWithQuantile() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromFloatArray(10.0d).getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        randomVariableFromFloatArray.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when05() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableFromFloatArray(10.0d).getQuantileExpectation(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code -1.7976931348623157E308}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when17976931348623157e308() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableFromFloatArray(10.0d)
            .getQuantileExpectation(-1.7976931348623157E308d, 10.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenNegative_infinity() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableFromFloatArray(10.0d)
            .getQuantileExpectation(Double.NEGATIVE_INFINITY, 10.0d),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableFromFloatArray.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableFromFloatArray(10.0d).getQuantileExpectation(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableFromFloatArray.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndOne() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableFromFloatArray(10.0d)
            .getHistogram(
                new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableFromFloatArray.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithZeroAndZero() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY)
            .getHistogram(
                new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableFromFloatArray.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations_thenReturnArrayLengthIsTwo() {
    // Arrange and Act
    double[][] actualHistogram = new RandomVariableFromFloatArray(10.0d).getHistogram(10, 10.0d);

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
   * Test {@link RandomVariableFromFloatArray#isDeterministic()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableFromFloatArray.isDeterministic()"})
  public void testIsDeterministic() {
    // Arrange, Act and Assert
    assertTrue(new RandomVariableFromFloatArray(10.0d).isDeterministic());
  }

  /**
   * Test {@link RandomVariableFromFloatArray#cache()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cache()"})
  public void testCache() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCacheResult = randomVariableFromFloatArray.cache();

    // Assert
    assertSame(randomVariableFromFloatArray, actualCacheResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableFromFloatArray.getRealizations()"})
  public void testGetRealizations() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d}, new RandomVariableFromFloatArray(10.0d).getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#doubleValue()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableFromFloatArray.doubleValue()"})
  public void testDoubleValue() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new RandomVariableFromFloatArray(10.0d).doubleValue().doubleValue(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#getOperator()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#getOperator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IntToDoubleFunction RandomVariableFromFloatArray.getOperator()"})
  public void testGetOperator() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new RandomVariableFromFloatArray(10.0d).getOperator().applyAsDouble(1), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument22() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromFloatArray argument1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument23() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableLazyEvaluation argument1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(
            operator, argument1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument24() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(
            operator, argument1, new RandomVariableFromFloatArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument25() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d, 10.0d);

    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(
            operator, argument1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(10.0d, 10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableFromFloatArray.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_givenRuntimeException_thenThrowRuntimeException2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableFromFloatArray.apply(
                operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d)));
    verify(operator).applyAsDouble(10.0d, 10.0d);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnSizeIsFour() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);
    RandomVariableUniqueVariable argument =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualApplyResult = randomVariableFromFloatArray.apply(operator, argument);

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(eq(10.0d), anyDouble());
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(4, actualApplyResult.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(
            operator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    verify(operator, atLeast(1)).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualApplyResult.size());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualApplyResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(operator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(operator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);
    when(operator.applyAsDouble(anyDouble(), anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult =
        randomVariableFromFloatArray.apply(operator, Scalar.of(10.0d));

    // Assert
    verify(operator).applyAsDouble(10.0d, 10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> randomVariableFromFloatArray.apply(operator));
    verify(operator).applyAsDouble(10.0d);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_givenTen_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult = randomVariableFromFloatArray.apply(operator);

    // Assert
    verify(operator).applyAsDouble(10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualApplyResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#cap(double)} with {@code cap}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(double)"})
  public void testCapWithCap() {
    // Arrange and Act
    RandomVariable actualCapResult = new RandomVariableFromFloatArray(10.0d).cap(10.0d);

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenAbsReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromFloatArray.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromFloatArray.cap(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromFloatArray.cap(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getFiltrationTime(), 0.0);
    assertFalse(actualCapResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromFloatArray.cap(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCapResult =
        randomVariableFromFloatArray.cap(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#cap(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualCapResult = randomVariableFromFloatArray.cap(Scalar.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#floor(double)} with {@code floor}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(double)"})
  public void testFloorWithFloor() {
    // Arrange and Act
    RandomVariable actualFloorResult = new RandomVariableFromFloatArray(10.0d).floor(10.0d);

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromFloatArray.floor(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenAbsReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromFloatArray.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromFloatArray.floor(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getFiltrationTime(), 0.0);
    assertFalse(actualFloorResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualFloorResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromFloatArray.floor(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualFloorResult =
        randomVariableFromFloatArray.floor(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#floor(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualFloorResult = randomVariableFromFloatArray.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromFloatArray.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromFloatArray.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualAddResult.size());
    assertEquals(10.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertFalse(actualAddResult.isDeterministic());
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromFloatArray.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromFloatArray.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddResult =
        randomVariableFromFloatArray.add(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddResult = randomVariableFromFloatArray.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#add(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.add(double)"})
  public void testAddWithValue() {
    // Arrange and Act
    RandomVariable actualAddResult = new RandomVariableFromFloatArray(10.0d).add(10.0d);

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromFloatArray.sub(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromFloatArray.sub(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualSubResult.size());
    assertEquals(10.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertFalse(actualSubResult.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromFloatArray.sub(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromFloatArray.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubResult =
        randomVariableFromFloatArray.sub(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubResult = randomVariableFromFloatArray.sub(Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#sub(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sub(double)"})
  public void testSubWithValue() {
    // Arrange and Act
    RandomVariable actualSubResult = new RandomVariableFromFloatArray(10.0d).sub(10.0d);

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromFloatArray.bus(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromFloatArray.bus(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualBusResult.size());
    assertEquals(10.0d, actualBusResult.getFiltrationTime(), 0.0);
    assertFalse(actualBusResult.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromFloatArray.bus(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualBusResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromFloatArray.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualBusResult =
        randomVariableFromFloatArray.bus(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualBusResult = randomVariableFromFloatArray.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#bus(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#bus(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.bus(double)"})
  public void testBusWithValue() {
    // Arrange and Act
    RandomVariable actualBusResult = new RandomVariableFromFloatArray(10.0d).bus(10.0d);

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromFloatArray.mult(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromFloatArray.mult(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromFloatArray.mult(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromFloatArray.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualMultResult =
        randomVariableFromFloatArray.mult(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualMultResult = randomVariableFromFloatArray.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#mult(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.mult(double)"})
  public void testMultWithValue() {
    // Arrange and Act
    RandomVariable actualMultResult = new RandomVariableFromFloatArray(10.0d).mult(10.0d);

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromFloatArray.div(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromFloatArray.div(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualDivResult.size());
    assertEquals(10.0d, actualDivResult.getFiltrationTime(), 0.0);
    assertFalse(actualDivResult.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromFloatArray.div(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromFloatArray.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDivResult =
        randomVariableFromFloatArray.div(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDivResult = randomVariableFromFloatArray.div(Scalar.of(10.0d));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#div(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.div(double)"})
  public void testDivWithValue() {
    // Arrange and Act
    RandomVariable actualDivResult = new RandomVariableFromFloatArray(10.0d).div(10.0d);

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromFloatArray.vid(RandomVariableDifferentiableAADPathwise.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromFloatArray.vid(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromFloatArray);
    assertEquals(10, actualVidResult.size());
    assertEquals(10.0d, actualVidResult.getFiltrationTime(), 0.0);
    assertFalse(actualVidResult.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromFloatArray.vid(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromFloatArray.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualVidResult =
        randomVariableFromFloatArray.vid(new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualVidResult = randomVariableFromFloatArray.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#vid(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#vid(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.vid(double)"})
  public void testVidWithValue() {
    // Arrange and Act
    RandomVariable actualVidResult = new RandomVariableFromFloatArray(10.0d).vid(10.0d);

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#pow(double)}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.pow(double)"})
  public void testPow() {
    // Arrange and Act
    RandomVariable actualPowResult = new RandomVariableFromFloatArray(10.0d).pow(10.0d);

    // Assert
    assertTrue(actualPowResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualPowResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#average()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.average()"})
  public void testAverage() {
    // Arrange and Act
    RandomVariable actualAverageResult = new RandomVariableFromFloatArray(10.0d).average();

    // Assert
    assertTrue(actualAverageResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAverageResult.variance() instanceof RandomVariableFromFloatArray);
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
   * RandomVariableFromFloatArray#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ConditionalExpectationEstimator conditionalExpectationOperator =
        mock(ConditionalExpectationEstimator.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(conditionalExpectationOperator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    // Act
    RandomVariable actualConditionalExpectation =
        randomVariableFromFloatArray.getConditionalExpectation(conditionalExpectationOperator);

    // Assert
    verify(conditionalExpectationOperator).getConditionalExpectation(isA(RandomVariable.class));
    assertSame(randomVariableFromDoubleArray, actualConditionalExpectation);
  }

  /**
   * Test {@link
   * RandomVariableFromFloatArray#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFromFloatArray#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ConditionalExpectationEstimator conditionalExpectationOperator =
        mock(ConditionalExpectationEstimator.class);
    when(conditionalExpectationOperator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            randomVariableFromFloatArray.getConditionalExpectation(conditionalExpectationOperator));
    verify(conditionalExpectationOperator).getConditionalExpectation(isA(RandomVariable.class));
  }

  /**
   * Test {@link RandomVariableFromFloatArray#squared()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.squared()"})
  public void testSquared() {
    // Arrange and Act
    RandomVariable actualSquaredResult = new RandomVariableFromFloatArray(10.0d).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSquaredResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#sqrt()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sqrt()"})
  public void testSqrt() {
    // Arrange and Act
    RandomVariable actualSqrtResult = new RandomVariableFromFloatArray(10.0d).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSqrtResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#invert()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.invert()"})
  public void testInvert() {
    // Arrange and Act
    RandomVariable actualInvertResult = new RandomVariableFromFloatArray(10.0d).invert();

    // Assert
    assertTrue(actualInvertResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualInvertResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#abs()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.abs()"})
  public void testAbs() {
    // Arrange and Act
    RandomVariable actualAbsResult = new RandomVariableFromFloatArray(10.0d).abs();

    // Assert
    assertTrue(actualAbsResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAbsResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#exp()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableFromFloatArray RandomVariableFromFloatArray.exp()"})
  public void testExp() {
    // Arrange and Act
    RandomVariableFromFloatArray actualExpResult = new RandomVariableFromFloatArray(10.0d).exp();

    // Assert
    assertTrue(actualExpResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#log()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableFromFloatArray RandomVariableFromFloatArray.log()"})
  public void testLog() {
    // Arrange and Act
    RandomVariableFromFloatArray actualLogResult = new RandomVariableFromFloatArray(10.0d).log();

    // Assert
    assertTrue(actualLogResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualLogResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#sin()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.sin()"})
  public void testSin() {
    // Arrange and Act
    RandomVariable actualSinResult = new RandomVariableFromFloatArray(10.0d).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSinResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#cos()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.cos()"})
  public void testCos() {
    // Arrange and Act
    RandomVariable actualCosResult = new RandomVariableFromFloatArray(10.0d).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCosResult.variance() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromFloatArray.accrue(
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
   * Test {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1010.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnAverageIs10100() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromFloatArray.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromFloatArray);
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenReturnFiltrationTimeIsOne() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromFloatArray.accrue(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.accrue(RandomVariable, double)"})
  public void testAccrue_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromFloatArray.accrue(RandomVariableDifferentiableAAD.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.accrue(RandomVariable, double)"})
  public void testAccrue_whenRandomVariableFromFloatArrayWithValueIsOne() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        randomVariableFromFloatArray.accrue(new RandomVariableFromFloatArray(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then abs return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.accrue(RandomVariable, double)"})
  public void testAccrue_whenScalarWithValueIsOne_thenAbsReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAccrueResult = randomVariableFromFloatArray.accrue(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {110.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromFloatArray.discount(
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
    assertEquals(0.9090909090909091d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMin(), 0.0);
    assertEquals(1, actualDiscountResult.size());
    assertEquals(3, actualDiscountResult.getTypePriority());
    assertTrue(actualDiscountResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountResult.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualDiscountResult.getValues());
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.9090909361839294}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs09090909361839294() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromFloatArray.discount(
            new RandomVariableLazyEvaluation(1.0d, 10, 1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromFloatArray);
    assertEquals(0.9090909361839294d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909361839294d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909361839294d, actualDiscountResult.getMin(), 0.0);
    assertEquals(1.0d, actualDiscountResult.getFiltrationTime(), 0.0);
    assertEquals(10, actualDiscountResult.size());
    assertFalse(actualDiscountResult.isDeterministic());
    assertArrayEquals(
        new double[] {
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d,
          0.9090909361839294d
        },
        actualDiscountResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.09900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenReturnAverageIs009900990099009901() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromFloatArray.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromFloatArray);
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.discount(RandomVariable, double)"
  })
  public void testDiscount_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromFloatArray.discount(RandomVariableDifferentiableAAD.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromFloatArray);
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
    assertEquals(0.9090909090909091d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.9090909090909091d, actualDiscountResult.getMin(), 0.0);
    assertEquals(1, actualDiscountResult.size());
    assertEquals(3, actualDiscountResult.getTypePriority());
    assertTrue(actualDiscountResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.discount(RandomVariable, double)"
  })
  public void testDiscount_whenRandomVariableFromFloatArrayWithValueIsOne() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromFloatArray.discount(new RandomVariableFromFloatArray(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then abs return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.discount(RandomVariable, double)"
  })
  public void testDiscount_whenScalarWithValueIsOne_thenAbsReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        randomVariableFromFloatArray.discount(Scalar.of(1.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {0.9090909090909091d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with
   *       value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_givenRandomVariableFromFloatArrayWithValueIsNegative_infinity() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromFloatArray.choose(
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
   * Test {@link RandomVariableFromFloatArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromFloatArray.choose(
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
   * Test {@link RandomVariableFromFloatArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)}
   *       with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_thenReturnRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray valueIfTriggerNonNegative =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromFloatArray.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertSame(valueIfTriggerNonNegative, actualChooseResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with value
   *       is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenRandomVariableFromFloatArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromFloatArray.choose(
            valueIfTriggerNonNegative, new RandomVariableFromFloatArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromFloatArray.choose(valueIfTriggerNonNegative, Scalar.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose_whenScalarWithValueIsTen_thenReturnScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar valueIfTriggerNonNegative = Scalar.of(10.0d);

    // Act
    RandomVariable actualChooseResult =
        randomVariableFromFloatArray.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertSame(valueIfTriggerNonNegative, actualChooseResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(new RandomVariableFromFloatArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(RandomVariableDifferentiableAAD.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble_whenScalarWithValueIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable5() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable6() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable7() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, Scalar.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable8() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromFloatArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable9() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable10() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable11() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable12() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable13() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable14() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable15() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable16() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable17() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray factor1 = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable18() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable19() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable20() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable21() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation factor1 = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
            factor1, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable22() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, Scalar.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable23() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable24() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(factor1, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable25() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD factor1 = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
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
   * Test {@link RandomVariableFromFloatArray#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise factor1 =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        randomVariableFromFloatArray.addProduct(
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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, new ArrayList<>());

    // Assert
    assertSame(randomVariableFromFloatArray, actualAddSumProductResult);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList5() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromFloatArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromFloatArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray5() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenAbsReturnRandomVariableFromDoubleArray6() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1810.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnAverageIs18100() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

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
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1810.0d, actualAddSumProductResult.getAverage(), 0.0);
    assertEquals(1810.0d, actualAddSumProductResult.getMax(), 0.0);
    assertEquals(1810.0d, actualAddSumProductResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1810.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromFloatArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray5() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray6() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableFromFloatArray7() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {
          110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d, 110.0d
        },
        actualAddSumProductResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAADPathwise.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(Scalar.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromFloatArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableFromFloatArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromFloatArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

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
   * Test {@link RandomVariableFromFloatArray#addSumProduct(List, List)} with {@code List}, {@code
   * List}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(RandomVariableDifferentiableAAD.of(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableLazyEvaluation(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromFloatArray.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertNull(actualAddSumProductResult.getOperator());
    assertEquals(0.0d, actualAddSumProductResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, Scalar.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenRandomVariableReturnRandomVariableFromDoubleArray5() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromFloatArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndEleven() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndEleven2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndEleven3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with eleven and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnRealizationsIsArrayOfDoubleWithElevenAndEleven4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d, 11.0d},
        actualAddRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableFromFloatArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(
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
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        randomVariableFromFloatArray.addRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, Scalar.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then RandomVariable return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenRandomVariableReturnRandomVariableFromDoubleArray5() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromFloatArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNineAndNine() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNineAndNine2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNineAndNine3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(
            numerator, new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with nine and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnRealizationsIsArrayOfDoubleWithNineAndNine4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableLazyEvaluation numerator = new RandomVariableLazyEvaluation(10.0d, 10, 10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(
        new double[] {9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d, 9.0d},
        actualSubRatioResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAADPathwise numerator =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromFloatArray numerator = new RandomVariableFromFloatArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableFromFloatArray4() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromFloatArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_thenValuesReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableDifferentiableAAD numerator = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(
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
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromFloatArray.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio_whenScalarWithValueIsTen_thenReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        randomVariableFromFloatArray.subRatio(numerator, Scalar.of(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubRatioResult.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromFloatArray#RandomVariableFromFloatArray(double)} with
   *       value is {@link Double#NaN}.
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.isNaN()"})
  public void testIsNaN_givenRandomVariableFromFloatArrayWithValueIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableFromFloatArray(Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromFloatArray);
    assertEquals(1.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromFloatArray.isNaN()"})
  public void testIsNaN_thenReturnAverageIsZero() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = new RandomVariableFromFloatArray(10.0d).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFromFloatArray#toString()}.
   *
   * <p>Method under test: {@link RandomVariableFromFloatArray#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String RandomVariableFromFloatArray.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "RandomVariableFromFloatArray [time=-Infinity, realizations=10.0, isDeterministic()=true,"
            + " typePriority=1]",
        new RandomVariableFromFloatArray(10.0d).toString());
  }
}
