package net.finmath.optimizer.parameter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ParameterTransfromSimpleBoundedDiffblueTest {
  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualModelParametersFrom.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom2() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable instanceof RandomVariableLazyEvaluation);
    assertEquals(1, actualModelParametersFrom.length);
    assertEquals(20.0d, randomVariable.getAverage(), 0.0);
    assertEquals(20.0d, randomVariable.getMax(), 0.0);
    assertEquals(20.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {20.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom3() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(1, actualModelParametersFrom.length);
    assertArrayEquals(new double[] {9.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom4() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualModelParametersFrom[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualModelParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom5() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(-1.0d)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(1, actualModelParametersFrom.length);
    assertArrayEquals(new double[] {9.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom6() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(-1.0d);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {
              randomVariableLazyEvaluation, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualModelParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualModelParametersFrom[0] instanceof RandomVariableLazyEvaluation);
    assertEquals(2, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom7() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(-1.0d);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {ofResult, new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualModelParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualModelParametersFrom[0] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom8() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(-1.0d)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualModelParametersFrom.length);
    assertEquals(2, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom9() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(1, actualModelParametersFrom.length);
    assertArrayEquals(new double[] {9.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom10() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    RandomVariable[] parameterUpperBound = new RandomVariable[] {randomVariableDifferentiableAAD};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualModelParametersFrom.length);
    assertEquals(2, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenFirstElementReturnRandomVariableFromFloatArray() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {new RandomVariableFromFloatArray(-1.0d)});

    // Assert
    assertTrue(actualModelParametersFrom[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenFirstElementReturnScalar() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {Scalar.of(-1.0d)});

    // Assert
    assertTrue(actualModelParametersFrom[0] instanceof Scalar);
    assertEquals(1, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenFirstElementValuesReturnScalar() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.exp()).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(anyDouble())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).exp();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD5).mult(-1.0d);
    verify(randomVariableAAD3).mult(-1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.getValues() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertEquals(1, actualModelParametersFrom.length);
    assertEquals(20.0d, randomVariable.getAverage(), 0.0);
    assertEquals(20.0d, randomVariable.getMax(), 0.0);
    assertEquals(20.0d, randomVariable.getMin(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is minus one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenReturnFirstElementAverageIsMinusOne() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(-1.0d)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(-1.0d, randomVariable.getAverage(), 0.0);
    assertEquals(-1.0d, randomVariable.getMax(), 0.0);
    assertEquals(-1.0d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualModelParametersFrom.length);
    assertArrayEquals(new double[] {-1.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is minus one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_whenRandomVariableFromDoubleArrayWithValueIsMinusOne() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromSimpleBounded.getModelParametersFrom(
            new RandomVariable[] {
              randomVariableDifferentiableAAD, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualModelParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualModelParametersFrom[0] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom2() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom3() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY)});

    // Assert
    assertTrue(actualOptimizerParametersFrom[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom4() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualOptimizerParametersFrom[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOptimizerParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom5() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {
              randomVariableLazyEvaluation, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualOptimizerParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOptimizerParametersFrom[0] instanceof RandomVariableLazyEvaluation);
    assertEquals(2, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom6() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableDifferentiableAAD ofResult =
        RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {ofResult, new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualOptimizerParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOptimizerParametersFrom[0] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom7() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {
              randomVariableDifferentiableAAD, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualOptimizerParametersFrom[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualOptimizerParametersFrom[0] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom8() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    RandomVariable[] parameterUpperBound = new RandomVariable[] {randomVariableDifferentiableAAD};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(2, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom9() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(2, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom10() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableDifferentiableAAD values2 =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values2, new RandomVariableDifferentiableAADFactory());
    RandomVariable[] parameterUpperBound = new RandomVariable[] {randomVariableDifferentiableAAD};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(2, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom11() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariableDifferentiableAAD values = RandomVariableDifferentiableAAD.of(-1.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    RandomVariable[] parameterUpperBound = new RandomVariable[] {randomVariableDifferentiableAAD};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(2, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom_thenFirstElementReturnScalar() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {Scalar.of(Double.NEGATIVE_INFINITY)});

    // Assert
    assertTrue(actualOptimizerParametersFrom[0] instanceof Scalar);
    assertEquals(1, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom_thenFirstElementValuesReturnScalar() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.log()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.log()).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.sub(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).log();
    verify(randomVariableAAD).log();
    verify(randomVariableAAD2).mult(-1.0d);
    verify(randomVariableAAD5).sub(isA(RandomVariable.class));
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.getValues() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertEquals(-10.0d, randomVariable.getAverage(), 0.0);
    assertEquals(-10.0d, randomVariable.getMax(), 0.0);
    assertEquals(-10.0d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom_thenReturnFirstElementAverageIsPositive_infinity() {
    // Arrange
    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariableAAD.log()).thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.log()).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.sub(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD4);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {randomVariableAAD5});

    // Assert
    verify(randomVariableAAD4).div(isA(RandomVariable.class));
    verify(randomVariableAAD3).log();
    verify(randomVariableAAD).log();
    verify(randomVariableAAD2).mult(-1.0d);
    verify(randomVariableAAD5).sub(isA(RandomVariable.class));
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(Double.POSITIVE_INFINITY, randomVariable.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, randomVariable.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, randomVariable.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Gradient size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromSimpleBounded#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromSimpleBounded.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom_thenReturnFirstElementGradientSizeIsOne() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)};

    ParameterTransfromSimpleBounded parameterTransfromSimpleBounded =
        new ParameterTransfromSimpleBounded(parameterLowerBound, parameterUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromSimpleBounded.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, randomVariable.getRealizations(), 0.0);
  }
}
