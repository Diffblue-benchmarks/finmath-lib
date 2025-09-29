package net.finmath.montecarlo.conditionalexpectation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctions;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctionsGiven;
import net.finmath.stochastic.ConditionalExpectationEstimator;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactoryDiffblueTest {
  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(1, basisFunctionsEstimator.getBasisFunctions().length);
    assertEquals(1, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator2() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(Double.NaN));

    // Assert
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(1, basisFunctionsEstimator.getBasisFunctions().length);
    assertEquals(1, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator3() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3).mult(isA(RandomVariable.class));
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(1, basisFunctionsEstimator.getBasisFunctions().length);
    assertEquals(1, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator4() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {randomVariableAAD2},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.sub(anyDouble())).thenReturn(randomVariableAAD3);
    RandomVariableAAD randomVariableAAD5 = mock(RandomVariableAAD.class);
    when(randomVariableAAD5.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD6 = mock(RandomVariableAAD.class);
    when(randomVariableAAD6.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD5);
    when(randomVariableAAD6.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD6.squared()).thenReturn(randomVariableAAD4);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD6);

    // Assert
    verify(randomVariableAAD3).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD6).getStandardDeviation();
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD6).mult(isA(RandomVariable.class));
    verify(randomVariableAAD5).mult(isA(RandomVariable.class));
    verify(randomVariableAAD6).squared();
    verify(randomVariableAAD4).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
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
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator5() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {
                  randomVariableFromDoubleArray,
                  randomVariableFromDoubleArray2,
                  new RandomVariableFromDoubleArray(10.0d)
                },
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(3, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator6() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {
                  randomVariableFromDoubleArray,
                  randomVariableFromDoubleArray2,
                  new RandomVariableFromDoubleArray(10.0d)
                },
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(2.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(3, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator7() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {
                  randomVariableFromDoubleArray,
                  randomVariableFromDoubleArray2,
                  new RandomVariableFromDoubleArray(10.0d)
                },
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(3, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator8() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {
                  randomVariableFromDoubleArray,
                  randomVariableFromDoubleArray2,
                  new RandomVariableFromDoubleArray(10.0d)
                },
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(3, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator_thenReturnArrayLengthIsTwo() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {
                  randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
                },
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(2, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator_thenReturnArrayLengthIsTwo2() {
    // Arrange
    MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory =
            new MonteCarloConditionalExpectationLocalizedOnDependentRegressionFactory(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLocalizedOnDependentRegressionFactory
            .getConditionalExpectationEstimator(
                new RandomVariable[] {
                  randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
                },
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sub(anyDouble())).thenReturn(randomVariableAAD);
    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariableAAD4.squared()).thenReturn(randomVariableAAD2);
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD4);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(randomVariableAAD4).getStandardDeviation();
    verify(randomVariableAAD4).mult(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4).squared();
    verify(randomVariableAAD2).sub(10000.0d);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegressionLocalizedOnDependents)
                actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegressionLocalizedOnDependents);
    assertEquals(2, basisFunctions.length);
  }
}
