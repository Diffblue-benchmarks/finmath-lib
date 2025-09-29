package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CancelableSwapDiffblueTest {
  /**
   * Test {@link CancelableSwap#CancelableSwap(boolean[], double[], double[], double[], double[],
   * double[])}.
   *
   * <p>Method under test: {@link CancelableSwap#CancelableSwap(boolean[], double[], double[],
   * double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CancelableSwap.<init>(boolean[], double[], double[], double[], double[], double[])"
  })
  public void testNewCancelableSwap() {
    // Arrange and Act
    CancelableSwap actualCancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertNull(actualCancelableSwap.getCurrency());
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(18.0d, actualValue.getAverage(), 0.0);
    assertEquals(18.0d, actualValue.getMax(), 0.0);
    assertEquals(18.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {18.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(1.0d));
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(3.0d));
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(0.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(randomVariable);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(randomVariable, atLeast(1)).mult(-9.0d);
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable, atLeast(1)).sub(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2020.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(null);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(randomVariable);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(randomVariable, atLeast(1)).mult(-9.0d);
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable, atLeast(1)).sub(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2020.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariable);
    when(randomVariable2.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable2.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(randomVariable2);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(randomVariable, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getFiltrationTime();
    verify(randomVariable, atLeast(1)).getTypePriority();
    verify(randomVariable, atLeast(1)).isDeterministic();
    verify(randomVariable2, atLeast(1)).mult(-9.0d);
    verify(randomVariable2, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable2, atLeast(1)).sub(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2020.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#add(double)}.
   * </ul>
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenCallsAdd()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.add(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.getAverage()).thenReturn(10.0d);

    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.doubleValue()).thenReturn(10.0d);
    when(randomVariable3.isDeterministic()).thenReturn(true);
    when(randomVariable3.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable3.getTypePriority()).thenReturn(1);
    when(randomVariable3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariable2);

    RandomVariable randomVariable4 = mock(RandomVariable.class);
    when(randomVariable4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariable3);
    when(randomVariable4.mult(anyDouble())).thenReturn(randomVariable);
    when(randomVariable4.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(randomVariable4);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(randomVariable, atLeast(1)).add(1.0d);
    verify(randomVariable3, atLeast(1)).doubleValue();
    verify(randomVariable2, atLeast(1)).getAverage();
    verify(randomVariable3, atLeast(1)).getFiltrationTime();
    verify(randomVariable3, atLeast(1)).getTypePriority();
    verify(randomVariable3, atLeast(1)).isDeterministic();
    verify(randomVariable4, atLeast(1)).mult(-9.0d);
    verify(randomVariable4, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable3, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable4, atLeast(1)).sub(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2020.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CancelableSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#getAverage()}.
   * </ul>
   *
   * <p>Method under test: {@link CancelableSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CancelableSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenCallsGetAverage()
      throws CalculationException {
    // Arrange
    CancelableSwap cancelableSwap =
        new CancelableSwap(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.getAverage()).thenReturn(10.0d);

    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.doubleValue()).thenReturn(10.0d);
    when(randomVariable2.isDeterministic()).thenReturn(true);
    when(randomVariable2.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable2.getTypePriority()).thenReturn(1);
    when(randomVariable2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariable);

    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariable2);
    when(randomVariable3.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable3.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(randomVariable3);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = cancelableSwap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(randomVariable2, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getAverage();
    verify(randomVariable2, atLeast(1)).getFiltrationTime();
    verify(randomVariable2, atLeast(1)).getTypePriority();
    verify(randomVariable2, atLeast(1)).isDeterministic();
    verify(randomVariable3, atLeast(1)).mult(-9.0d);
    verify(randomVariable3, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable2, atLeast(1)).mult(isA(RandomVariable.class));
    verify(randomVariable3, atLeast(1)).sub(anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2020.0d}, actualValue.getRealizations(), 0.0);
  }
}
