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
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JumpProcessIncrementsDiffblueTest {
  /**
   * Test {@link JumpProcessIncrements#JumpProcessIncrements(TimeDiscretization, double[], int,
   * int)}.
   *
   * <p>Method under test: {@link JumpProcessIncrements#JumpProcessIncrements(TimeDiscretization,
   * double[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JumpProcessIncrements.<init>(TimeDiscretization, double[], int, int)"})
  public void testNewJumpProcessIncrements() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    JumpProcessIncrements actualJumpProcessIncrements =
        new JumpProcessIncrements(
            timeDiscretization, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);

    // Assert
    assertEquals(10, actualJumpProcessIncrements.getNumberOfPaths());
    assertEquals(4, actualJumpProcessIncrements.getNumberOfFactors());
    assertEquals(42, actualJumpProcessIncrements.getSeed());
    assertSame(timeDiscretization, actualJumpProcessIncrements.getTimeDiscretization());
  }

  /**
   * Test {@link JumpProcessIncrements#JumpProcessIncrements(TimeDiscretization, double[], int, int,
   * RandomVariableFactory)}.
   *
   * <p>Method under test: {@link JumpProcessIncrements#JumpProcessIncrements(TimeDiscretization,
   * double[], int, int, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JumpProcessIncrements.<init>(TimeDiscretization, double[], int, int, RandomVariableFactory)"
  })
  public void testNewJumpProcessIncrements2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    JumpProcessIncrements actualJumpProcessIncrements =
        new JumpProcessIncrements(
            timeDiscretization,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10,
            42,
            new RandomVariableFloatFactory());

    // Assert
    assertEquals(10, actualJumpProcessIncrements.getNumberOfPaths());
    assertEquals(4, actualJumpProcessIncrements.getNumberOfFactors());
    assertEquals(42, actualJumpProcessIncrements.getSeed());
    assertSame(timeDiscretization, actualJumpProcessIncrements.getTimeDiscretization());
  }

  /**
   * Test {@link JumpProcessIncrements#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link JumpProcessIncrements#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JumpProcessIncrements JumpProcessIncrements.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    JumpProcessIncrements jumpProcessIncrements =
        new JumpProcessIncrements(
            timeDiscretization, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);

    // Act
    JumpProcessIncrements actualCloneWithModifiedSeed =
        jumpProcessIncrements.getCloneWithModifiedSeed(42);

    // Assert
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(4, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(42, actualCloneWithModifiedSeed.getSeed());
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link JumpProcessIncrements#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * JumpProcessIncrements#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JumpProcessIncrements JumpProcessIncrements.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    JumpProcessIncrements jumpProcessIncrements =
        new JumpProcessIncrements(
            new TenorFromArray(10.0d, 10, 0.5d), new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    JumpProcessIncrements actualCloneWithModifiedTimeDiscretization =
        jumpProcessIncrements.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(4, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(42, actualCloneWithModifiedTimeDiscretization.getSeed());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link JumpProcessIncrements#getIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link JumpProcessIncrements#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable JumpProcessIncrements.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    JumpProcessIncrements jumpProcessIncrements =
        new JumpProcessIncrements(
            new TenorFromArray(10.0d, 10, 0.5d), new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);

    // Act
    RandomVariable actualIncrement = jumpProcessIncrements.getIncrement(1, 3);

    // Assert
    assertTrue(actualIncrement instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.09000000000000001d, actualIncrement.getVariance(), 0.0);
    assertEquals(0.09486832980505139d, actualIncrement.getStandardError(), 0.0);
    assertEquals(0.0d, actualIncrement.getMin(), 0.0);
    assertEquals(0.10000000000000002d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(0.1d, actualIncrement.getAverage(), 0.0);
    assertEquals(0.30000000000000004d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(1.0d, actualIncrement.getMax(), 0.0);
    assertEquals(10, actualIncrement.size());
    assertEquals(11.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link JumpProcessIncrements#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link JumpProcessIncrements#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JumpProcessIncrements.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    JumpProcessIncrements jumpProcessIncrements =
        new JumpProcessIncrements(
            new TenorFromArray(10.0d, 10, 0.5d), new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);

    // Act and Assert
    assertEquals(4, jumpProcessIncrements.getNumberOfFactors());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JumpProcessIncrements#getNumberOfPaths()}
   *   <li>{@link JumpProcessIncrements#getSeed()}
   *   <li>{@link JumpProcessIncrements#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int JumpProcessIncrements.getNumberOfPaths()",
    "int JumpProcessIncrements.getSeed()",
    "TimeDiscretization JumpProcessIncrements.getTimeDiscretization()",
    "java.lang.String JumpProcessIncrements.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    JumpProcessIncrements jumpProcessIncrements =
        new JumpProcessIncrements(
            timeDiscretization, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);

    // Act
    int actualNumberOfPaths = jumpProcessIncrements.getNumberOfPaths();
    int actualSeed = jumpProcessIncrements.getSeed();

    // Assert
    assertEquals(10, actualNumberOfPaths);
    assertEquals(42, actualSeed);
    assertSame(timeDiscretization, jumpProcessIncrements.getTimeDiscretization());
  }

  /**
   * Test {@link JumpProcessIncrements#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link JumpProcessIncrements#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable JumpProcessIncrements.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    JumpProcessIncrements jumpProcessIncrements =
        new JumpProcessIncrements(
            new TenorFromArray(10.0d, 10, 0.5d), new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10, 42);

    // Act
    RandomVariable actualRandomVariableForConstant =
        jumpProcessIncrements.getRandomVariableForConstant(10.0d);

    // Assert
    assertTrue(actualRandomVariableForConstant instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.abs() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.cos() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.exp() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.invert() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sin() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.squared() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.variance() instanceof Scalar);
    assertNull(actualRandomVariableForConstant.getRealizations());
    assertNull(actualRandomVariableForConstant.getOperator());
    assertNull(actualRandomVariableForConstant.getRealizationsStream());
    assertEquals(0, actualRandomVariableForConstant.getTypePriority());
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }
}
