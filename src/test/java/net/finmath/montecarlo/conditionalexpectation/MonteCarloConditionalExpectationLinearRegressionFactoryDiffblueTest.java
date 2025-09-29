package net.finmath.montecarlo.conditionalexpectation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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

public class MonteCarloConditionalExpectationLinearRegressionFactoryDiffblueTest {
  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertEquals(1, basisFunctionsPredictor.getBasisFunctions().length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator2() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {randomVariableAAD},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD2);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
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
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator3() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctionsPredictor.getBasisFunctions().length);
    assertEquals(2, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator4() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(3, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator5() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctionsPredictor.getBasisFunctions().length);
    assertEquals(2, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator6() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(0.5d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctions.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator7() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {null, new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertEquals(1, basisFunctionsPredictor.getBasisFunctions().length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator8() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {ofResult, new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctions.length);
    assertSame(ofResult, randomVariable);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator9() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {randomVariableFromDoubleArray2, randomVariableFromDoubleArray3});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[1];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctionsEstimator.getBasisFunctions().length);
    assertEquals(2, basisFunctions.length);
    assertSame(randomVariableFromDoubleArray3, randomVariable);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator10() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(3, basisFunctions.length);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator11() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(0.5d);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[1];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(3, basisFunctions.length);
    assertSame(randomVariableFromDoubleArray2, randomVariable);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationLinearRegressionFactory#getConditionalExpectationEstimator(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator MonteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(RandomVariable[], RandomVariable[])"
  })
  public void testGetConditionalExpectationEstimator_thenReturnArrayLengthIsOne() {
    // Arrange
    MonteCarloConditionalExpectationLinearRegressionFactory
        monteCarloConditionalExpectationLinearRegressionFactory =
            new MonteCarloConditionalExpectationLinearRegressionFactory();

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        monteCarloConditionalExpectationLinearRegressionFactory.getConditionalExpectationEstimator(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(10.0d));

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertEquals(1, basisFunctionsPredictor.getBasisFunctions().length);
  }
}
