package net.finmath.optimizer.parameter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ParameterTransfromBoundedSlopeDiffblueTest {
  /**
   * Test {@link ParameterTransfromBoundedSlope#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromBoundedSlope#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromBoundedSlope.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    ParameterTransfromBoundedSlope parameterTransfromBoundedSlope =
        new ParameterTransfromBoundedSlope(
            parameterLowerBound,
            parameterUpperBound,
            parameterSlopeLowerBound,
            parameterSlopeUpperBound);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromBoundedSlope.getModelParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualModelParametersFrom[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualModelParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromBoundedSlope#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code 8.999546011007986}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromBoundedSlope#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromBoundedSlope.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenReturnFirstElementAverageIs8999546011007986() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable[] parameterUpperBound = new RandomVariable[] {randomVariableAAD};
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterSlopeLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    ParameterTransfromBoundedSlope parameterTransfromBoundedSlope =
        new ParameterTransfromBoundedSlope(
            parameterLowerBound,
            parameterUpperBound,
            parameterSlopeLowerBound,
            parameterSlopeUpperBound);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromBoundedSlope.getModelParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualModelParametersFrom.length);
    assertEquals(8.999546011007986d, randomVariable.getAverage(), 0.0);
    assertEquals(8.999546011007986d, randomVariable.getMax(), 0.0);
    assertEquals(8.999546011007986d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {8.999546011007986d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromBoundedSlope#getModelParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code 9.999500612108786}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromBoundedSlope#getModelParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromBoundedSlope.getModelParametersFrom(RandomVariable[])"
  })
  public void testGetModelParametersFrom_thenReturnFirstElementAverageIs9999500612108786() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    ParameterTransfromBoundedSlope parameterTransfromBoundedSlope =
        new ParameterTransfromBoundedSlope(
            parameterLowerBound,
            parameterUpperBound,
            parameterSlopeLowerBound,
            parameterSlopeUpperBound);

    // Act
    RandomVariable[] actualModelParametersFrom =
        parameterTransfromBoundedSlope.getModelParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualModelParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualModelParametersFrom.length);
    assertEquals(9.999500612108786d, randomVariable.getAverage(), 0.0);
    assertEquals(9.999500612108786d, randomVariable.getMax(), 0.0);
    assertEquals(9.999500612108786d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {9.999500612108786d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromBoundedSlope#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterTransfromBoundedSlope#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromBoundedSlope.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    ParameterTransfromBoundedSlope parameterTransfromBoundedSlope =
        new ParameterTransfromBoundedSlope(
            parameterLowerBound,
            parameterUpperBound,
            parameterSlopeLowerBound,
            parameterSlopeUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromBoundedSlope.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualOptimizerParametersFrom[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualOptimizerParametersFrom.length);
  }

  /**
   * Test {@link ParameterTransfromBoundedSlope#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromBoundedSlope#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromBoundedSlope.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom_thenReturnFirstElementAverageIsNaN() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable[] parameterUpperBound = new RandomVariable[] {randomVariableAAD};
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterSlopeLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    ParameterTransfromBoundedSlope parameterTransfromBoundedSlope =
        new ParameterTransfromBoundedSlope(
            parameterLowerBound,
            parameterUpperBound,
            parameterSlopeLowerBound,
            parameterSlopeUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromBoundedSlope.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(Double.NaN, randomVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMax(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ParameterTransfromBoundedSlope#getOptimizerParametersFrom(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterTransfromBoundedSlope#getOptimizerParametersFrom(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ParameterTransfromBoundedSlope.getOptimizerParametersFrom(RandomVariable[])"
  })
  public void testGetOptimizerParametersFrom_thenReturnFirstElementAverageIsPositive_infinity() {
    // Arrange
    RandomVariable[] parameterLowerBound =
        new RandomVariable[] {RandomVariableDifferentiableAAD.of(-1.0d)};
    RandomVariable[] parameterUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeLowerBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterSlopeUpperBound =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    ParameterTransfromBoundedSlope parameterTransfromBoundedSlope =
        new ParameterTransfromBoundedSlope(
            parameterLowerBound,
            parameterUpperBound,
            parameterSlopeLowerBound,
            parameterSlopeUpperBound);

    // Act
    RandomVariable[] actualOptimizerParametersFrom =
        parameterTransfromBoundedSlope.getOptimizerParametersFrom(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualOptimizerParametersFrom[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualOptimizerParametersFrom.length);
    assertEquals(Double.POSITIVE_INFINITY, randomVariable.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, randomVariable.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, randomVariable.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, randomVariable.getRealizations(), 0.0);
  }
}
