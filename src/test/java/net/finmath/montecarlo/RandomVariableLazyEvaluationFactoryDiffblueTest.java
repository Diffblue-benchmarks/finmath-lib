package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableLazyEvaluationFactoryDiffblueTest {
  /**
   * Test {@link RandomVariableLazyEvaluationFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluationFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluationFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableLazyEvaluationFactory().createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualCreateRandomVariableResult.getOperator());
    assertEquals(0, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(0.0d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertTrue(actualCreateRandomVariableResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d}, actualCreateRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableLazyEvaluationFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link RandomVariableLazyEvaluationFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableLazyEvaluationFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableLazyEvaluationFactory()
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualCreateRandomVariableResult.variance() instanceof RandomVariableLazyEvaluation);
    assertEquals(0, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(0.5d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(2.375d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(22.5625d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(30.083333333333332d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(4, actualCreateRandomVariableResult.size());
    assertEquals(4.75d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertFalse(actualCreateRandomVariableResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }
}
