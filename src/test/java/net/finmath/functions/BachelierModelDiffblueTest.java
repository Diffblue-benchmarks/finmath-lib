package net.finmath.functions;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
import java.util.function.DoubleUnaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BachelierModelDiffblueTest {
  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(126.15662610100802d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(3.141592653589793d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(94.82007552296452d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(2.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(90.17224441855609d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(82.41241314072116d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(3.141592653589793d, 0.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(31.41592653589793d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble_when05_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Math#PI}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionValue(double, double, double, double, double)"
  })
  public void testBachelierOptionValueWithDoubleDoubleDoubleDoubleDouble_whenPi_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(3.141592653589793d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(
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
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBachelierOptionValueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBachelierOptionValueResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBachelierOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
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
        BachelierModel.bachelierOptionValue(
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
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
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
        BachelierModel.bachelierOptionValue(
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
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
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
        BachelierModel.bachelierOptionValue(
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
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable7() {
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
        BachelierModel.bachelierOptionValue(
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
   * Test {@link BachelierModel#bachelierOptionValue(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionValueResult =
        BachelierModel.bachelierOptionValue(
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
    assertSame(
        factory, ((RandomVariableDifferentiableAAD) actualBachelierOptionValueResult).getFactory());
    assertArrayEquals(
        new double[] {4162.27766016838d}, actualBachelierOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 3.089859425347398}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_thenReturn3089859425347398() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        BachelierModel.bachelierOptionImpliedVolatility(
            1.3068844036618974d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.089859425347398d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.22475990624610612}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_thenReturn022475990624610612() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        BachelierModel.bachelierOptionImpliedVolatility(
            17.079468445347132d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.22475990624610612d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 3.157150460928908}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenOne_thenReturn3157150460928908() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        BachelierModel.bachelierOptionImpliedVolatility(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.157150460928908d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.7926654595212022}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenTen_thenReturn07926654595212022() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        BachelierModel.bachelierOptionImpliedVolatility(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.7926654595212022d, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionImpliedVolatility(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionImpliedVolatility(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierOptionImpliedVolatility_whenZero_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualBachelierOptionImpliedVolatilityResult =
        BachelierModel.bachelierOptionImpliedVolatility(10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualBachelierOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.38192978634591623d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.37591481702292473d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.38797339413907156d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when05_thenReturnOne() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(0.5d, 0.0d, 10.0d, 0.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when05_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when05_thenReturnZero2() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(0.5d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierOptionDeltaWithDoubleDoubleDoubleDoubleDouble_whenTen_thenReturn05() {
    // Arrange and Act
    double actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualBachelierOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void testBachelierOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualBachelierOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.5d, actualBachelierOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.5d, actualBachelierOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.5d}, actualBachelierOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualBachelierOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionDeltaResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierOptionDeltaResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBachelierOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualBachelierOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionDelta(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionDeltaResult =
        BachelierModel.bachelierOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(actualBachelierOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualBachelierOptionDeltaResult.getAverage(), 0.0);
    assertEquals(10.0d, actualBachelierOptionDeltaResult.getMax(), 0.0);
    assertEquals(10.0d, actualBachelierOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualBachelierOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionVega(double, double, double, double, double)"
  })
  public void testBachelierOptionVegaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.615662610100802d, actualBachelierOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionVega(double, double, double, double, double)"
  })
  public void testBachelierOptionVegaWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.528822005427315d, actualBachelierOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionVega(double, double, double, double, double)"
  })
  public void testBachelierOptionVegaWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.000389484301364d, actualBachelierOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionVega(double, double, double, double, double)"
  })
  public void testBachelierOptionVegaWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.114936420863774d, actualBachelierOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(double, double, double, double, double)} with
   * {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierOptionVega(double, double, double, double, double)"
  })
  public void testBachelierOptionVegaWithDoubleDoubleDoubleDoubleDouble_when05_thenReturnZero() {
    // Arrange and Act
    double actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void testBachelierOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(12.615662610100802d, actualBachelierOptionVegaResult.getAverage(), 0.0);
    assertEquals(12.615662610100802d, actualBachelierOptionVegaResult.getMax(), 0.0);
    assertEquals(12.615662610100802d, actualBachelierOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {12.615662610100802d}, actualBachelierOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void testBachelierOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierOptionVegaResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierOptionVegaResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBachelierOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierOptionVega(RandomVariable, RandomVariable, double, double,
   * RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code double}, {@code
   * double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void testBachelierOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierOptionVegaResult =
        BachelierModel.bachelierOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(actualBachelierOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(316.22776601683796d, actualBachelierOptionVegaResult.getAverage(), 0.0);
    assertEquals(316.22776601683796d, actualBachelierOptionVegaResult.getMax(), 0.0);
    assertEquals(316.22776601683796d, actualBachelierOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {316.22776601683796d}, actualBachelierOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.012528822005427313}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierDigitalOptionDelta_thenReturn0012528822005427313() {
    // Arrange and Act
    double actualBachelierDigitalOptionDeltaResult =
        BachelierModel.bachelierDigitalOptionDelta(6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.012528822005427313d, actualBachelierDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierDigitalOptionDelta_when05_thenReturnZero() {
    // Arrange and Act
    double actualBachelierDigitalOptionDeltaResult =
        BachelierModel.bachelierDigitalOptionDelta(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.01211493642086377}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierDigitalOptionDelta_whenOne_thenReturn001211493642086377() {
    // Arrange and Act
    double actualBachelierDigitalOptionDeltaResult =
        BachelierModel.bachelierDigitalOptionDelta(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.01211493642086377d, actualBachelierDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.0126156626101008}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierDigitalOptionDelta_whenTen_thenReturn00126156626101008() {
    // Arrange and Act
    double actualBachelierDigitalOptionDeltaResult =
        BachelierModel.bachelierDigitalOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0126156626101008d, actualBachelierDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code 0.012000389484301361}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierDigitalOptionDelta(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierDigitalOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierDigitalOptionDelta_whenZero_thenReturn0012000389484301361() {
    // Arrange and Act
    double actualBachelierDigitalOptionDeltaResult =
        BachelierModel.bachelierDigitalOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.012000389484301361d, actualBachelierDigitalOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(12.615662610100802d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            3.141592653589793d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.1688080457165103d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(2.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.057760757195110604d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.006733553125075845d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            3.141592653589793d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble7() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            3.141592653589793d, -0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-68.58407346410206d, actualBachelierHomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        12.615662610100802d, actualBachelierHomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(12.615662610100802d, actualBachelierHomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(12.615662610100802d, actualBachelierHomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {12.615662610100802d},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierHomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierHomogeneousOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBachelierHomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBachelierHomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBachelierHomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
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
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).mult(1.0d);
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(100.0d, actualBachelierHomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBachelierHomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(100.0d, actualBachelierHomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {100.0d}, actualBachelierHomogeneousOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
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
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).doubleValue();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2).isDeterministic();
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualBachelierHomogeneousOptionValueResult.getFiltrationTime(), 0.0);
    assertEquals(1316.227766016838d, actualBachelierHomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(1316.227766016838d, actualBachelierHomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(1316.227766016838d, actualBachelierHomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1316.227766016838d},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
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
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {1316.227766016838d},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable7() {
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
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.getValues()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {1316.227766016838d},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable8() {
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
    RandomVariableDifferentiableAAD volatility = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {1316.227766016838d},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionValueResult =
        BachelierModel.bachelierHomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierHomogeneousOptionValueResult.getValues()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
            .getGradient()
            .size());
    assertSame(
        factory,
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionValueResult)
            .getFactory());
    assertArrayEquals(
        new double[] {1316.227766016838d},
        actualBachelierHomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 30.89859425347398}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionImpliedVolatility_thenReturn3089859425347398() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierHomogeneousOptionImpliedVolatility(
            1.3068844036618974d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(30.89859425347398d, actualBachelierHomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 31.57150460928908}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionImpliedVolatility_thenReturn3157150460928908() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierHomogeneousOptionImpliedVolatility(
            1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(31.57150460928908d, actualBachelierHomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 7.926654595212021}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionImpliedVolatility_thenReturn7926654595212021() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierHomogeneousOptionImpliedVolatility(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(7.926654595212021d, actualBachelierHomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 2.2475990624610613}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionImpliedVolatility_thenReturn22475990624610613() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierHomogeneousOptionImpliedVolatility(
            17.079468445347132d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(2.2475990624610613d, actualBachelierHomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionImpliedVolatility(double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionImpliedVolatility_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierHomogeneousOptionImpliedVolatility(
            10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        Double.POSITIVE_INFINITY, actualBachelierHomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0013315596295692795d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(7.827011290012761E-4d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0022132629289599195d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(0.5d, 0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble7() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(0.5d, -0.5d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBachelierHomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualBachelierHomogeneousOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.5d, actualBachelierHomogeneousOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.5d, actualBachelierHomogeneousOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.5d}, actualBachelierHomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierHomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierHomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBachelierHomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableDifferentiableAAD volatility = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionDeltaResult =
        BachelierModel.bachelierHomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBachelierHomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1.2615662610100802d, actualBachelierHomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(
            6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.6323061675928043d, actualBachelierHomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(0.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.008500366602520347d, actualBachelierHomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.021979480031862706d, actualBachelierHomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierHomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierHomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierHomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.2615662610100802d, actualBachelierHomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(1.2615662610100802d, actualBachelierHomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(1.2615662610100802d, actualBachelierHomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.2615662610100802d},
        actualBachelierHomogeneousOptionVegaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBachelierHomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierHomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(actualBachelierHomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(31.622776601683796d, actualBachelierHomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(31.622776601683796d, actualBachelierHomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(31.622776601683796d, actualBachelierHomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {31.622776601683796d},
        actualBachelierHomogeneousOptionVegaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD4);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD3).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD4).div(isA(RandomVariable.class));
    verify(randomVariableAAD).mult(3.1622776601683795d);
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierHomogeneousOptionVegaResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierHomogeneousOptionVegaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionVegaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionVegaResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionVegaResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierHomogeneousOptionVegaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBachelierHomogeneousOptionVegaResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierHomogeneousOptionVegaResult)
            .getGradient()
            .size());
    assertEquals(1, actualBachelierHomogeneousOptionVegaResult.size());
    assertEquals(1.0d, actualBachelierHomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(1.0d, actualBachelierHomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(1.0d, actualBachelierHomogeneousOptionVegaResult.getMin(), 0.0);
    assertEquals(3, actualBachelierHomogeneousOptionVegaResult.getTypePriority());
    assertTrue(actualBachelierHomogeneousOptionVegaResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualBachelierHomogeneousOptionVegaResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {1.0d}, actualBachelierHomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierHomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierHomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierHomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD4);
    RandomVariableDifferentiableAAD volatility = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    RandomVariable actualBachelierHomogeneousOptionVegaResult =
        BachelierModel.bachelierHomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD3).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD4).div(isA(RandomVariable.class));
    verify(randomVariableAAD).mult(3.1622776601683795d);
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(actualBachelierHomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualBachelierHomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(1.0d, actualBachelierHomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(1.0d, actualBachelierHomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.0d}, actualBachelierHomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(58.493124560460885d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(24.179443076799544d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(2.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(26.99031271168597d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            3.141592653589793d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(30.48619995526029d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(10.0d, 10.0d, 10.0d, 10.0d, 1.0d);

    // Assert
    assertEquals(12.615662610100802d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(1.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble7() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(1.0d, 0.0d, 10.0d, 0.5d, 10.0d);

    // Assert
    assertEquals(5.0d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionValue(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionValueWithDoubleDoubleDoubleDoubleDouble_when05() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierInhomogeneousOptionValueResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        58.493124560460885d, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(58.493124560460885d, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(58.493124560460885d, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {58.493124560460885d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierInhomogeneousOptionValueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(Double.NaN, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(Double.NaN, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
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
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).add(isA(RandomVariable.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).mult(1.0d);
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(100.0d, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(100.0d, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(100.0d, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {100.0d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
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
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).doubleValue();
    verify(randomVariableAAD2).getFiltrationTime();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2).isDeterministic();
    verify(forward, atLeast(1)).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualBachelierInhomogeneousOptionValueResult.getFiltrationTime(), 0.0);
    assertEquals(
        2466.2051989476427d, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(2466.2051989476427d, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(2466.2051989476427d, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2466.2051989476427d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(1.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        246.62051989476424d, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(246.62051989476424d, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(246.62051989476424d, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {246.62051989476424d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(1.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {156.62051989476427d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(1.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableDifferentiableAAD volatility = RandomVariableDifferentiableAAD.of(1.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        114.66205198947642d, actualBachelierInhomogeneousOptionValueResult.getAverage(), 0.0);
    assertEquals(114.66205198947642d, actualBachelierInhomogeneousOptionValueResult.getMax(), 0.0);
    assertEquals(114.66205198947642d, actualBachelierInhomogeneousOptionValueResult.getMin(), 0.0);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {114.66205198947642d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionValue(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionValue(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionValueWithRandomVariableRandomVariableDoubleDoubleRandomVariable9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionValueResult =
        BachelierModel.bachelierInhomogeneousOptionValue(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD, atLeast(1)).apply(Mockito.<DoubleUnaryOperator>any());
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).getTypePriority();
    verify(forward, atLeast(1)).sub(10.0d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult.getValues()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionValueResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
            .getGradient()
            .size());
    assertSame(
        factory,
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionValueResult)
            .getFactory());
    assertArrayEquals(
        new double[] {156.62051989476427d},
        actualBachelierInhomogeneousOptionValueResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.4847569978156799}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionImpliedVolatility_thenReturn04847569978156799() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(
            17.079468445347132d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        0.4847569978156799d, actualBachelierInhomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 6.664137762469973}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionImpliedVolatility_thenReturn6664137762469973() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(
            1.3068844036618974d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        6.664137762469973d, actualBachelierInhomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 6.809269520768016}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionImpliedVolatility_thenReturn6809269520768016() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(
            1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        6.809269520768016d, actualBachelierInhomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 7.926654595212021}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionImpliedVolatility_thenReturn7926654595212021() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(
            10.0d, 10.0d, 10.0d, 1.0d, 10.0d);

    // Assert
    assertEquals(
        7.926654595212021d, actualBachelierInhomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 1.7096026370866189}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionImpliedVolatility_thenReturn17096026370866189() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        1.7096026370866189d, actualBachelierInhomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BachelierModel#bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionImpliedVolatility_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionImpliedVolatilityResult =
        BachelierModel.bachelierInhomogeneousOptionImpliedVolatility(
            10.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        Double.POSITIVE_INFINITY, actualBachelierInhomogeneousOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.2696640015186614d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(2.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.2926614486610168d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(0.5d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.2585147457294628d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(10.0d, 10.0d, 10.0d, 10.0d, 1.0d);

    // Assert
    assertEquals(0.5d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(1.0d, 0.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble7() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(1.0d, 0.0d, 10.0d, 0.5d, 10.0d);

    // Assert
    assertEquals(1.0d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionDelta(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionDeltaWithDoubleDoubleDoubleDoubleDouble_when05() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierInhomogeneousOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualBachelierInhomogeneousOptionDeltaResult.getAverage(), 0.0);
    assertEquals(0.5d, actualBachelierInhomogeneousOptionDeltaResult.getMax(), 0.0);
    assertEquals(0.5d, actualBachelierInhomogeneousOptionDeltaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.5d}, actualBachelierInhomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierInhomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableFromDoubleArray forward =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierInhomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBachelierInhomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(volatility).mult(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBachelierInhomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionDelta(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionDelta(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionDeltaWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD payoffUnit = mock(RandomVariableAAD.class);
    when(payoffUnit.doubleValue()).thenReturn(10.0d);
    when(payoffUnit.isDeterministic()).thenReturn(true);
    when(payoffUnit.getFiltrationTime()).thenReturn(10.0d);
    when(payoffUnit.getTypePriority()).thenReturn(1);
    when(payoffUnit.log()).thenReturn(randomVariableAAD3);
    when(payoffUnit.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualBachelierInhomogeneousOptionDeltaResult =
        BachelierModel.bachelierInhomogeneousOptionDelta(
            forward, volatility, 10.0d, 10.0d, payoffUnit);

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(payoffUnit).doubleValue();
    verify(payoffUnit).getFiltrationTime();
    verify(payoffUnit).getTypePriority();
    verify(payoffUnit).isDeterministic();
    verify(payoffUnit).log();
    verify(randomVariableAAD3).mult(2.0d);
    verify(volatility).mult(isA(RandomVariable.class));
    verify(payoffUnit).squared();
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionDeltaResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualBachelierInhomogeneousOptionDeltaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.849312456046088d, actualBachelierInhomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble2() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(1.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(4.844920321347907d, actualBachelierInhomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble3() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(2.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.0403228604567305d, actualBachelierInhomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble4() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            6.283185307179586d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(5.6643563437475235d, actualBachelierInhomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble5() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualBachelierInhomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double, double, double,
   * double)} with {@code double}, {@code double}, {@code double}, {@code double}, {@code double}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BachelierModel.bachelierInhomogeneousOptionVega(double, double, double, double, double)"
  })
  public void testBachelierInhomogeneousOptionVegaWithDoubleDoubleDoubleDoubleDouble6() {
    // Arrange and Act
    double actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(10.0d, 10.0d, 10.0d, 10.0d, 1.0d);

    // Assert
    assertEquals(1.2615662610100802d, actualBachelierInhomogeneousOptionVegaResult, 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        5.849312456046088d, actualBachelierInhomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(5.849312456046088d, actualBachelierInhomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(5.849312456046088d, actualBachelierInhomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {5.849312456046088d},
        actualBachelierInhomogeneousOptionVegaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray forward = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBachelierInhomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBachelierInhomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(0.0d, actualBachelierInhomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualBachelierInhomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable3() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD2);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        146.62051989476427d, actualBachelierInhomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(146.62051989476427d, actualBachelierInhomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(146.62051989476427d, actualBachelierInhomogeneousOptionVegaResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {146.62051989476427d},
        actualBachelierInhomogeneousOptionVegaResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable4() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableAAD4);

    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD6);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD5).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD6).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).mult(3.1622776601683795d);
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {1.0d}, actualBachelierInhomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable5() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.apply(Mockito.<DoubleUnaryOperator>any()))
        .thenReturn(randomVariableAAD4);

    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);

    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.sub(anyDouble())).thenReturn(randomVariableAAD6);

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, 10.0d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(randomVariableAAD5).apply(isA(DoubleUnaryOperator.class));
    verify(randomVariableAAD6).div(isA(RandomVariable.class));
    verify(randomVariableAAD2).div(isA(RandomVariable.class));
    verify(volatility).doubleValue();
    verify(volatility).getFiltrationTime();
    verify(volatility).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD3).mult(3.1622776601683795d);
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(volatility).mult(isA(RandomVariable.class));
    verify(forward).sub(10.0d);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualBachelierInhomogeneousOptionVegaResult.getAverage(), 0.0);
    assertEquals(1.0d, actualBachelierInhomogeneousOptionVegaResult.getMax(), 0.0);
    assertEquals(1.0d, actualBachelierInhomogeneousOptionVegaResult.getMin(), 0.0);
    assertEquals(10.0d, actualBachelierInhomogeneousOptionVegaResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {1.0d}, actualBachelierInhomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable6() {
    // Arrange
    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(volatility).getTypePriority();
    verify(forward).mult(0.0d);
    verify(volatility).mult(isA(RandomVariable.class));
    verify(volatility, atLeast(1)).getValues();
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {1.0d}, actualBachelierInhomogeneousOptionVegaResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable,
   * double, double, RandomVariable)} with {@code RandomVariable}, {@code RandomVariable}, {@code
   * double}, {@code double}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link BachelierModel#bachelierInhomogeneousOptionVega(RandomVariable,
   * RandomVariable, double, double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BachelierModel.bachelierInhomogeneousOptionVega(RandomVariable, RandomVariable, double, double, RandomVariable)"
  })
  public void
      testBachelierInhomogeneousOptionVegaWithRandomVariableRandomVariableDoubleDoubleRandomVariable7() {
    // Arrange
    RandomVariableAAD forward = mock(RandomVariableAAD.class);
    when(forward.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualBachelierInhomogeneousOptionVegaResult =
        BachelierModel.bachelierInhomogeneousOptionVega(
            forward, volatility, -1.0E-10d, 10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(volatility).getTypePriority();
    verify(forward).mult(0.0d);
    verify(volatility).mult(isA(RandomVariable.class));
    verify(volatility, atLeast(1)).getValues();
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualBachelierInhomogeneousOptionVegaResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(
        2,
        ((RandomVariableDifferentiableAAD) actualBachelierInhomogeneousOptionVegaResult)
            .getGradient()
            .size());
    assertArrayEquals(
        new double[] {1.0d}, actualBachelierInhomogeneousOptionVegaResult.getRealizations(), 0.0);
  }
}
