package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableFromArrayFactoryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableFromArrayFactory#RandomVariableFromArrayFactory()}
   *   <li>{@link RandomVariableFromArrayFactory#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromArrayFactory.<init>()",
    "void RandomVariableFromArrayFactory.<init>(boolean)",
    "java.lang.String RandomVariableFromArrayFactory.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "RandomVariableFromArrayFactory [isUseDoublePrecisionFloatingPointImplementation=true]",
        new RandomVariableFromArrayFactory().toString());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableFromArrayFactory#RandomVariableFromArrayFactory(boolean)}
   *   <li>{@link RandomVariableFromArrayFactory#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableFromArrayFactory.<init>()",
    "void RandomVariableFromArrayFactory.<init>(boolean)",
    "java.lang.String RandomVariableFromArrayFactory.toString()"
  })
  public void testGettersAndSetters_whenTrue() {
    // Arrange, Act and Assert
    assertEquals(
        "RandomVariableFromArrayFactory [isUseDoublePrecisionFloatingPointImplementation=true]",
        new RandomVariableFromArrayFactory(true).toString());
  }

  /**
   * Test {@link RandomVariableFromArrayFactory#createRandomVariable(double, double)} with {@code
   * time}, {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromArrayFactory#createRandomVariable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromArrayFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableFromArrayFactory(true).createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.abs() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.cos() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.exp() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.invert() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.sin() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.sqrt() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.squared() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.variance() instanceof Scalar);
    assertNull(actualCreateRandomVariableResult.getRealizations());
    assertNull(actualCreateRandomVariableResult.getOperator());
    assertNull(actualCreateRandomVariableResult.getRealizationsStream());
    assertEquals(0, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(0.0d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertTrue(actualCreateRandomVariableResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCreateRandomVariableResult.expectation();
    assertSame(actualCreateRandomVariableResult, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableFromArrayFactory#createRandomVariable(double, double[])} with {@code
   * time}, {@code values}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromArrayFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromArrayFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues_thenReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableFromArrayFactory(true)
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getTypePriority());
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

  /**
   * Test {@link RandomVariableFromArrayFactory#createRandomVariable(double, double[])} with {@code
   * time}, {@code values}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableFromArrayFactory#createRandomVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFromArrayFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues_thenReturnRandomVariableFromFloatArray() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableFromArrayFactory(false)
            .createRandomVariable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.5d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getTypePriority());
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

  /**
   * Test {@link RandomVariableFromArrayFactory#createRandomVariable(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableFromArrayFactory#createRandomVariable(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFromArrayFactory.createRandomVariable(double)"})
  public void testCreateRandomVariableWithValue() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableFromArrayFactory(true).createRandomVariable(10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.abs() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.cos() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.exp() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.invert() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.sin() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.sqrt() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.squared() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.variance() instanceof Scalar);
    assertNull(actualCreateRandomVariableResult.getRealizations());
    assertNull(actualCreateRandomVariableResult.getOperator());
    assertNull(actualCreateRandomVariableResult.getRealizationsStream());
    assertEquals(0, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(0.0d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertTrue(actualCreateRandomVariableResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCreateRandomVariableResult.expectation();
    assertSame(actualCreateRandomVariableResult, actualExpectationResult);
  }
}
