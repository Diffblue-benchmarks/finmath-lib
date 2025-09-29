package net.finmath.functions;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.function.DoubleUnaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AnalyticFormulasDiffblueTest {
  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(10.0d, 0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(57.08046995596508d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble7() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble8() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble9() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble10() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(0.5d, 1.0E-16d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble11() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(0.5d, 1.0E-16d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(4.999999999999999d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionValueWithDoubleDoubleDoubleDoubleDouble12() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(10.0d, 1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(88.61537019933421d, actualBlackScholesGeneralizedOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult.getMax(), 0.0);
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {100.0d},
        actualBlackScholesGeneralizedOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(
            forward, volatility, 0.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBlackScholesGeneralizedOptionValueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesGeneralizedOptionValueResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesGeneralizedOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN},
        actualBlackScholesGeneralizedOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesGeneralizedOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(0.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesGeneralizedOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
    // Arrange
    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(forward.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesGeneralizedOptionValue(
                forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(forward).div(10.0d);
    verify(forward).getTypePriority();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(forward.div(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesGeneralizedOptionValue(
                forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(forward).div(10.0d);
    verify(forward).getTypePriority();
    verify(randomVariableAAD2).log();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(forward.div(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesGeneralizedOptionValue(
                forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(forward).div(10.0d);
    verify(forward).getTypePriority();
    verify(randomVariableAAD2).log();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.log()).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.sub(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.mult(anyDouble())).thenReturn(randomVariableAAD4);
    when(forward.div(anyDouble())).thenReturn(randomVariableAAD3);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesGeneralizedOptionValueResult =
        AnalyticFormulas.blackScholesGeneralizedOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD2).add(isA(RandomVariable.class));
    verify(forward).div(10.0d);
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).log();
    verify(forward).mult(0.9992172988709987d);
    verify(randomVariableAAD4).sub(isA(RandomVariable.class));
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesGeneralizedOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionValueResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesGeneralizedOptionValueResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesGeneralizedOptionValueResult.size());
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult.getMax(), 0.0);
    assertEquals(100.0d, actualBlackScholesGeneralizedOptionValueResult.getMin(), 0.0);
    assertEquals(3, actualBlackScholesGeneralizedOptionValueResult.getTypePriority());
    assertTrue(actualBlackScholesGeneralizedOptionValueResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualBlackScholesGeneralizedOptionValueResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {100.0d},
        actualBlackScholesGeneralizedOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(forward.div(anyDouble())).thenReturn(randomVariableAAD5);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesGeneralizedOptionValue(
                forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(forward).div(10.0d);
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(forward).getTypePriority();
    verify(randomVariableAAD5).log();
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesGeneralizedOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBlackScholesGeneralizedOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable10() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(anyDouble())).thenReturn(mock(RandomVariableAAD.class));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.log()).thenReturn(randomVariableAAD3);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.div(anyDouble())).thenReturn(randomVariableAAD4);

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.mult(anyDouble())).thenThrow(new IllegalArgumentException());
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesGeneralizedOptionValue(
                forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD3).add(isA(RandomVariable.class));
    verify(forward).div(10.0d);
    verify(randomVariableAAD).div(3.1622776601683795d);
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD4).log();
    verify(volatility).mult(3.1622776601683795d);
    verify(volatility).squared();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            2.6881171418161355E44d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.6881171418161355E44d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(3.9894228542891554E-7d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean2() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean3() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            10.0d, 2.6881171418161355E44d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean4() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            10.0d, 10.0d, 2.6881171418161355E44d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean5() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 0.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean6() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, -1.0d, 10.0d, true);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean7() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d, true);

    // Assert
    assertEquals(3.9894228542891554E-7d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean_whenFalse() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, false);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean_whenNaN() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean_whenZero() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double,
   * boolean)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double},
   * {@code boolean}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double, boolean)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDoubleBoolean_whenZero2() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 0.0d, 10.0d, true);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturn05() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            10.0d, 2.6881171418161355E44d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturnTen() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturnTen2() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            10.0d, 10.0d, 2.6881171418161355E44d, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturnTen3() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_whenMinusOne() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_whenMinusOne2() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, -1.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_whenNaN() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_whenZero() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithDoubleDoubleDoubleDoubleDouble_whenZero2() {
    // Arrange and Act
    double actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(10.0d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(10.0d, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(10.0d, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualBlackScholesOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY),
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBlackScholesOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            new RandomVariableFromDoubleArray(0.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(randomVariableAAD);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d));
    verify(randomVariableAAD).div(10.0d);
    verify(randomVariableAAD).getTypePriority();
    verify(initialStockValue).mult(2.6881171418161356E43d);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(randomVariableAAD2).div(10.0d);
    verify(randomVariableAAD2).mult(1.0d);
    verify(initialStockValue).mult(2.6881171418161356E43d);
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionValueResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionValueResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionValueResult.getValues() instanceof Scalar);
    assertTrue(actualBlackScholesOptionValueResult.isNaN() instanceof Scalar);
    assertNull(actualBlackScholesOptionValueResult.getRealizations());
    assertNull(actualBlackScholesOptionValueResult.getOperator());
    assertNull(actualBlackScholesOptionValueResult.getRealizationsStream());
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesOptionValueResult.size());
    assertEquals(3, actualBlackScholesOptionValueResult.getTypePriority());
    assertEquals(3.720075976020836E-43d, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertTrue(actualBlackScholesOptionValueResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualBlackScholesOptionValueResult.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD3.div(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(randomVariableAAD3);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(10.0d);
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD2).log();
    verify(initialStockValue).mult(2.6881171418161356E43d);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD3.div(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(randomVariableAAD3);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(10.0d);
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD2).log();
    verify(initialStockValue).mult(2.6881171418161356E43d);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD6.div(anyDouble())).thenReturn(randomVariableAAD5);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(randomVariableAAD6);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d));
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD6).div(10.0d);
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(randomVariableAAD6).getTypePriority();
    verify(randomVariableAAD5).log();
    verify(initialStockValue).mult(2.6881171418161356E43d);
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionValueWithRandomVariableDoubleDoubleDoubleDouble9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(mock(RandomVariableAAD.class));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(randomVariableAAD);

    // Act
    AnalyticFormulas.blackScholesOptionValue(initialStockValue, 10.0d, 10.0d, -1.0d, 10.0d);

    // Assert
    verify(randomVariableAAD).mult(0.0d);
    verify(initialStockValue).mult(4.5399929762484854E-5d);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(10.0d, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(10.0d, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualBlackScholesOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble2() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(0.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble3() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBlackScholesOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(randomVariableAAD).div(10.0d);
    verify(randomVariableAAD).getTypePriority();
    verify(initialStockValue).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD3.div(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(10.0d);
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD2).log();
    verify(initialStockValue).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD3.div(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(10.0d);
    verify(randomVariableAAD3).getTypePriority();
    verify(randomVariableAAD2).log();
    verify(initialStockValue).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.log()).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.sub(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);
    when(randomVariableAAD5.div(anyDouble())).thenReturn(randomVariableAAD3);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionValueResult =
        AnalyticFormulas.blackScholesOptionValue(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD2).add(isA(RandomVariable.class));
    verify(randomVariableAAD5).div(10.0d);
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).log();
    verify(randomVariableAAD5).mult(0.9992172988709987d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
    verify(randomVariableAAD4).sub(isA(RandomVariable.class));
    assertTrue(
        actualBlackScholesOptionValueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionValueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionValueResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionValueResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionValueResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionValueResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesOptionValueResult.size());
    assertEquals(3, actualBlackScholesOptionValueResult.getTypePriority());
    assertEquals(3.720075976020836E-43d, actualBlackScholesOptionValueResult.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualBlackScholesOptionValueResult.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualBlackScholesOptionValueResult.getMin(), 0.0);
    assertTrue(actualBlackScholesOptionValueResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualBlackScholesOptionValueResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {3.720075976020836E-43d},
        actualBlackScholesOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD6.div(anyDouble())).thenReturn(randomVariableAAD5);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD6);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD6).div(10.0d);
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(randomVariableAAD6).getTypePriority();
    verify(randomVariableAAD5).log();
    verify(initialStockValue).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(anyDouble())).thenReturn(mock(RandomVariableAAD.class));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.log()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.div(anyDouble())).thenReturn(randomVariableAAD4);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);

    RandomVariableAAD riskFreeRate = mock(RandomVariableAAD.class);
    when(riskFreeRate.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.mult(anyDouble())).thenThrow(new IllegalArgumentException());
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionValue(
                initialStockValue, riskFreeRate, volatility, 10.0d, 10.0d));
    verify(randomVariableAAD3).add(isA(RandomVariable.class));
    verify(randomVariableAAD5).div(10.0d);
    verify(randomVariableAAD).div(3.1622776601683795d);
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD4).log();
    verify(riskFreeRate, atLeast(1)).mult(anyDouble());
    verify(volatility).mult(3.1622776601683795d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
    verify(volatility).squared();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionValue(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionValue(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionValueWithRandomVariableRandomVariableRandomVariableDoubleDouble10() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(mock(RandomVariableAAD.class));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD riskFreeRate = mock(RandomVariableAAD.class);
    when(riskFreeRate.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    AnalyticFormulas.blackScholesOptionValue(
        initialStockValue, riskFreeRate, mock(RandomVariableAAD.class), -1.0d, 10.0d);

    // Assert
    verify(riskFreeRate, atLeast(1)).mult(anyDouble());
    verify(randomVariableAAD).mult(0.0d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesATMOptionValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 57.0804699559651}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesATMOptionValue(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesATMOptionValue(double, double, double, double)"
  })
  public void testBlackScholesATMOptionValue_when05_thenReturn570804699559651() {
    // Arrange and Act
    double actualBlackScholesATMOptionValueResult =
        AnalyticFormulas.blackScholesATMOptionValue(0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(57.0804699559651d, actualBlackScholesATMOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesATMOptionValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code -88.61537019933422}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesATMOptionValue(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesATMOptionValue(double, double, double, double)"
  })
  public void testBlackScholesATMOptionValue_whenMinusOne_thenReturn8861537019933422() {
    // Arrange and Act
    double actualBlackScholesATMOptionValueResult =
        AnalyticFormulas.blackScholesATMOptionValue(-1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-88.61537019933422d, actualBlackScholesATMOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesATMOptionValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesATMOptionValue(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesATMOptionValue(double, double, double, double)"
  })
  public void testBlackScholesATMOptionValue_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesATMOptionValueResult =
        AnalyticFormulas.blackScholesATMOptionValue(10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesATMOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesATMOptionValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesATMOptionValue(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesATMOptionValue(double, double, double, double)"
  })
  public void testBlackScholesATMOptionValue_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesATMOptionValueResult =
        AnalyticFormulas.blackScholesATMOptionValue(Double.NaN, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesATMOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesATMOptionValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesATMOptionValue(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesATMOptionValue(double, double, double, double)"
  })
  public void testBlackScholesATMOptionValue_whenTen_thenReturnOneHundred() {
    // Arrange and Act
    double actualBlackScholesATMOptionValueResult =
        AnalyticFormulas.blackScholesATMOptionValue(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(100.0d, actualBlackScholesATMOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesATMOptionValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesATMOptionValue(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesATMOptionValue(double, double, double, double)"
  })
  public void testBlackScholesATMOptionValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesATMOptionValueResult =
        AnalyticFormulas.blackScholesATMOptionValue(0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesATMOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(0.5000000239365368d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_thenReturnOne() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when05() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when10e16() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(0.5d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when10e162() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(1.0E-16d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when052() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenMinusOne() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenMinusOne2() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(10.0d, 10.0d, 10.0d, -1.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenMinusOne3() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, -1.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenZero() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenZero2() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(10.0d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenZero3() {
    // Arrange and Act
    double actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {1.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableFromDoubleArray(0.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableLazyEvaluation(10.0d, 10, 10.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBlackScholesOptionDeltaResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(10.0d, actualBlackScholesOptionDeltaResult.getFiltrationTime(), 0.0);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualBlackScholesOptionDeltaResult.getRealizations(),
        0.0);
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult.getStandardError(), 0.0);
    assertEquals(0, actualBlackScholesOptionDeltaResult.getTypePriority());
    assertEquals(0.0d, actualBlackScholesOptionDeltaResult.getVariance(), 0.0);
    assertFalse(actualBlackScholesOptionDeltaResult.isDeterministic());
    assertTrue(actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualBlackScholesOptionDeltaResult.size());
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableLazyEvaluation);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5000000239365368d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.5000000239365368d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.5000000239365368d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.5000000239365368d},
        actualBlackScholesOptionDeltaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble_when05() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {1.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <ul>
   *   <li>When {@code -1.0E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionDeltaWithRandomVariableDoubleDoubleDoubleDouble_when10e10() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, -1.0E-10d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble2() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue, riskFreeRate, new RandomVariableFromDoubleArray(10.0d), 0.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble3() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            -1.0E-10d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble4() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(0.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD2).log();
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.6240851829770753d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.6240851829770753d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.6240851829770753d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.6240851829770753d},
        actualBlackScholesOptionDeltaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD2).log();
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.5d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.5d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.5d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD5);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(randomVariableAAD5).log();
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleDouble8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD5);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(randomVariableAAD5).log();
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            10.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(1.0d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            0.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            -1.0E-10d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable4() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(0.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            10.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            10.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(initialStockValue).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).log();
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.6240851829770753d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.6240851829770753d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.6240851829770753d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.6240851829770753d},
        actualBlackScholesOptionDeltaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.log()).thenReturn(randomVariableAAD);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            10.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(initialStockValue).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).log();
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualBlackScholesOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.5d, actualBlackScholesOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.5d, actualBlackScholesOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.5d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            10.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(initialStockValue).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(randomVariableAAD5).log();
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable, RandomVariable,
   * RandomVariable, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable},
   * {@code RandomVariable}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionDelta(RandomVariable,
   * RandomVariable, RandomVariable, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionDelta(RandomVariable, RandomVariable, RandomVariable, double, RandomVariable)"
  })
  public void
      testBlackScholesOptionDeltaWithRandomVariableRandomVariableRandomVariableDoubleRandomVariable8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.log()).thenReturn(randomVariableAAD4);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionDeltaResult =
        AnalyticFormulas.blackScholesOptionDelta(
            initialStockValue,
            riskFreeRate,
            volatility,
            10.0d,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD4).add(isA(RandomVariable.class));
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(3.1622776601683795d);
    verify(initialStockValue).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).div(isA(RandomVariable.class));
    verify(randomVariableAAD5).log();
    assertTrue(actualBlackScholesOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBlackScholesOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionGamma(double, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(8.470388368212206E-82d, actualBlackScholesOptionGammaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionGamma(double, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithDoubleDoubleDoubleDoubleDouble_when10e10() {
    // Arrange and Act
    double actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(10.0d, 10.0d, 10.0d, 1.0E-10d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionGammaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionGamma(double, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithDoubleDoubleDoubleDoubleDouble_whenZero() {
    // Arrange and Act
    double actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(10.0d, 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionGammaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionGamma(double, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithDoubleDoubleDoubleDoubleDouble_whenZero2() {
    // Arrange and Act
    double actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(10.0d, 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionGammaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithRandomVariableDoubleDoubleDoubleDouble() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(8.470388368211725E-82d, actualBlackScholesOptionGammaResult.getAverage(), 0.0);
    assertEquals(8.470388368211725E-82d, actualBlackScholesOptionGammaResult.getMax(), 0.0);
    assertEquals(8.470388368211725E-82d, actualBlackScholesOptionGammaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {8.470388368211725E-82d},
        actualBlackScholesOptionGammaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithRandomVariableDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertTrue(actualBlackScholesOptionGammaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionGammaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithRandomVariableDoubleDoubleDoubleDouble3() {
    // Arrange
    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any()))
        .thenThrow(new IllegalArgumentException());
    when(initialStockValue.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionGamma(
                initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d));
    verify(initialStockValue).div(10.0d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithRandomVariableDoubleDoubleDoubleDouble4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(initialStockValue.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD).mult(7.926654595212022d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
    assertTrue(
        actualBlackScholesOptionGammaResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionGammaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionGammaResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionGammaResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesOptionGammaResult.size());
    assertEquals(1.682061865311688E-80d, actualBlackScholesOptionGammaResult.getAverage(), 0.0);
    assertEquals(1.682061865311688E-80d, actualBlackScholesOptionGammaResult.getMax(), 0.0);
    assertEquals(1.682061865311688E-80d, actualBlackScholesOptionGammaResult.getMin(), 0.0);
    assertEquals(3, actualBlackScholesOptionGammaResult.getTypePriority());
    assertTrue(actualBlackScholesOptionGammaResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualBlackScholesOptionGammaResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {1.682061865311688E-80d},
        actualBlackScholesOptionGammaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionGammaWithRandomVariableDoubleDoubleDoubleDouble_when10e10() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertTrue(actualBlackScholesOptionGammaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionGammaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionGammaWithRandomVariableRandomVariableRandomVariableDoubleDouble() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(8.470388368211725E-82d, actualBlackScholesOptionGammaResult.getAverage(), 0.0);
    assertEquals(8.470388368211725E-82d, actualBlackScholesOptionGammaResult.getMax(), 0.0);
    assertEquals(8.470388368211725E-82d, actualBlackScholesOptionGammaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {8.470388368211725E-82d},
        actualBlackScholesOptionGammaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionGammaWithRandomVariableRandomVariableRandomVariableDoubleDouble2() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            initialStockValue, riskFreeRate, new RandomVariableFromDoubleArray(10.0d), 0.0d, 0.0d);

    // Assert
    assertTrue(actualBlackScholesOptionGammaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionGammaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionGammaWithRandomVariableRandomVariableRandomVariableDoubleDouble3() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            0.0d,
            1.0E-10d);

    // Assert
    assertTrue(actualBlackScholesOptionGammaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionGammaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionGammaWithRandomVariableRandomVariableRandomVariableDoubleDouble4() {
    // Arrange
    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any()))
        .thenThrow(new IllegalArgumentException());
    when(initialStockValue.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionGamma(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(initialStockValue).div(10.0d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionGammaWithRandomVariableRandomVariableRandomVariableDoubleDouble5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(initialStockValue.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD).mult(7.926654595212022d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
    assertTrue(
        actualBlackScholesOptionGammaResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionGammaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionGammaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionGammaResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionGammaResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionGammaResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionGammaResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesOptionGammaResult.size());
    assertEquals(1.682061865311688E-80d, actualBlackScholesOptionGammaResult.getAverage(), 0.0);
    assertEquals(1.682061865311688E-80d, actualBlackScholesOptionGammaResult.getMax(), 0.0);
    assertEquals(1.682061865311688E-80d, actualBlackScholesOptionGammaResult.getMin(), 0.0);
    assertEquals(3, actualBlackScholesOptionGammaResult.getTypePriority());
    assertTrue(actualBlackScholesOptionGammaResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualBlackScholesOptionGammaResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {1.682061865311688E-80d},
        actualBlackScholesOptionGammaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionGamma(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionGamma(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionGammaWithRandomVariableRandomVariableRandomVariableDoubleDouble6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.squared()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.div(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD4);

    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.log()).thenReturn(randomVariableAAD5);

    RandomVariableAAD randomVariableAAD7 = mock(RandomVariableAAD.class);
    when(randomVariableAAD7.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD7);
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD6);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionGammaResult =
        AnalyticFormulas.blackScholesOptionGamma(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD5).add(isA(RandomVariable.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD3).div(3.1622776601683795d);
    verify(randomVariableAAD4).div(isA(RandomVariable.class));
    verify(randomVariableAAD6).log();
    verify(randomVariableAAD).mult(-0.5d);
    verify(randomVariableAAD7).mult(7.926654595212022d);
    verify(initialStockValue).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).squared();
    assertTrue(actualBlackScholesOptionGammaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(2202.646579480672d, actualBlackScholesOptionGammaResult.getAverage(), 0.0);
    assertEquals(2202.646579480672d, actualBlackScholesOptionGammaResult.getMax(), 0.0);
    assertEquals(2202.646579480672d, actualBlackScholesOptionGammaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2202.646579480672d},
        actualBlackScholesOptionGammaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(8.470388368212208E-78d, actualBlackScholesOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithDoubleDoubleDoubleDoubleDouble_when10e10() {
    // Arrange and Act
    double actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(10.0d, 10.0d, 10.0d, 1.0E-10d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithDoubleDoubleDoubleDoubleDouble_whenZero() {
    // Arrange and Act
    double actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(10.0d, 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(double, double, double, double, double)}
   * with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithDoubleDoubleDoubleDoubleDouble_whenZero2() {
    // Arrange and Act
    double actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(10.0d, 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithRandomVariableDoubleDoubleDoubleDouble() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(8.470388368211725E-78d, actualBlackScholesOptionVegaResult.getAverage(), 0.0);
    assertEquals(8.470388368211725E-78d, actualBlackScholesOptionVegaResult.getMax(), 0.0);
    assertEquals(8.470388368211725E-78d, actualBlackScholesOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {8.470388368211725E-78d},
        actualBlackScholesOptionVegaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithRandomVariableDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertTrue(actualBlackScholesOptionVegaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithRandomVariableDoubleDoubleDoubleDouble3() {
    // Arrange
    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(initialStockValue.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionVega(initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d));
    verify(initialStockValue).div(10.0d);
    verify(initialStockValue).getTypePriority();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithRandomVariableDoubleDoubleDoubleDouble4() {
    // Arrange
    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(initialStockValue.div(anyDouble())).thenReturn(Scalar.of(10.0d));

    // Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(initialStockValue, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(initialStockValue).div(10.0d);
    verify(initialStockValue).mult(2.1220324982089074E-80d);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionVegaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionVegaResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionVegaResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionVegaResult.getValues() instanceof Scalar);
    assertTrue(actualBlackScholesOptionVegaResult.isNaN() instanceof Scalar);
    assertNull(actualBlackScholesOptionVegaResult.getRealizations());
    assertNull(actualBlackScholesOptionVegaResult.getOperator());
    assertNull(actualBlackScholesOptionVegaResult.getRealizationsStream());
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesOptionVegaResult.size());
    assertEquals(100.0d, actualBlackScholesOptionVegaResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBlackScholesOptionVegaResult.getMax(), 0.0);
    assertEquals(100.0d, actualBlackScholesOptionVegaResult.getMin(), 0.0);
    assertEquals(3, actualBlackScholesOptionVegaResult.getTypePriority());
    assertTrue(actualBlackScholesOptionVegaResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualBlackScholesOptionVegaResult.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double, double, double,
   * double)} with {@code RandomVariable}, {@code double}, {@code double}, {@code double}, {@code
   * double}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, double, double, double, double)"
  })
  public void testBlackScholesOptionVegaWithRandomVariableDoubleDoubleDoubleDouble_when10e10() {
    // Arrange and Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertTrue(actualBlackScholesOptionVegaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionVegaWithRandomVariableRandomVariableRandomVariableDoubleDouble() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(8.470388368211725E-78d, actualBlackScholesOptionVegaResult.getAverage(), 0.0);
    assertEquals(8.470388368211725E-78d, actualBlackScholesOptionVegaResult.getMax(), 0.0);
    assertEquals(8.470388368211725E-78d, actualBlackScholesOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {8.470388368211725E-78d},
        actualBlackScholesOptionVegaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionVegaWithRandomVariableRandomVariableRandomVariableDoubleDouble2() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            initialStockValue, riskFreeRate, new RandomVariableFromDoubleArray(10.0d), 0.0d, 0.0d);

    // Assert
    assertTrue(actualBlackScholesOptionVegaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionVegaWithRandomVariableRandomVariableRandomVariableDoubleDouble3() {
    // Arrange
    RandomVariableFromDoubleArray initialStockValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            0.0d,
            1.0E-10d);

    // Assert
    assertTrue(actualBlackScholesOptionVegaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBlackScholesOptionVegaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBlackScholesOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionVegaWithRandomVariableRandomVariableRandomVariableDoubleDouble4() {
    // Arrange
    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(initialStockValue.div(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionVega(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(initialStockValue).div(10.0d);
    verify(initialStockValue).getTypePriority();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionVegaWithRandomVariableRandomVariableRandomVariableDoubleDouble5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.log()).thenReturn(randomVariableAAD2);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD3);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBlackScholesOptionVegaResult =
        AnalyticFormulas.blackScholesOptionVega(
            initialStockValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(10.0d),
            10.0d,
            10.0d);

    // Assert
    verify(randomVariableAAD2).add(isA(RandomVariable.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).log();
    verify(initialStockValue).mult(8.50036660252035E-4d);
    assertTrue(
        actualBlackScholesOptionVegaResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesOptionVegaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionVegaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionVegaResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBlackScholesOptionVegaResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBlackScholesOptionVegaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBlackScholesOptionVegaResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBlackScholesOptionVegaResult)
            .getGradient()
            .size());
    assertEquals(1, actualBlackScholesOptionVegaResult.size());
    assertEquals(100.0d, actualBlackScholesOptionVegaResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBlackScholesOptionVegaResult.getMax(), 0.0);
    assertEquals(100.0d, actualBlackScholesOptionVegaResult.getMin(), 0.0);
    assertEquals(3, actualBlackScholesOptionVegaResult.getTypePriority());
    assertTrue(actualBlackScholesOptionVegaResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualBlackScholesOptionVegaResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {100.0d}, actualBlackScholesOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable, RandomVariable,
   * RandomVariable, double, double)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * RandomVariable}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionVega(RandomVariable,
   * RandomVariable, RandomVariable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.blackScholesOptionVega(RandomVariable, RandomVariable, RandomVariable, double, double)"
  })
  public void
      testBlackScholesOptionVegaWithRandomVariableRandomVariableRandomVariableDoubleDouble6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.exp()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.squared()).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.div(anyDouble())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD4);

    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);

    RandomVariableAAD randomVariableAAD7 = mock(RandomVariableAAD.class);
    when(randomVariableAAD7.log()).thenReturn(randomVariableAAD6);

    RandomVariableAAD initialStockValue = mock(RandomVariableAAD.class);
    when(initialStockValue.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(initialStockValue.div(anyDouble())).thenReturn(randomVariableAAD7);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.blackScholesOptionVega(
                initialStockValue,
                riskFreeRate,
                new RandomVariableFromDoubleArray(10.0d),
                10.0d,
                10.0d));
    verify(randomVariableAAD6).add(isA(RandomVariable.class));
    verify(initialStockValue).div(10.0d);
    verify(randomVariableAAD4).div(3.1622776601683795d);
    verify(randomVariableAAD5).div(isA(RandomVariable.class));
    verify(randomVariableAAD).exp();
    verify(initialStockValue).getTypePriority();
    verify(randomVariableAAD7).log();
    verify(randomVariableAAD2).mult(-0.5d);
    verify(randomVariableAAD3).squared();
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code -1.8995661739172721E-78}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when05_thenReturn18995661739172721e78() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-1.8995661739172721E-78d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code -3.7200759760208363E-42}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when05_thenReturn37200759760208363e42() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.7200759760208363E-42d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when05_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(-0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when05_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 10.0d, 10.0d, -0.5d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when05_thenReturnZero2() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 10.0d, 10.0d, 10.0d, -0.5d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code -1.9947114520071585E9}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when10e16_thenReturn19947114520071585e9() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(-1.9947114520071585E9d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 6.283185307179586}.
   *   <li>Then return {@code -3.240804188886992E-26}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when6283185307179586_thenReturn3240804188886992e26() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 6.283185307179586d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.240804188886992E-26d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 6.283185307179586}.
   *   <li>Then return {@code -3.240804188886992E-26}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when6283185307179586_thenReturn3240804188886992e262() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(
            6.283185307179586d, 6.283185307179586d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.240804188886992E-26d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 6.283185307179586}.
   *   <li>Then return {@code -5.261809260938058E-78}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_when6283185307179586_thenReturn5261809260938058e78() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-5.261809260938058E-78d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code -4.225263753339319E-5}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_whenOne_thenReturn4225263753339319e5() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 1.0d, 2.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-4.225263753339319E-5d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code -6.339797016776508E-78}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_whenTen_thenReturn6339797016776508e78() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-6.339797016776508E-78d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code -3.7200759760208363E-42}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_whenTwo_thenReturn37200759760208363e42() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(10.0d, 10.0d, 2.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.7200759760208363E-42d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionTheta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionTheta(double, double, double, double, double)"
  })
  public void testBlackScholesOptionTheta_whenZero_thenReturn00() {
    // Arrange and Act
    double actualBlackScholesOptionThetaResult =
        AnalyticFormulas.blackScholesOptionTheta(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-0.0d, actualBlackScholesOptionThetaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 6.274916338115339E-79}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_when05_thenReturn6274916338115339e79() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(6.274916338115339E-79d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 3.7200759760208363E-42}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_when05_thenReturn37200759760208363e42() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.7200759760208363E-42d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-15}.
   *   <li>Then return {@code 3.7200759760208363E-42}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_when10e15_thenReturn37200759760208363e42() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(1.0E-15d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.7200759760208363E-42d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 4.999999840423083E-16}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_when10e16_thenReturn4999999840423083e16() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(4.999999840423083E-16d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 3.7200759760208363E-42}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_when10e16_thenReturn37200759760208363e42() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(1.0E-16d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.7200759760208363E-42d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 94.3076850996671}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenMinusOne_thenReturn943076850996671() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 0.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(94.3076850996671d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 0.33689734995427334}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenMinusOne_thenReturn033689734995427334() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 0.5d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.33689734995427334d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 2.1046028326706174E-78}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenTen_thenReturn21046028326706174e78() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.1046028326706174E-78d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionRho(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionRho(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesOptionRho_whenZero_thenReturnZero3() {
    // Arrange and Act
    double actualBlackScholesOptionRhoResult =
        AnalyticFormulas.blackScholesOptionRho(10.0d, 10.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 8.889515668709967}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_when05_thenReturn8889515668709967() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(8.889515668709967d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-15}.
   *   <li>Then return {@code 7.926654595212014E23}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_when10e15_thenReturn7926654595212014e23() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(1.0E-15d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(7.926654595212014E23d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.6}.
   *   <li>Then return {@code 0.8626074035757518}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_when16_thenReturn08626074035757518() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(1.6d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.8626074035757518d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code -38252.60904453625}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenMinusOne_thenReturn3825260904453625() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(10.0d, 10.0d, 1.6d, -1.0d, 1.0d);

    // Assert
    assertEquals(-38252.60904453625d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code -25.303768751723528}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenMinusOne_thenReturn25303768751723528() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(10.0d, 10.0d, 1.6d, -1.0d, 10.0d);

    // Assert
    assertEquals(-25.303768751723528d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenMinusOne_thenReturnZero2() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(10.0d, 10.0d, 10.0d, -1.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(
            Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.09498022386674701}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenOne_thenReturn009498022386674701() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(1.6d, 10.0d, 1.0d, 10.0d, 1.0d);

    // Assert
    assertEquals(0.09498022386674701d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 5.3829231976220155}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenOne_thenReturn53829231976220155() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.3829231976220155d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.07947521398129419}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBlackScholesOptionImpliedVolatility_whenTen_thenReturn007947521398129419() {
    // Arrange and Act
    double actualBlackScholesOptionImpliedVolatilityResult =
        AnalyticFormulas.blackScholesOptionImpliedVolatility(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.07947521398129419d, actualBlackScholesOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 3.720075976020836E-44}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_when05_thenReturn3720075976020836e44() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.720075976020836E-44d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 6.274916338114796E-81}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_when05_thenReturn6274916338114796e81() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(6.274916338114796E-81d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-15}.
   *   <li>Then return {@code 3.720075976020836E-44}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_when10e15_thenReturn3720075976020836e44() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(1.0E-15d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.720075976020836E-44d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 3.720075976020836E-44}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_when10e16_thenReturn3720075976020836e44() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(1.0E-16d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.720075976020836E-44d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 0.4999999840423083}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_when10e16_thenReturn04999999840423083() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(0.4999999840423083d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 0.9430768509966712}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_whenMinusOne_thenReturn09430768509966712() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(10.0d, 0.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.9430768509966712d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 2.104602832670405E-80}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_whenTen_thenReturn2104602832670405e80() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.104602832670405E-80d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_whenZero_thenReturnOne() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(1.0d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionValue(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionValueResult =
        AnalyticFormulas.blackScholesDigitalOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionDelta_when10e10_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionDeltaResult =
        AnalyticFormulas.blackScholesDigitalOptionDelta(10.0d, 10.0d, 10.0d, 1.0E-10d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 8.470388368211124E-82}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionDelta_whenTen_thenReturn8470388368211124e82() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionDeltaResult =
        AnalyticFormulas.blackScholesDigitalOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(8.470388368211124E-82d, actualBlackScholesDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionDelta_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionDeltaResult =
        AnalyticFormulas.blackScholesDigitalOptionDelta(10.0d, 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionDelta_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionDeltaResult =
        AnalyticFormulas.blackScholesDigitalOptionDelta(10.0d, 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionVega_when10e10_thenReturn00() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionVegaResult =
        AnalyticFormulas.blackScholesDigitalOptionVega(10.0d, 10.0d, 10.0d, 1.0E-10d, 1.0E-10d);

    // Assert
    assertEquals(-0.0d, actualBlackScholesDigitalOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code -5.082233020926674E-79}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionVega_whenTen_thenReturn5082233020926674e79() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionVegaResult =
        AnalyticFormulas.blackScholesDigitalOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-5.082233020926674E-79d, actualBlackScholesDigitalOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionVega_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionVegaResult =
        AnalyticFormulas.blackScholesDigitalOptionVega(10.0d, 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionVega_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionVegaResult =
        AnalyticFormulas.blackScholesDigitalOptionVega(10.0d, 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code -5.157900062542852E-27}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_thenReturn5157900062542852e27() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(
            10.0d, 6.283185307179586d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(-5.157900062542852E-27d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code -1.0421281939569044E-79}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_thenReturn10421281939569044e79() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(
            6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-1.0421281939569044E-79d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code -3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when05_thenReturn3720075976020836e43() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.720075976020836E-43d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code -3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when05_thenReturn3720075976020836e432() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 10.0d, 10.0d, 10.0d, -0.5d);

    // Assert
    assertEquals(-3.720075976020836E-43d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code -3.7307672579038775E-80}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when05_thenReturn37307672579038775e80() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.7307672579038775E-80d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when05_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(-0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when05_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 10.0d, 10.0d, -0.5d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code -3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when10e16_thenReturn3720075976020836e43() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(1.0E-16d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(-3.720075976020836E-43d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code -3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when10e16_thenReturn3720075976020836e432() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(
            1.0E-16d, 10.0d, 0.5d, 10.0d, 6.283185307179586d);

    // Assert
    assertEquals(-3.720075976020836E-43d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 3.9894223040143357E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_when10e16_thenReturn39894223040143357e10() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(3.9894223040143357E-10d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code -0.04219010159794768}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_whenMinusOne_thenReturn004219010159794768() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 0.5d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-0.04219010159794768d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code -1.2575639958494326E-79}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_whenTen_thenReturn12575639958494326e79() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-1.2575639958494326E-79d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code -9.792213295303071}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_whenZero_thenReturn9792213295303071() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(10.0d, 0.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-9.792213295303071d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesDigitalOptionRho(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesDigitalOptionRho(double, double, double, double, double)"
  })
  public void testBlackScholesDigitalOptionRho_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesDigitalOptionRhoResult =
        AnalyticFormulas.blackScholesDigitalOptionRho(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesDigitalOptionRhoResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 570.8046995596508}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_when05_thenReturn5708046995596508() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(10.0d, 0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(570.8046995596508d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return fifty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_when05_thenReturnFifty() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(50.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 49.999999999999986}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_when10e16_thenReturn49999999999999986() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(0.5d, 1.0E-16d, 10.0d, 1.0E-16d, 10.0d, 10.0d);

    // Assert
    assertEquals(49.999999999999986d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_when10e16_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(0.5d, 1.0E-16d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenMinusOne_thenReturnZero2() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(10.0d, 10.0d, -1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 886.153701993342}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenOne_thenReturn886153701993342() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(886.153701993342d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenTen_thenReturnOneThousand() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1000.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(10.0d, 0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletValue(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletValue(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletValue_whenZero_thenReturnZero3() {
    // Arrange and Act
    double actualBlackModelCapletValueResult =
        AnalyticFormulas.blackModelCapletValue(10.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.34685952162190903}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_thenReturn034685952162190903() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(
            1971.5909503065513d, 10.0d, 1.0E-15d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.34685952162190903d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code 0.5437024046297436}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_when05_thenReturn05437024046297436() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(1.0d, 10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.5437024046297436d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.6463191627210505}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_when05_thenReturn06463191627210505() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.6463191627210505d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-15}.
   *   <li>Then return {@code -4.907026418198245E73}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_when10e15_thenReturn4907026418198245e73() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(
            10.0d, 10.0d, 1.0E-15d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-4.907026418198245E73d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-15}.
   *   <li>Then return {@code 7.926654595212015E22}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_when10e15_thenReturn7926654595212015e22() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(
            1.0E-15d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(7.926654595212015E22d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.6}.
   *   <li>Then return {@code 0.44748532110338074}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_when16_thenReturn044748532110338074() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 1.6d);

    // Assert
    assertEquals(0.44748532110338074d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When five.
   *   <li>Then return {@code 0.14824681636494125}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_whenFive_thenReturn014824681636494125() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(5.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.14824681636494125d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(
            10.0d, 10.0d, -1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(
            Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.4692626129421456}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_whenOne_thenReturn04692626129421456() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.4692626129421456d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.007926862125953808}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelCapletImpliedVolatility(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelCapletImpliedVolatility(double, double, double, double, double, double)"
  })
  public void testBlackModelCapletImpliedVolatility_whenTen_thenReturn0007926862125953808() {
    // Arrange and Act
    double actualBlackModelCapletImpliedVolatilityResult =
        AnalyticFormulas.blackModelCapletImpliedVolatility(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.007926862125953808d, actualBlackModelCapletImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 21.459765022017457}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_when05_thenReturn21459765022017457() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(10.0d, 0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(21.459765022017457d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 2.8732396695723622E-55}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_when05_thenReturn28732396695723622e55() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.8732396695723622E-55d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 94.30768509966711}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_whenMinusOne_thenReturn9430768509966711() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(10.0d, -1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(94.30768509966711d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 1.298403519670024E-54}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_whenTen_thenReturn1298403519670024e54() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.298403519670024E-54d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_whenZero_thenReturnOneHundred() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(100.0d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_whenZero_thenReturnOneHundred2() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(0.0d, -1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(100.0d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelDigitalCapletValueResult =
        AnalyticFormulas.blackModelDigitalCapletValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelDigitalCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletDelta(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletDelta_when10e10_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelDigitalCapletDeltaResult =
        AnalyticFormulas.blackModelDigitalCapletDelta(
            10.0d, 10.0d, 10.0d, 10.0d, 1.0E-10d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackModelDigitalCapletDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 6.517781960574035E-56}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletDelta(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletDelta_whenTen_thenReturn6517781960574035e56() {
    // Arrange and Act
    double actualBlackModelDigitalCapletDeltaResult =
        AnalyticFormulas.blackModelDigitalCapletDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(6.517781960574035E-56d, actualBlackModelDigitalCapletDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletDelta(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletDelta_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelDigitalCapletDeltaResult =
        AnalyticFormulas.blackModelDigitalCapletDelta(10.0d, 10.0d, 10.0d, 10.0d, 0.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelDigitalCapletDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDigitalCapletDelta(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDigitalCapletDelta(double, double, double, double, double, double)"
  })
  public void testBlackModelDigitalCapletDelta_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackModelDigitalCapletDeltaResult =
        AnalyticFormulas.blackModelDigitalCapletDelta(10.0d, 10.0d, 10.0d, 10.0d, 0.0d, 1.0E-10d);

    // Assert
    assertEquals(0.0d, actualBlackModelDigitalCapletDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 21.459765022017457}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_when05_thenReturn21459765022017457() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(10.0d, 0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(21.459765022017457d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 2.8732396695723622E-55}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_when05_thenReturn28732396695723622e55() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.8732396695723622E-55d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 94.30768509966711}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_whenMinusOne_thenReturn9430768509966711() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(10.0d, -1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(94.30768509966711d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 1.298403519670024E-54}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_whenTen_thenReturn1298403519670024e54() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.298403519670024E-54d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_whenZero_thenReturnOneHundred() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(100.0d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_whenZero_thenReturnOneHundred2() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(0.0d, -1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(100.0d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelDgitialCapletValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelDgitialCapletValue(double, double, double, double, double, double)"
  })
  public void testBlackModelDgitialCapletValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelDgitialCapletValueResult =
        AnalyticFormulas.blackModelDgitialCapletValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelDgitialCapletValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 57.08046995596508}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_when05_thenReturn5708046995596508() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(10.0d, 0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(57.08046995596508d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_when05_thenReturnFive() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 4.999999999999999}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_when10e16_thenReturn4999999999999999() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(0.5d, 1.0E-16d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(4.999999999999999d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_when10e16_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(0.5d, 1.0E-16d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenMinusOne_thenReturnZero2() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 88.61537019933421}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenOne_thenReturn8861537019933421() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(10.0d, 1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(88.61537019933421d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenTen_thenReturnOneHundred() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(100.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackModelSwaptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackModelSwaptionValue(double, double, double, double, double)"
  })
  public void testBlackModelSwaptionValue_whenZero_thenReturnZero3() {
    // Arrange and Act
    double actualBlackModelSwaptionValueResult =
        AnalyticFormulas.blackModelSwaptionValue(10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackModelSwaptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 5.708046995596508}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_when05_thenReturn5708046995596508() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(10.0d, 10.0d, 0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.708046995596508d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code 1.0E-16}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_when10e16_thenReturn10e16() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(
            1.0E-16d, 10.0d, -1.0d, 17.349351572897472d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0E-16d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 17.349351572897472}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_when17349351572897472_thenReturnTen() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(
            10.0d, 10.0d, -1.0d, 17.349351572897472d, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenMinusOne_thenReturnTen() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(10.0d, 10.0d, -1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenMinusOne_thenReturnZero2() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, -1.0d);

    // Assert
    assertEquals(0.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenOne_thenReturnNaN() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenOne_thenReturnZero() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 1.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenTen_thenReturnNaN() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.4674993281404387}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenTwo_thenReturn04674993281404387() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(2.0d, 10.0d, 0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.4674993281404387d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenTwo_thenReturnNaN() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#margrabeExchangeOptionValue(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.margrabeExchangeOptionValue(double, double, double, double, double, double)"
  })
  public void testMargrabeExchangeOptionValue_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualMargrabeExchangeOptionValueResult =
        AnalyticFormulas.margrabeExchangeOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualMargrabeExchangeOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(126.15662610100802d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(3.141592653589793d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(94.82007552296452d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(2.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(90.17224441855609d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(82.41241314072116d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(10.0d, 10.0d, 10.0d, 3.141592653589793d, 10.0d);

    // Assert
    assertEquals(163.40414898706658d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(3.141592653589793d, -1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-68.75288150981858d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble7() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(0.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.5127270830006285d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble8() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(10.0d, 0.0d, 10.0d, 3.141592653589793d, 10.0d);

    // Assert
    assertEquals(68.58407346410206d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble9() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(-1.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.8232687714055817d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble_thenReturnZero2() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(3.141592653589793d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(126.15662610100802d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(126.15662610100802d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(126.15662610100802d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {126.15662610100802d},
        actualBachelierOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 0.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionValueResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionValueResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(0.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(82.41241314072116d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(82.41241314072116d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(82.41241314072116d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {82.41241314072116d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionValueResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionValueResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
    // Arrange
    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(182.41241314072116d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(182.41241314072116d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(182.41241314072116d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {182.41241314072116d},
        actualBachelierOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.bachelierOptionValue(
                forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariableAAD).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).mult(1.0d);
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(100.0d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(100.0d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {100.0d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD2.isDeterministic()).thenReturn(true);
    when(randomVariableAAD2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).doubleValue();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2).isDeterministic();
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualBachelierOptionValueResult.getFiltrationTime(), 0.0);
    assertEquals(4162.27766016838d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(4162.27766016838d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(4162.27766016838d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {4162.27766016838d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable10() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierOptionValueResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionValueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierOptionValueResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBachelierOptionValueResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierOptionValueResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult).getGradient().size());
    assertArrayEquals(
        new double[] {4162.27766016838d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable11() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        AnalyticFormulas.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualBachelierOptionValueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierOptionValueResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBachelierOptionValueResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierOptionValueResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierOptionValueResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult).getGradient().size());
    assertArrayEquals(
        new double[] {4162.27766016838d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionValue(RandomVariable, RandomVariable, double,
   * double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double},
   * {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticFormulas.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable12() {
    // Arrange
    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.mult(anyDouble())).thenThrow(new IllegalArgumentException());
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.bachelierOptionValue(
                forward, volatility, -1.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(forward).mult(0.0d);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 3.089859425347398}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_thenReturn3089859425347398() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(
            1.3068844036618974d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.089859425347398d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.22475990624610612}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_thenReturn022475990624610612() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(
            17.079468445347132d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.22475990624610612d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 2.5221422550165165}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_thenReturn25221422550165165() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(
            4.0610539079335215d, 10.0d, 10.0d, 10.0d, 10.558931301102602d);

    // Assert
    assertEquals(2.5221422550165165d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 3.157150460928908}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenOne_thenReturn3157150460928908() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.157150460928908d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.7926654595212022}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenTen_thenReturn07926654595212022() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.7926654595212022d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenZero_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenZero_thenReturnPositive_infinity2() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(
            17.079468445347132d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenZero_thenReturnPositive_infinity3() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        AnalyticFormulas.bachelierOptionImpliedVolatility(
            17.079468445347132d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.6180702136540838}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_when05_thenReturn06180702136540838() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(10.0d, 10.0d, 10.0d, 0.5d, 10.0d);

    // Assert
    assertEquals(0.6180702136540838d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 9.372342252032749E-10}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_when05_thenReturn9372342252032749e10() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(0.5d, 0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(9.372342252032749E-10d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.38192978634591623}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_when05_thenReturn038192978634591623() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.38192978634591623d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 0.0598974652129593}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenMinusOne_thenReturn00598974652129593() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(-1.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0598974652129593d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 0.36397621480534914}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenMinusOne_thenReturn036397621480534914() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.36397621480534914d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenTen_thenReturn05() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code 0.07864960352514244}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenZero_thenReturn007864960352514244() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(0.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.07864960352514244d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code 0.37591481702292473}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenZero_thenReturn037591481702292473() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.37591481702292473d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenZero_thenReturnOne() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(10.0d, 0.0d, 10.0d, 0.5d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierOptionDelta(double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDelta_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        AnalyticFormulas.bachelierOptionDelta(0.5d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_when10e10_thenReturnNaN() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(0.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 1.0E-10d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code -1.0E-10}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_when10e10_thenReturnNaN2() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(
            0.0d, 0.0d, 10.0d, -1.0E-10d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code -1.0E-10}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_when10e10_thenReturnZero() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(
            -1.0E-10d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 1982291.9215326044}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenMinusOne_thenReturn19822919215326044() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(10.0d, -1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1982291.9215326044d, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 1982303.8232287106}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenOne_thenReturn19823038232287106() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1982303.8232287106d, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenPositive_infinity_thenReturnNaN() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenTen_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenZero_thenReturnNaN() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(0.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenZero_thenReturnNaN2() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSOptionValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSOptionValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSOptionValue_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualHuntKennedyCMSOptionValueResult =
        AnalyticFormulas.huntKennedyCMSOptionValue(10.0d, 10.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualHuntKennedyCMSOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_when10e10_thenReturnNaN() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(0.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 1.0E-10d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code -1.0E-10}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_when10e10_thenReturnNaN2() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(0.0d, 0.0d, 10.0d, -1.0E-10d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code -1.0E-10}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_when10e10_thenReturnZero() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(-1.0E-10d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(0.0d, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 1982391.9215326044}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenMinusOne_thenReturn19823919215326044() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(10.0d, -1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1982391.9215326044d, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 1982403.8232287106}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenOne_thenReturn19824038232287106() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1982403.8232287106d, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenPositive_infinity_thenReturnNaN() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenTen_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenZero_thenReturnNaN() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(0.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 0.0d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenZero_thenReturnNaN2() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSFloorValue(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSFloorValue(double, double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSFloorValue_whenZero_thenReturnOneHundred() {
    // Arrange and Act
    double actualHuntKennedyCMSFloorValueResult =
        AnalyticFormulas.huntKennedyCMSFloorValue(10.0d, 10.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(100.0d, actualHuntKennedyCMSFloorValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSAdjustedRate(double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSAdjustedRate_when05_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualHuntKennedyCMSAdjustedRateResult =
        AnalyticFormulas.huntKennedyCMSAdjustedRate(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualHuntKennedyCMSAdjustedRateResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSAdjustedRate(double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSAdjustedRate_whenMinusOne_thenReturnNegative_infinity() {
    // Arrange and Act
    double actualHuntKennedyCMSAdjustedRateResult =
        AnalyticFormulas.huntKennedyCMSAdjustedRate(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NEGATIVE_INFINITY, actualHuntKennedyCMSAdjustedRateResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSAdjustedRate(double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSAdjustedRate_whenOne_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualHuntKennedyCMSAdjustedRateResult =
        AnalyticFormulas.huntKennedyCMSAdjustedRate(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualHuntKennedyCMSAdjustedRateResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#huntKennedyCMSAdjustedRate(double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.huntKennedyCMSAdjustedRate(double, double, double, double, double, double)"
  })
  public void testHuntKennedyCMSAdjustedRate_whenTen_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualHuntKennedyCMSAdjustedRateResult =
        AnalyticFormulas.huntKennedyCMSAdjustedRate(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualHuntKennedyCMSAdjustedRateResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                0.0d, 10.0d, 1.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity3() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                0.0d, 10.0d, 1.0d, 0.0d, 10.0d, 1.0E-10d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity4() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                0.0d, 10.0d, 1.0000000000000002d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity5() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                1.0E-10d, 10.0d, 1.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity6() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                1.0E-10d, 10.0d, 1.0d, 1.0E-10d, 10.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity7() {
    // Arrange and Act
    double actualSabrHaganLognormalBlackVolatilityApproximationResult =
        AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
            1.0E-10d, 10.0d, 1.0d, 1.0E-10d, 10.0d, 1.0E-10d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSabrHaganLognormalBlackVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity8() {
    // Arrange and Act
    double actualSabrHaganLognormalBlackVolatilityApproximationResult =
        AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
            1.0E-10d, 10.0d, -1.0d, 1.0E-10d, 10.0d, 1.0E-10d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        115.31548152040844d, actualSabrHaganLognormalBlackVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity9() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                1.0E-10d, 10.0d, -1.0000000000000002d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho},
   * {@code nu}, {@code displacement}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuDisplacementUnderlyingStrikeMaturity10() {
    // Arrange and Act
    double actualSabrHaganLognormalBlackVolatilityApproximationResult =
        AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
            10.0d, 10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        4.529848320006554E39d, actualSabrHaganLognormalBlackVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                0.0d, 10.0d, 1.0d, 0.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity3() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                0.0d, 10.0d, 1.0d, 0.0d, 1.0E-10d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity4() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                0.0d, 10.0d, 1.0000000000000002d, 0.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity5() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                1.0E-10d, 10.0d, 1.0d, 0.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity6() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                1.0E-10d, 10.0d, 1.0d, 1.0E-10d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity7() {
    // Arrange and Act
    double actualSabrHaganLognormalBlackVolatilityApproximationResult =
        AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
            1.0E-10d, 10.0d, 1.0d, 1.0E-10d, 1.0E-10d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSabrHaganLognormalBlackVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity8() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
                1.0E-10d, 10.0d, -1.0000000000000002d, 0.0d, 0.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double,
   * double, double, double, double, double)} with {@code alpha}, {@code beta}, {@code rho}, {@code
   * nu}, {@code underlying}, {@code strike}, {@code maturity}.
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(double, double, double, double, double, double, double)"
  })
  public void
      testSabrHaganLognormalBlackVolatilityApproximationWithAlphaBetaRhoNuUnderlyingStrikeMaturity9() {
    // Arrange and Act
    double actualSabrHaganLognormalBlackVolatilityApproximationResult =
        AnalyticFormulas.sabrHaganLognormalBlackVolatilityApproximation(
            10.0d, 10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.3750000025E31d, actualSabrHaganLognormalBlackVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 5.38397222445014E27}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_thenReturn538397222445014e27() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 2.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        5.38397222445014E27d, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 7.158278827190953E38}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_thenReturn7158278827190953e38() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        7.158278827190953E38d, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 8.947848533464405E40}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_thenReturn8947848533464405e40() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        8.947848533464405E40d, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 2.4652203612946928E27}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_thenReturn24652203612946928e27() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            10.0d, 10.0d, 0.9999999999999999d, 10.0d, 10.0d, 2.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        2.4652203612946928E27d, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            10.0d, 0.9999999999999999d, 10.0d, 10.0d, 10.0d, 2.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        Double.POSITIVE_INFINITY, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_thenReturnZero() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            10.0d, 0.9999999999999999d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrBerestyckiNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrBerestyckiNormalVolatilityApproximation_whenOne_thenReturnZero() {
    // Arrange and Act
    double actualSabrBerestyckiNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrBerestyckiNormalVolatilityApproximation(
            10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualSabrBerestyckiNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-8}.
   *   <li>Then return {@code 9.078793396906664E13}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityApproximation_when10e8_thenReturn9078793396906664e13() {
    // Arrange and Act
    double actualSabrNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityApproximation(
            1.0E-8d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(9.078793396906664E13d, actualSabrNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 8.947848534644052E37}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityApproximation_whenOne_thenReturn8947848534644052e37() {
    // Arrange and Act
    double actualSabrNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityApproximation(
            1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(8.947848534644052E37d, actualSabrNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 8.947848533464405E40}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityApproximation_whenTen_thenReturn8947848533464405e40() {
    // Arrange and Act
    double actualSabrNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(8.947848533464405E40d, actualSabrNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 5.907463330574636E37}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityApproximation_whenTwo_thenReturn5907463330574636e37() {
    // Arrange and Act
    double actualSabrNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 2.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.907463330574636E37d, actualSabrNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 7.158278827190953E38}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilityApproximation(double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityApproximation(double, double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityApproximation_whenTwo_thenReturn7158278827190953e38() {
    // Arrange and Act
    double actualSabrNormalVolatilityApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityApproximation(
            2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(7.158278827190953E38d, actualSabrNormalVolatilityApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return {@code -7.865566354327813E-34}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrAlphaApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrAlphaApproximation_when10e16_thenReturn7865566354327813e34() {
    // Arrange and Act
    double actualSabrAlphaApproximationResult =
        AnalyticFormulas.sabrAlphaApproximation(1.0E-16d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-7.865566354327813E-34d, actualSabrAlphaApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#MAX_VALUE}.
   *   <li>Then return {@code 1.7555597020139802E295}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrAlphaApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrAlphaApproximation_whenMax_value_thenReturn17555597020139802e295() {
    // Arrange and Act
    double actualSabrAlphaApproximationResult =
        AnalyticFormulas.sabrAlphaApproximation(
            Double.MAX_VALUE, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.7555597020139802E295d, actualSabrAlphaApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code -7.865559976121654E-18}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrAlphaApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrAlphaApproximation_whenOne_thenReturn7865559976121654e18() {
    // Arrange and Act
    double actualSabrAlphaApproximationResult =
        AnalyticFormulas.sabrAlphaApproximation(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-7.865559976121654E-18d, actualSabrAlphaApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code -7.865502573142955E-17}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrAlphaApproximation(double, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrAlphaApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrAlphaApproximation_whenTen_thenReturn7865502573142955e17() {
    // Arrange and Act
    double actualSabrAlphaApproximationResult =
        AnalyticFormulas.sabrAlphaApproximation(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-7.865502573142955E-17d, actualSabrAlphaApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -1.34151948808873E-12}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilitySkewApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilitySkewApproximation_thenReturn134151948808873e12() {
    // Arrange and Act
    double actualSabrNormalVolatilitySkewApproximationResult =
        AnalyticFormulas.sabrNormalVolatilitySkewApproximation(
            10.0d, 0.9999999999999999d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-1.34151948808873E-12d, actualSabrNormalVolatilitySkewApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilitySkewApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilitySkewApproximation_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualSabrNormalVolatilitySkewApproximationResult =
        AnalyticFormulas.sabrNormalVolatilitySkewApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSabrNormalVolatilitySkewApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilitySkewApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilitySkewApproximation_whenOne_thenReturnZero() {
    // Arrange and Act
    double actualSabrNormalVolatilitySkewApproximationResult =
        AnalyticFormulas.sabrNormalVolatilitySkewApproximation(
            10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualSabrNormalVolatilitySkewApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 6.263493973399962E40}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilitySkewApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilitySkewApproximation_whenTen_thenReturn6263493973399962e40() {
    // Arrange and Act
    double actualSabrNormalVolatilitySkewApproximationResult =
        AnalyticFormulas.sabrNormalVolatilitySkewApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(6.263493973399962E40d, actualSabrNormalVolatilitySkewApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 5.010795178933179E38}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#sabrNormalVolatilitySkewApproximation(double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilitySkewApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilitySkewApproximation_whenTwo_thenReturn5010795178933179e38() {
    // Arrange and Act
    double actualSabrNormalVolatilitySkewApproximationResult =
        AnalyticFormulas.sabrNormalVolatilitySkewApproximation(
            2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.010795178933179E38d, actualSabrNormalVolatilitySkewApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -7.042977312465837E-13}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityCurvatureApproximation_thenReturn7042977312465837e13() {
    // Arrange and Act
    double actualSabrNormalVolatilityCurvatureApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(
            10.0d, 0.9999999999999999d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        -7.042977312465837E-13d, actualSabrNormalVolatilityCurvatureApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 3.2182428559380022E38}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityCurvatureApproximation_thenReturn32182428559380022e38() {
    // Arrange and Act
    double actualSabrNormalVolatilityCurvatureApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(
            2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        3.2182428559380022E38d, actualSabrNormalVolatilityCurvatureApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 4.0228035698067226E40}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityCurvatureApproximation_thenReturn40228035698067226e40() {
    // Arrange and Act
    double actualSabrNormalVolatilityCurvatureApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        4.0228035698067226E40d, actualSabrNormalVolatilityCurvatureApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityCurvatureApproximation_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualSabrNormalVolatilityCurvatureApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(
            10.0d, 10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSabrNormalVolatilityCurvatureApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double,
   * double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#sabrNormalVolatilityCurvatureApproximation(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(double, double, double, double, double, double, double)"
  })
  public void testSabrNormalVolatilityCurvatureApproximation_whenOne_thenReturnZero() {
    // Arrange and Act
    double actualSabrNormalVolatilityCurvatureApproximationResult =
        AnalyticFormulas.sabrNormalVolatilityCurvatureApproximation(
            10.0d, 1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualSabrNormalVolatilityCurvatureApproximationResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -14.04846862793934}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn1404846862793934() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(10.0d, 10.0d, 10.0d, -1.0d);

    // Assert
    assertEquals(-14.04846862793934d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 7.133989135690819}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn7133989135690819() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(-1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(7.133989135690819d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 12.907118563984394}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn12907118563984394() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(
            6.283185307179586d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.907118563984394d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 14.267978271381638}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn14267978271381638() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(8.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(14.267978271381638d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -14.936482656248543}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn14936482656248543() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(10.0d, 10.0d, 8.0d, -1.0d);

    // Assert
    assertEquals(
        -14.936482656248543d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 15.853309190424042}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn15853309190424042() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(15.853309190424042d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 200.00000000000014}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_thenReturn20000000000000014() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(10.0d, 10.0d, 1.0E-16d, 10.0d);

    // Assert
    assertEquals(200.00000000000014d, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticFormulas#volatilityConversionLognormalATMtoNormalATM(double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(double, double, double, double)"
  })
  public void testVolatilityConversionLognormalATMtoNormalATM_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualVolatilityConversionLognormalATMtoNormalATMResult =
        AnalyticFormulas.volatilityConversionLognormalATMtoNormalATM(10.0d, 10.0d, -1.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualVolatilityConversionLognormalATMtoNormalATMResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.0956320095964875}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_thenReturn00956320095964875() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(1.0d, -1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0956320095964875d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 18.294314355100177}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_thenReturn18294314355100177() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(
            23.162009047647032d, 10.0d, 10.0d, 10.0d, 1.0d);

    // Assert
    assertEquals(18.294314355100177d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.10094489901851461}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_when05_thenReturn010094489901851461() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(0.5d, -1.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.10094489901851461d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 11.704722663639185}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_when05_thenReturn11704722663639185() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(11.704722663639185d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(10.0d, 10.0d, 10.0d, 10.0d, -1.0d);

    // Assert
    assertEquals(0.0d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(
            Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 11.948966519310154}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenOne_thenReturn11948966519310154() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(11.948966519310154d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 14.048468627939341}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenOne_thenReturn14048468627939341() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(10.0d, 10.0d, 10.0d, 10.0d, 1.0d);

    // Assert
    assertEquals(14.048468627939341d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 15.853309190424042}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenTen_thenReturn15853309190424042() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(15.853309190424042d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When twenty.
   *   <li>Then return {@code 19.561588718177312}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenTwenty_thenReturn19561588718177312() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(20.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(19.561588718177312d, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenZero_thenReturnNaN() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#volatilityConversionLognormalToNormal(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.volatilityConversionLognormalToNormal(double, double, double, double, double)"
  })
  public void testVolatilityConversionLognormalToNormal_whenZero_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualVolatilityConversionLognormalToNormalResult =
        AnalyticFormulas.volatilityConversionLognormalToNormal(1.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualVolatilityConversionLognormalToNormalResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#price(Date, Date, double, double, double, int)} with {@code
   * settlementDate}, {@code maturityDate}, {@code coupon}, {@code yield}, {@code redemption},
   * {@code frequency}.
   *
   * <p>Method under test: {@link AnalyticFormulas#price(Date, Date, double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AnalyticFormulas.price(Date, Date, double, double, double, int)"})
  public void testPriceWithSettlementDateMaturityDateCouponYieldRedemptionFrequency() {
    // Arrange
    Date settlementDate =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    double actualPriceResult =
        AnalyticFormulas.price(
            settlementDate,
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            10.0d,
            10.0d,
            10.0d,
            1);

    // Assert
    assertEquals(0.0d, actualPriceResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#price(Date, Date, double, double, double, int)} with {@code
   * settlementDate}, {@code maturityDate}, {@code coupon}, {@code yield}, {@code redemption},
   * {@code frequency}.
   *
   * <p>Method under test: {@link AnalyticFormulas#price(Date, Date, double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AnalyticFormulas.price(Date, Date, double, double, double, int)"})
  public void testPriceWithSettlementDateMaturityDateCouponYieldRedemptionFrequency2() {
    // Arrange
    LocalDate ofYearDayResult = LocalDate.ofYearDay(2, 2);
    Date settlementDate =
        Date.from(ofYearDayResult.atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    double actualPriceResult =
        AnalyticFormulas.price(
            settlementDate,
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            10.0d,
            10.0d,
            10.0d,
            1);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualPriceResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#price(double, double, double, double, int)} with {@code
   * timeToMaturity}, {@code coupon}, {@code yield}, {@code redemption}, {@code frequency}.
   *
   * <p>Method under test: {@link AnalyticFormulas#price(double, double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AnalyticFormulas.price(double, double, double, double, int)"})
  public void testPriceWithTimeToMaturityCouponYieldRedemptionFrequency() {
    // Arrange and Act
    double actualPriceResult = AnalyticFormulas.price(10.0d, 10.0d, 10.0d, 10.0d, 3);

    // Assert
    assertEquals(1.5576399131754148d, actualPriceResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#price(double, double, double, double, int)} with {@code
   * timeToMaturity}, {@code coupon}, {@code yield}, {@code redemption}, {@code frequency}.
   *
   * <p>Method under test: {@link AnalyticFormulas#price(double, double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AnalyticFormulas.price(double, double, double, double, int)"})
  public void testPriceWithTimeToMaturityCouponYieldRedemptionFrequency2() {
    // Arrange and Act
    double actualPriceResult = AnalyticFormulas.price(1.0E-10d, 10.0d, 10.0d, 10.0d, 3);

    // Assert
    assertEquals(4.191240891961384d, actualPriceResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#price(double, double, double, double, int)} with {@code
   * timeToMaturity}, {@code coupon}, {@code yield}, {@code redemption}, {@code frequency}.
   *
   * <p>Method under test: {@link AnalyticFormulas#price(double, double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AnalyticFormulas.price(double, double, double, double, int)"})
  public void testPriceWithTimeToMaturityCouponYieldRedemptionFrequency3() {
    // Arrange and Act
    double actualPriceResult = AnalyticFormulas.price(1.0d, 10.0d, 10.0d, 10.0d, 3);

    // Assert
    assertEquals(1.5900055146456067d, actualPriceResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#price(double, double, double, double, int)} with {@code
   * timeToMaturity}, {@code coupon}, {@code yield}, {@code redemption}, {@code frequency}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#price(double, double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AnalyticFormulas.price(double, double, double, double, int)"})
  public void testPriceWithTimeToMaturityCouponYieldRedemptionFrequency_thenReturnZero() {
    // Arrange and Act
    double actualPriceResult = AnalyticFormulas.price(0.0d, 10.0d, 10.0d, 10.0d, 3);

    // Assert
    assertEquals(0.0d, actualPriceResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 12.528822005427315}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_thenReturn12528822005427315() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(
            6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.528822005427315d, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 6.283185307179586}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_when6283185307179586_thenReturnZero() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(
            6.283185307179586d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 11.875044606926359}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_whenMinusOne_thenReturn11875044606926359() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(11.875044606926359d, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(10.0d, 10.0d, -1.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_whenNaN_thenReturnNaN() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 12.615662610100802}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_whenTen_thenReturn12615662610100802() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.615662610100802d, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code 12.000389484301364}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#bachelierGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.bachelierGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBachelierGeneralizedOptionVega_whenZero_thenReturn12000389484301364() {
    // Arrange and Act
    double actualBachelierGeneralizedOptionVegaResult =
        AnalyticFormulas.bachelierGeneralizedOptionVega(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.000389484301364d, actualBachelierGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 1.4508952580617145E-53}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionVega_when05_thenReturn14508952580617145e53() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionVegaResult =
        AnalyticFormulas.blackScholesGeneralizedOptionVega(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.4508952580617145E-53d, actualBlackScholesGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionVega_whenMinusOne_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionVegaResult =
        AnalyticFormulas.blackScholesGeneralizedOptionVega(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 6.517781960574815E-53}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionVega_whenTen_thenReturn6517781960574815e53() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionVegaResult =
        AnalyticFormulas.blackScholesGeneralizedOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(6.517781960574815E-53d, actualBlackScholesGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionVega_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionVegaResult =
        AnalyticFormulas.blackScholesGeneralizedOptionVega(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionVega_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionVegaResult =
        AnalyticFormulas.blackScholesGeneralizedOptionVega(10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#blackScholesGeneralizedOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.blackScholesGeneralizedOptionVega(double, double, double, double, double)"
  })
  public void testBlackScholesGeneralizedOptionVega_whenZero_thenReturnZero3() {
    // Arrange and Act
    double actualBlackScholesGeneralizedOptionVegaResult =
        AnalyticFormulas.blackScholesGeneralizedOptionVega(10.0d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBlackScholesGeneralizedOptionVegaResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code 3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_thenReturn3720075976020836e43() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 10.0d, 10.0d, -0.11111111111111116d, 10.0d, 10.0d, false);

    // Assert
    assertEquals(3.720075976020836E-43d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code 0.4882691146299081}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_thenReturn04882691146299081() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 1.0E-16d, 0.5d, -0.11111111111111116d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(0.4882691146299081d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-15}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_when10e15_thenReturnTen() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 10.0d, 10.0d, 1.0E-15d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code -0.11111111111111116}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_when011111111111111116_thenReturnTen() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 10.0d, 10.0d, -0.11111111111111116d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 9.999999999999341}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_whenOne_thenReturn9999999999999341() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 1.0d, 10.0d, 0.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(9.999999999999341d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_whenTwo_thenReturnTen() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 10.0d, 10.0d, -0.11111111111111116d, 2.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }

  /**
   * Test {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double, double, double,
   * double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticFormulas#constantElasticityOfVarianceOptionValue(double,
   * double, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticFormulas.constantElasticityOfVarianceOptionValue(double, double, double, double, double, double, boolean)"
  })
  public void testConstantElasticityOfVarianceOptionValue_whenZero_thenReturnTen() {
    // Arrange and Act
    double actualConstantElasticityOfVarianceOptionValueResult =
        AnalyticFormulas.constantElasticityOfVarianceOptionValue(
            10.0d, 10.0d, 10.0d, 0.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualConstantElasticityOfVarianceOptionValueResult, 0.0);
  }
}
