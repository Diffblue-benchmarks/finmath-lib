package net.finmath.montecarlo.automaticdifferentiation.forward;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.RandomVariableDifferentiable;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableDifferentiableADFactoryDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiableADFactory#RandomVariableDifferentiableADFactory()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableADFactory#RandomVariableDifferentiableADFactory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableADFactory.<init>()"})
  public void testNewRandomVariableDifferentiableADFactory() {
    // Arrange and Act
    RandomVariableDifferentiableADFactory actualRandomVariableDifferentiableADFactory =
        new RandomVariableDifferentiableADFactory();

    // Assert
    assertTrue(
        actualRandomVariableDifferentiableADFactory.createRandomVariable(null)
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualRandomVariableDifferentiableADFactory.createRandomVariable(10.0d)
            instanceof RandomVariableDifferentiableAD);
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory(new RandomVariableDifferentiableADFactory())
            .createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue2() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory(new RandomVariableDifferentiableAADFactory())
            .createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue_thenNaNReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory(
                new RandomVariableDifferentiableAADPathwiseFactory())
            .createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableResult.getValues()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualCreateRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue_thenValuesReturnScalar() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory().createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.getValues() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory()
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues2() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory(new RandomVariableFloatFactory())
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues3() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory(new RandomVariableDifferentiableADFactory())
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableADFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues4() {
    // Arrange and Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        new RandomVariableDifferentiableADFactory(new RandomVariableDifferentiableAADFactory())
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(
        ((RandomVariableDifferentiableAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAD);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }
}
