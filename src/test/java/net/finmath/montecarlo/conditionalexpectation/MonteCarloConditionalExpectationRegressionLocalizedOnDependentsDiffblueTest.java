package net.finmath.montecarlo.conditionalexpectation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctions;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctionsGiven;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloConditionalExpectationRegressionLocalizedOnDependentsDiffblueTest {
  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[])"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] basisFunctions = new RandomVariable[] {randomVariableFromDoubleArray};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(basisFunctions);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions2 = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions2[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions2.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
    assertSame(
        basisFunctionsEstimator,
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[])"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents2() {
    // Arrange
    RandomVariable[] basisFunctions = new RandomVariable[] {null};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(basisFunctions);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(0, basisFunctionsEstimator.getBasisFunctions().length);
    assertSame(
        basisFunctionsEstimator,
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * double)}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[], double)"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {randomVariableFromDoubleArray};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(
                basisFunctionsEstimator, 10.0d);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator2.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
    assertSame(
        basisFunctionsEstimator2,
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * double)}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[], double)"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents4() {
    // Arrange
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {null};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(
                basisFunctionsEstimator, 10.0d);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(0, basisFunctionsEstimator2.getBasisFunctions().length);
    assertSame(
        basisFunctionsEstimator2,
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[], RandomVariable[])"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents5() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {randomVariableFromDoubleArray};
    RandomVariable[] basisFunctionsPredictor =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(
                basisFunctionsEstimator, basisFunctionsPredictor);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator2.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    RegressionBasisFunctions basisFunctionsPredictor2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions2 = basisFunctionsPredictor2.getBasisFunctions();
    assertTrue(basisFunctions2[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertEquals(1, basisFunctions2.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[], RandomVariable[])"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents6() {
    // Arrange
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {null};
    RandomVariable[] basisFunctionsPredictor =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(
                basisFunctionsEstimator, basisFunctionsPredictor);

    // Assert
    RegressionBasisFunctions basisFunctionsPredictor2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor2.getBasisFunctions();
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
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(0, basisFunctionsEstimator2.getBasisFunctions().length);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[], double)}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[], RandomVariable[], double)"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents7() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {randomVariableFromDoubleArray};
    RandomVariable[] basisFunctionsPredictor =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(
                basisFunctionsEstimator, basisFunctionsPredictor, 10.0d);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator2.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    RegressionBasisFunctions basisFunctionsPredictor2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions2 = basisFunctionsPredictor2.getBasisFunctions();
    assertTrue(basisFunctions2[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertEquals(1, basisFunctions2.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[], double)}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegressionLocalizedOnDependents#MonteCarloConditionalExpectationRegressionLocalizedOnDependents(RandomVariable[],
   * RandomVariable[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegressionLocalizedOnDependents.<init>(RandomVariable[], RandomVariable[], double)"
  })
  public void testNewMonteCarloConditionalExpectationRegressionLocalizedOnDependents8() {
    // Arrange
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {null};
    RandomVariable[] basisFunctionsPredictor =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    MonteCarloConditionalExpectationRegressionLocalizedOnDependents
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents =
            new MonteCarloConditionalExpectationRegressionLocalizedOnDependents(
                basisFunctionsEstimator, basisFunctionsPredictor, 10.0d);

    // Assert
    RegressionBasisFunctions basisFunctionsPredictor2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsPredictor();
    RandomVariable[] basisFunctions = basisFunctionsPredictor2.getBasisFunctions();
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
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegressionLocalizedOnDependents
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(0, basisFunctionsEstimator2.getBasisFunctions().length);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }
}
