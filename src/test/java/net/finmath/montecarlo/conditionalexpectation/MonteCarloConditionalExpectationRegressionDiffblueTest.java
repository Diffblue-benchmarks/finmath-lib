package net.finmath.montecarlo.conditionalexpectation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

public class MonteCarloConditionalExpectationRegressionDiffblueTest {
  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression()}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloConditionalExpectationRegression.<init>()"})
  public void testNewMonteCarloConditionalExpectationRegression() {
    // Arrange and Act
    MonteCarloConditionalExpectationRegression actualMonteCarloConditionalExpectationRegression =
        new MonteCarloConditionalExpectationRegression();

    // Assert
    assertNull(actualMonteCarloConditionalExpectationRegression.getBasisFunctionsEstimator());
    assertNull(actualMonteCarloConditionalExpectationRegression.getBasisFunctionsPredictor());
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloConditionalExpectationRegression.<init>(RandomVariable[])"})
  public void testNewMonteCarloConditionalExpectationRegression2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] basisFunctions = new RandomVariable[] {randomVariableFromDoubleArray};

    // Act
    MonteCarloConditionalExpectationRegression actualMonteCarloConditionalExpectationRegression =
        new MonteCarloConditionalExpectationRegression(basisFunctions);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator =
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions2 = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions2[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions2.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
    assertSame(
        basisFunctionsEstimator,
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsPredictor());
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegression.<init>(RandomVariable[], RandomVariable[])"
  })
  public void testNewMonteCarloConditionalExpectationRegression3() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {randomVariableFromDoubleArray};
    RandomVariable[] basisFunctionsPredictor =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    MonteCarloConditionalExpectationRegression actualMonteCarloConditionalExpectationRegression =
        new MonteCarloConditionalExpectationRegression(
            basisFunctionsEstimator, basisFunctionsPredictor);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator2 =
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator2.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    RegressionBasisFunctions basisFunctionsPredictor2 =
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsPredictor();
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
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloConditionalExpectationRegression.<init>(RandomVariable[], RandomVariable[])"
  })
  public void testNewMonteCarloConditionalExpectationRegression4() {
    // Arrange
    RandomVariable[] basisFunctionsEstimator = new RandomVariable[] {null};
    RandomVariable[] basisFunctionsPredictor =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    MonteCarloConditionalExpectationRegression actualMonteCarloConditionalExpectationRegression =
        new MonteCarloConditionalExpectationRegression(
            basisFunctionsEstimator, basisFunctionsPredictor);

    // Assert
    RegressionBasisFunctions basisFunctionsPredictor2 =
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsPredictor();
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
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator2 instanceof RegressionBasisFunctionsGiven);
    assertTrue(basisFunctionsPredictor2 instanceof RegressionBasisFunctionsGiven);
    assertEquals(0, basisFunctionsEstimator2.getBasisFunctions().length);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloConditionalExpectationRegression#MonteCarloConditionalExpectationRegression(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloConditionalExpectationRegression.<init>(RandomVariable[])"})
  public void testNewMonteCarloConditionalExpectationRegression_thenReturnArrayLengthIsZero() {
    // Arrange
    RandomVariable[] basisFunctions = new RandomVariable[] {null};

    // Act
    MonteCarloConditionalExpectationRegression actualMonteCarloConditionalExpectationRegression =
        new MonteCarloConditionalExpectationRegression(basisFunctions);

    // Assert
    RegressionBasisFunctions basisFunctionsEstimator =
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(0, basisFunctionsEstimator.getBasisFunctions().length);
    assertSame(
        basisFunctionsEstimator,
        actualMonteCarloConditionalExpectationRegression.getBasisFunctionsPredictor());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MonteCarloConditionalExpectationRegression#getBasisFunctionsEstimator()}
   *   <li>{@link MonteCarloConditionalExpectationRegression#getBasisFunctionsPredictor()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctions MonteCarloConditionalExpectationRegression.getBasisFunctionsEstimator()",
    "RegressionBasisFunctions MonteCarloConditionalExpectationRegression.getBasisFunctionsPredictor()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MonteCarloConditionalExpectationRegression monteCarloConditionalExpectationRegression =
        new MonteCarloConditionalExpectationRegression();

    // Act
    RegressionBasisFunctions actualBasisFunctionsEstimator =
        monteCarloConditionalExpectationRegression.getBasisFunctionsEstimator();

    // Assert
    assertNull(actualBasisFunctionsEstimator);
    assertNull(monteCarloConditionalExpectationRegression.getBasisFunctionsPredictor());
  }

  /**
   * Test RegressionBasisFunctionsGiven getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RegressionBasisFunctionsGiven#RegressionBasisFunctionsGiven(RandomVariable[])}
   *   <li>{@link RegressionBasisFunctionsGiven#getBasisFunctions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RegressionBasisFunctionsGiven.<init>(RandomVariable[])",
    "RandomVariable[] RegressionBasisFunctionsGiven.getBasisFunctions()"
  })
  public void testRegressionBasisFunctionsGivenGettersAndSetters() {
    // Arrange
    RandomVariable[] basisFunctions =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    RegressionBasisFunctionsGiven actualRegressionBasisFunctionsGiven =
        new RegressionBasisFunctionsGiven(basisFunctions);

    // Assert
    assertSame(basisFunctions, actualRegressionBasisFunctionsGiven.getBasisFunctions());
  }
}
