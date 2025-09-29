package net.finmath.montecarlo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.randomnumbers.HighEntropyRandomNumberGenerator;
import net.finmath.randomnumbers.RandomNumberGenerator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BrownianMotionFromRandomNumberGeneratorDiffblueTest {
  /**
   * Test {@link
   * BrownianMotionFromRandomNumberGenerator#BrownianMotionFromRandomNumberGenerator(TimeDiscretization,
   * int, int, RandomNumberGenerator)}.
   *
   * <ul>
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromRandomNumberGenerator#BrownianMotionFromRandomNumberGenerator(TimeDiscretization,
   * int, int, RandomNumberGenerator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianMotionFromRandomNumberGenerator.<init>(TimeDiscretization, int, int, RandomNumberGenerator)"
  })
  public void testNewBrownianMotionFromRandomNumberGenerator_thenReturnNumberOfPathsIsTen() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Act
    BrownianMotionFromRandomNumberGenerator actualBrownianMotionFromRandomNumberGenerator =
        new BrownianMotionFromRandomNumberGenerator(
            timeDiscretization, 3, 10, new HighEntropyRandomNumberGenerator());

    // Assert
    assertEquals(10, actualBrownianMotionFromRandomNumberGenerator.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionFromRandomNumberGenerator.getNumberOfFactors());
    assertSame(
        timeDiscretization, actualBrownianMotionFromRandomNumberGenerator.getTimeDiscretization());
  }

  /**
   * Test {@link
   * BrownianMotionFromRandomNumberGenerator#BrownianMotionFromRandomNumberGenerator(TimeDiscretization,
   * int, int, RandomNumberGenerator, RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromRandomNumberGenerator#BrownianMotionFromRandomNumberGenerator(TimeDiscretization,
   * int, int, RandomNumberGenerator, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianMotionFromRandomNumberGenerator.<init>(TimeDiscretization, int, int, RandomNumberGenerator, RandomVariableFactory)"
  })
  public void testNewBrownianMotionFromRandomNumberGenerator_thenReturnNumberOfPathsIsTen2() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    HighEntropyRandomNumberGenerator randomNumberGenerator = new HighEntropyRandomNumberGenerator();

    // Act
    BrownianMotionFromRandomNumberGenerator actualBrownianMotionFromRandomNumberGenerator =
        new BrownianMotionFromRandomNumberGenerator(
            timeDiscretization, 3, 10, randomNumberGenerator, new RandomVariableFloatFactory());

    // Assert
    assertEquals(10, actualBrownianMotionFromRandomNumberGenerator.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionFromRandomNumberGenerator.getNumberOfFactors());
    assertSame(
        timeDiscretization, actualBrownianMotionFromRandomNumberGenerator.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionFromRandomNumberGenerator#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return {@link BrownianMotionFromRandomNumberGenerator}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromRandomNumberGenerator#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionFromRandomNumberGenerator.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnBrownianMotionFromRandomNumberGenerator() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 0, 0.5d);
    BrownianMotionFromRandomNumberGenerator brownianMotionFromRandomNumberGenerator =
        new BrownianMotionFromRandomNumberGenerator(
            timeDiscretization, 3, 10, new HighEntropyRandomNumberGenerator());

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        brownianMotionFromRandomNumberGenerator.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionFromRandomNumberGenerator);
    assertEquals(brownianMotionFromRandomNumberGenerator, actualCloneWithModifiedSeed);
  }

  /**
   * Test {@link
   * BrownianMotionFromRandomNumberGenerator#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionFromRandomNumberGenerator#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionFromRandomNumberGenerator.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 0, 0.5d);
    BrownianMotionFromRandomNumberGenerator brownianMotionFromRandomNumberGenerator =
        new BrownianMotionFromRandomNumberGenerator(
            timeDiscretization, 3, 10, new HighEntropyRandomNumberGenerator());

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianMotionFromRandomNumberGenerator.getCloneWithModifiedTimeDiscretization(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    // Assert
    assertTrue(
        actualCloneWithModifiedTimeDiscretization
            instanceof BrownianMotionFromRandomNumberGenerator);
    assertEquals(
        brownianMotionFromRandomNumberGenerator, actualCloneWithModifiedTimeDiscretization);
  }

  /**
   * Test {@link BrownianMotionFromRandomNumberGenerator#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromRandomNumberGenerator#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionFromRandomNumberGenerator.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 0, 0.5d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        new BrownianMotionFromRandomNumberGenerator(
                timeDiscretization, 3, 10, new HighEntropyRandomNumberGenerator())
            .getRandomVariableForConstant(10.0d);

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
