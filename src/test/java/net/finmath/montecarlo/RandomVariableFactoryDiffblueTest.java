package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableFactoryDiffblueTest {
  /**
   * Test {@link RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFactory.getRandomVariableOrDefault(RandomVariableFactory, Object, RandomVariable)"
  })
  public void testGetRandomVariableOrDefault_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualRandomVariableOrDefault =
        RandomVariableFactory.getRandomVariableOrDefault(
            null, null, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualRandomVariableOrDefault.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualRandomVariableOrDefault.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableOrDefault instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualRandomVariableOrDefault.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableOrDefault.getTypePriority());
    assertEquals(1, actualRandomVariableOrDefault.size());
    assertEquals(10.0d, actualRandomVariableOrDefault.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableOrDefault.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableOrDefault.getMin(), 0.0);
    assertTrue(actualRandomVariableOrDefault.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualRandomVariableOrDefault.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualRandomVariableOrDefault.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFactory.getRandomVariableOrDefault(RandomVariableFactory, Object, RandomVariable)"
  })
  public void testGetRandomVariableOrDefault_thenAbsReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    // Act
    RandomVariable actualRandomVariableOrDefault =
        RandomVariableFactory.getRandomVariableOrDefault(
            randomVariableFactory, 42, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualRandomVariableOrDefault.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableOrDefault instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableOrDefault.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableOrDefault.getTypePriority());
    assertEquals(1, actualRandomVariableOrDefault.size());
    assertEquals(42.0d, actualRandomVariableOrDefault.getAverage(), 0.0);
    assertEquals(42.0d, actualRandomVariableOrDefault.getMax(), 0.0);
    assertEquals(42.0d, actualRandomVariableOrDefault.getMin(), 0.0);
    assertTrue(actualRandomVariableOrDefault.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualRandomVariableOrDefault.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {42.0d}, actualRandomVariableOrDefault.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFactory.getRandomVariableOrDefault(RandomVariableFactory, Object, RandomVariable)"
  })
  public void testGetRandomVariableOrDefault_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualRandomVariableOrDefault =
        RandomVariableFactory.getRandomVariableOrDefault(
            randomVariableFactory, ofResult, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualRandomVariableOrDefault.abs() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.cos() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.exp() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.invert() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.sin() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.squared() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault.variance() instanceof Scalar);
    assertTrue(actualRandomVariableOrDefault instanceof Scalar);
    assertNull(actualRandomVariableOrDefault.getRealizations());
    assertNull(actualRandomVariableOrDefault.getOperator());
    assertNull(actualRandomVariableOrDefault.getRealizationsStream());
    assertEquals(0, actualRandomVariableOrDefault.getTypePriority());
    assertEquals(0.0d, actualRandomVariableOrDefault.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableOrDefault.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableOrDefault.size());
    assertEquals(10.0d, actualRandomVariableOrDefault.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableOrDefault.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableOrDefault.getMin(), 0.0);
    assertTrue(actualRandomVariableOrDefault.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualRandomVariableOrDefault.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableOrDefault.expectation();
    assertSame(actualRandomVariableOrDefault, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableFactory#getRandomVariableOrDefault(RandomVariableFactory, Object,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableFactory.getRandomVariableOrDefault(RandomVariableFactory, Object, RandomVariable)"
  })
  public void testGetRandomVariableOrDefault_whenValue_thenThrowIllegalArgumentException() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            RandomVariableFactory.getRandomVariableOrDefault(
                randomVariableFactory, "Value", new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableFactory#createRandomVariable(double[])} with {@code double[]}.
   *
   * <p>Method under test: {@link RandomVariableFactory#createRandomVariable(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableFactory.createRandomVariable(double[])"})
  public void testCreateRandomVariableWithDouble() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableFloatFactory()
            .createRandomVariable(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

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
    assertEquals(0.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertEquals(0.5d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getTypePriority());
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
