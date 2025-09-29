package net.finmath.stochastic.operators;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomOperator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class RandomVariableOperatorDiffblueTest {
  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable actualApplyResult =
        actualExpectedShortFallResult.apply(randomVariableFromDoubleArray);

    // Assert
    assertSame(randomVariableFromDoubleArray, actualApplyResult);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall2() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.getVariance()).thenReturn(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).getQuantile(0.0d);
    verify(randomVariableAAD).getVariance();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(10.0d);
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
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall3() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.average()).thenReturn(randomVariableDifferentiableAAD);
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getVariance()).thenReturn(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD2);

    // Assert
    verify(randomVariableAAD).average();
    verify(randomVariableAAD2).getQuantile(0.0d);
    verify(randomVariableAAD2).getVariance();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).sub(10.0d);
    assertTrue(actualApplyResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyResult instanceof RandomVariableDifferentiableAAD);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualApplyResult).getFactory());
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall4() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.average()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getVariance()).thenReturn(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD2);

    // Assert
    verify(randomVariableAAD).average();
    verify(randomVariableAAD2).getQuantile(0.0d);
    verify(randomVariableAAD2).getVariance();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).sub(10.0d);
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
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>Then return apply {@link RandomVariableAAD} is {@link RandomVariableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_thenReturnApplyRandomVariableAADIsRandomVariableAAD() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.getVariance()).thenReturn(0.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).getVariance();
    verify(randomVariableAAD).isDeterministic();
    assertSame(randomVariableAAD, actualApplyResult);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>Then return apply {@link Scalar} with value is ten is {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_thenReturnApplyScalarWithValueIsTenIsScalarWithValueIsTen() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(ofResult);

    // Assert
    assertSame(ofResult, actualApplyResult);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then apply {@link RandomVariableAAD} return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_whenZero_thenApplyRandomVariableAADReturnScalar() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));
    when(randomVariableAAD.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.getVariance()).thenReturn(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).getQuantile(0.0d);
    verify(randomVariableAAD).getVariance();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(10.0d);
    assertTrue(actualApplyResult instanceof Scalar);
    assertEquals(Double.POSITIVE_INFINITY, actualApplyResult.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualApplyResult.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualApplyResult.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualApplyResult.expectation();
    assertSame(actualApplyResult, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then apply {@link RandomVariableAAD} Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_whenZero_thenApplyRandomVariableAADValuesReturnScalar() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.average()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    when(randomVariableAAD2.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.isDeterministic()).thenReturn(false);
    when(randomVariableAAD2.getVariance()).thenReturn(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD2);

    // Assert
    verify(randomVariableAAD).average();
    verify(randomVariableAAD2).getQuantile(0.0d);
    verify(randomVariableAAD2).getVariance();
    verify(randomVariableAAD2).isDeterministic();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).sub(10.0d);
    assertTrue(actualApplyResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualApplyResult.getValues() instanceof Scalar);
    assertTrue(actualApplyResult.isNaN() instanceof Scalar);
    assertNull(actualApplyResult.getRealizations());
    assertNull(actualApplyResult.getOperator());
    assertNull(actualApplyResult.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then calls {@link RandomVariableAAD#choose(RandomVariable, RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_whenZero_thenCallsChoose() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.average()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD);
    when(randomVariableAAD3.isDeterministic()).thenReturn(false);
    when(randomVariableAAD3.getVariance()).thenReturn(10.0d);
    RandomVariable actualApplyResult = actualExpectedShortFallResult.apply(randomVariableAAD3);

    // Assert
    verify(randomVariableAAD2).average();
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD3).getQuantile(0.0d);
    verify(randomVariableAAD3).getVariance();
    verify(randomVariableAAD3).isDeterministic();
    verify(randomVariableAAD3).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3).sub(10.0d);
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
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualApplyResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_whenZero_thenThrowIllegalArgumentException() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getQuantile(anyDouble())).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.getVariance()).thenReturn(10.0d);

    // Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> actualExpectedShortFallResult.apply(randomVariableAAD));
    verify(randomVariableAAD).getQuantile(0.0d);
    verify(randomVariableAAD).getVariance();
    verify(randomVariableAAD).isDeterministic();
  }

  /**
   * Test {@link RandomVariableOperator#expectedShortFall(Double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableOperator#expectedShortFall(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomVariableOperator.expectedShortFall(Double)"})
  public void testExpectedShortFall_whenZero_thenThrowIllegalArgumentException2() {
    // Arrange and Act
    RandomOperator actualExpectedShortFallResult = RandomVariableOperator.expectedShortFall(0.0d);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.getQuantile(anyDouble())).thenReturn(10.0d);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableAAD.getVariance()).thenReturn(10.0d);

    // Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> actualExpectedShortFallResult.apply(randomVariableAAD));
    verify(randomVariableAAD).getQuantile(0.0d);
    verify(randomVariableAAD).getVariance();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(10.0d);
  }
}
