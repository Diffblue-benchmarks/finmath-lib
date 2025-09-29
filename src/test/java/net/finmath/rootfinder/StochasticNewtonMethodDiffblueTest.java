package net.finmath.rootfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.rootfinder.StochasticNewtonMethod.MethodForAccuracy;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StochasticNewtonMethodDiffblueTest {
  @Mock private MethodForAccuracy methodForAccuracy;

  @Mock private RandomVariable randomVariable;

  @InjectMocks private StochasticNewtonMethod stochasticNewtonMethod;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StochasticNewtonMethod#StochasticNewtonMethod(RandomVariable, MethodForAccuracy)}
   *   <li>{@link StochasticNewtonMethod#getAccuracy()}
   *   <li>{@link StochasticNewtonMethod#getBestPoint()}
   *   <li>{@link StochasticNewtonMethod#getNextPoint()}
   *   <li>{@link StochasticNewtonMethod#getNumberOfIterations()}
   *   <li>{@link StochasticNewtonMethod#isDone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StochasticNewtonMethod.<init>(RandomVariable, MethodForAccuracy)",
    "double StochasticNewtonMethod.getAccuracy()",
    "RandomVariable StochasticNewtonMethod.getBestPoint()",
    "RandomVariable StochasticNewtonMethod.getNextPoint()",
    "int StochasticNewtonMethod.getNumberOfIterations()",
    "boolean StochasticNewtonMethod.isDone()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromDoubleArray guess = new RandomVariableFromDoubleArray(10.0d);

    // Act
    StochasticNewtonMethod actualStochasticNewtonMethod =
        new StochasticNewtonMethod(guess, MethodForAccuracy.AVERAGE);
    double actualAccuracy = actualStochasticNewtonMethod.getAccuracy();
    RandomVariable actualBestPoint = actualStochasticNewtonMethod.getBestPoint();
    RandomVariable actualNextPoint = actualStochasticNewtonMethod.getNextPoint();
    int actualNumberOfIterations = actualStochasticNewtonMethod.getNumberOfIterations();

    // Assert
    assertEquals(0, actualNumberOfIterations);
    assertFalse(actualStochasticNewtonMethod.isDone());
    assertEquals(Double.MAX_VALUE, actualAccuracy, 0.0);
    assertSame(guess, actualBestPoint);
    assertSame(guess, actualNextPoint);
  }

  /**
   * Test {@link StochasticNewtonMethod#setValueAndDerivative(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link StochasticNewtonMethod#setValueAndDerivative(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StochasticNewtonMethod.setValueAndDerivative(RandomVariable, RandomVariable)"
  })
  public void testSetValueAndDerivative() {
    // Arrange
    StochasticNewtonMethod stochasticNewtonMethod =
        new StochasticNewtonMethod(new RandomVariableFromDoubleArray(10.0d), MethodForAccuracy.MAX);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.abs()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            stochasticNewtonMethod.setValueAndDerivative(
                value, new RandomVariableFromDoubleArray(10.0d)));
    verify(value).abs();
  }

  /**
   * Test {@link StochasticNewtonMethod#setValueAndDerivative(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link StochasticNewtonMethod#setValueAndDerivative(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StochasticNewtonMethod.setValueAndDerivative(RandomVariable, RandomVariable)"
  })
  public void testSetValueAndDerivative2() {
    // Arrange
    StochasticNewtonMethod stochasticNewtonMethod =
        new StochasticNewtonMethod(new RandomVariableFromDoubleArray(10.0d), MethodForAccuracy.MIN);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.abs()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            stochasticNewtonMethod.setValueAndDerivative(
                value, new RandomVariableFromDoubleArray(10.0d)));
    verify(value).abs();
  }

  /**
   * Test {@link StochasticNewtonMethod#setValueAndDerivative(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link StochasticNewtonMethod#setValueAndDerivative(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StochasticNewtonMethod.setValueAndDerivative(RandomVariable, RandomVariable)"
  })
  public void testSetValueAndDerivative3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.addRatio(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    when(randomVariable.abs()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(methodForAccuracy.ordinal()).thenReturn(1);

    // Act
    stochasticNewtonMethod.setValueAndDerivative(randomVariable, randomVariable);

    // Assert
    verify(methodForAccuracy).ordinal();
    verify(randomVariable).abs();
    verify(randomVariable).addRatio(isA(RandomVariable.class), isA(RandomVariable.class));
    RandomVariable nextPoint = stochasticNewtonMethod.getNextPoint();
    assertTrue(nextPoint instanceof RandomVariableFromDoubleArray);
    assertEquals(1, stochasticNewtonMethod.getNumberOfIterations());
    assertEquals(10.0d, stochasticNewtonMethod.getAccuracy(), 0.0);
    assertSame(randomVariableFromDoubleArray, nextPoint);
  }
}
