package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractRandomVariableFactoryDiffblueTest {
  /**
   * Test {@link AbstractRandomVariableFactory#createRandomVariable(double)} with {@code value}.
   *
   * <p>Method under test: {@link AbstractRandomVariableFactory#createRandomVariable(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractRandomVariableFactory.createRandomVariable(double)"})
  public void testCreateRandomVariableWithValue() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableResult =
        new RandomVariableFloatFactory().createRandomVariable(10.0d);

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
    assertEquals(0.0d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableResult.getTypePriority());
    assertEquals(1, actualCreateRandomVariableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertTrue(actualCreateRandomVariableResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualCreateRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractRandomVariableFactory#createRandomVariableArray(double[])}.
   *
   * <p>Method under test: {@link AbstractRandomVariableFactory#createRandomVariableArray(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractRandomVariableFactory.createRandomVariableArray(double[])"
  })
  public void testCreateRandomVariableArray() {
    // Arrange and Act
    RandomVariable[] actualCreateRandomVariableArrayResult =
        new RandomVariableFloatFactory()
            .createRandomVariableArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCreateRandomVariableArrayResult[0] instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableArrayResult[1] instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableArrayResult[2] instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableArrayResult[3] instanceof RandomVariableFromFloatArray);
    assertEquals(4, actualCreateRandomVariableArrayResult.length);
  }

  /**
   * Test {@link AbstractRandomVariableFactory#createRandomVariableMatrix(double[][])}.
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableFactory#createRandomVariableMatrix(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[][] AbstractRandomVariableFactory.createRandomVariableMatrix(double[][])"
  })
  public void testCreateRandomVariableMatrix() {
    // Arrange and Act
    RandomVariable[][] actualCreateRandomVariableMatrixResult =
        new RandomVariableFloatFactory()
            .createRandomVariableMatrix(new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}});

    // Assert
    RandomVariable[] randomVariableArray = actualCreateRandomVariableMatrixResult[0];
    assertTrue(randomVariableArray[0] instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariableArray[1] instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariableArray[2] instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariableArray[3] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualCreateRandomVariableMatrixResult.length);
    assertEquals(4, randomVariableArray.length);
  }
}
