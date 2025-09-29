package net.finmath.montecarlo.automaticdifferentiation.backward.alternative;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.RandomVariableDifferentiable;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableDifferentiableAADPathwiseFactoryDiffblueTest {
  /**
   * Test {@link
   * RandomVariableDifferentiableAADPathwiseFactory#RandomVariableDifferentiableAADPathwiseFactory()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwiseFactory#RandomVariableDifferentiableAADPathwiseFactory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADPathwiseFactory.<init>()"})
  public void testNewRandomVariableDifferentiableAADPathwiseFactory() {
    // Arrange and Act
    RandomVariableDifferentiableAADPathwiseFactory
        actualRandomVariableDifferentiableAADPathwiseFactory =
            new RandomVariableDifferentiableAADPathwiseFactory();

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableAADPathwiseFactory.createRandomVariable(null)
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableDifferentiableAADPathwiseFactory.createRandomVariable(10.0d)
            instanceof RandomVariableDifferentiableAADPathwise);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwiseFactory#createRandomVariable(double,
   * double)} with {@code time}, {@code value}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwiseFactory#createRandomVariable(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwiseFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableAADPathwiseFactory().createRandomVariable(10.0d, 10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualCreateRandomVariableResult.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualCreateRandomVariableResult.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualCreateRandomVariableResult.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertEquals(1, actualCreateRandomVariableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(3, actualCreateRandomVariableResult.getTypePriority());
    assertTrue(actualCreateRandomVariableResult.isDeterministic());
    assertSame(randomVariable, actualCreateRandomVariableResult.getValues());
    assertArrayEquals(
        new double[] {10.0d}, actualCreateRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADPathwiseFactory#createRandomVariable(double,
   * double[])} with {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADPathwiseFactory#createRandomVariable(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADPathwiseFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableAADPathwiseFactory()
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualCreateRandomVariableResult.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualCreateRandomVariableResult.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualCreateRandomVariableResult.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.5d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(2.375d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(22.5625d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(3, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(30.083333333333332d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(4, actualCreateRandomVariableResult.size());
    assertEquals(4.75d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertFalse(actualCreateRandomVariableResult.isDeterministic());
    assertSame(randomVariable, actualCreateRandomVariableResult.getValues());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }
}
