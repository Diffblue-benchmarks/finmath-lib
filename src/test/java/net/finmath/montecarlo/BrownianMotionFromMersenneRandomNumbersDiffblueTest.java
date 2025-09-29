package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BrownianMotionFromMersenneRandomNumbersDiffblueTest {
  /**
   * Test {@link
   * BrownianMotionFromMersenneRandomNumbers#BrownianMotionFromMersenneRandomNumbers(TimeDiscretization,
   * int, int, int)}.
   *
   * <ul>
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromMersenneRandomNumbers#BrownianMotionFromMersenneRandomNumbers(TimeDiscretization,
   * int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianMotionFromMersenneRandomNumbers.<init>(TimeDiscretization, int, int, int)"
  })
  public void testNewBrownianMotionFromMersenneRandomNumbers_thenReturnNumberOfPathsIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotionFromMersenneRandomNumbers actualBrownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Assert
    assertEquals(10, actualBrownianMotionFromMersenneRandomNumbers.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionFromMersenneRandomNumbers.getNumberOfFactors());
    assertEquals(42, actualBrownianMotionFromMersenneRandomNumbers.getSeed());
    assertSame(
        timeDiscretization, actualBrownianMotionFromMersenneRandomNumbers.getTimeDiscretization());
  }

  /**
   * Test {@link
   * BrownianMotionFromMersenneRandomNumbers#BrownianMotionFromMersenneRandomNumbers(TimeDiscretization,
   * int, int, int, RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromMersenneRandomNumbers#BrownianMotionFromMersenneRandomNumbers(TimeDiscretization,
   * int, int, int, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianMotionFromMersenneRandomNumbers.<init>(TimeDiscretization, int, int, int, RandomVariableFactory)"
  })
  public void testNewBrownianMotionFromMersenneRandomNumbers_thenReturnNumberOfPathsIsTen2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotionFromMersenneRandomNumbers actualBrownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());

    // Assert
    assertEquals(10, actualBrownianMotionFromMersenneRandomNumbers.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionFromMersenneRandomNumbers.getNumberOfFactors());
    assertEquals(42, actualBrownianMotionFromMersenneRandomNumbers.getSeed());
    assertSame(
        timeDiscretization, actualBrownianMotionFromMersenneRandomNumbers.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionFromMersenneRandomNumbers#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionFromMersenneRandomNumbers.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        brownianMotionFromMersenneRandomNumbers.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionFromMersenneRandomNumbers);
    assertEquals(brownianMotionFromMersenneRandomNumbers, actualCloneWithModifiedSeed);
  }

  /**
   * Test {@link
   * BrownianMotionFromMersenneRandomNumbers#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionFromMersenneRandomNumbers#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionFromMersenneRandomNumbers.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianMotionFromMersenneRandomNumbers.getCloneWithModifiedTimeDiscretization(
            new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertTrue(
        actualCloneWithModifiedTimeDiscretization
            instanceof BrownianMotionFromMersenneRandomNumbers);
    assertEquals(
        brownianMotionFromMersenneRandomNumbers, actualCloneWithModifiedTimeDiscretization);
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#getIncrement(int, int)} with {@code
   * timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionFromMersenneRandomNumbers#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionFromMersenneRandomNumbers.getIncrement(int, int)"
  })
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10, 42);

    // Act
    RandomVariable actualIncrement = brownianMotionFromMersenneRandomNumbers.getIncrement(1, 3);

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
    assertEquals(-0.8523424263066289d, actualIncrement.getMin(), 0.0);
    assertEquals(0.0700968264533947d, actualIncrement.getAverage(), 0.0);
    assertEquals(0.19645887256967712d, actualIncrement.getStandardError(), 0.0);
    assertEquals(0.38596088611348633d, actualIncrement.getVariance(), 0.0);
    assertEquals(0.42884542901498485d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(0.6212575038689564d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(0.8675341374591691d, actualIncrement.getMax(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(10, actualIncrement.size());
    assertEquals(11.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.6952132395532244d,
          0.7699932913936561d,
          -0.3069818095628826d,
          -0.023254307099351678d,
          -0.8523424263066289d,
          0.48854416670980727d,
          0.283379697713815d,
          -0.5955439494039763d,
          0.7648527031835635d,
          0.8675341374591691d
        },
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#getBrownianIncrement(int, int)} with {@code
   * timeIndex}, {@code factor}.
   *
   * <p>Method under test: {@link BrownianMotionFromMersenneRandomNumbers#getBrownianIncrement(int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionFromMersenneRandomNumbers.getBrownianIncrement(int, int)"
  })
  public void testGetBrownianIncrementWithTimeIndexFactor() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10, 42);

    // Act
    RandomVariable actualBrownianIncrement =
        brownianMotionFromMersenneRandomNumbers.getBrownianIncrement(1, 3);

    // Assert
    assertTrue(actualBrownianIncrement instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.8523424263066289d, actualBrownianIncrement.getMin(), 0.0);
    assertEquals(0.0700968264533947d, actualBrownianIncrement.getAverage(), 0.0);
    assertEquals(0.19645887256967712d, actualBrownianIncrement.getStandardError(), 0.0);
    assertEquals(0.38596088611348633d, actualBrownianIncrement.getVariance(), 0.0);
    assertEquals(0.42884542901498485d, actualBrownianIncrement.getSampleVariance(), 0.0);
    assertEquals(0.6212575038689564d, actualBrownianIncrement.getStandardDeviation(), 0.0);
    assertEquals(0.8675341374591691d, actualBrownianIncrement.getMax(), 0.0);
    assertEquals(1, actualBrownianIncrement.getTypePriority());
    assertEquals(10, actualBrownianIncrement.size());
    assertEquals(11.0d, actualBrownianIncrement.getFiltrationTime(), 0.0);
    assertFalse(actualBrownianIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.6952132395532244d,
          0.7699932913936561d,
          -0.3069818095628826d,
          -0.023254307099351678d,
          -0.8523424263066289d,
          0.48854416670980727d,
          0.283379697713815d,
          -0.5955439494039763d,
          0.7648527031835635d,
          0.8675341374591691d
        },
        actualBrownianIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#getNumberOfFactors()}
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#getNumberOfPaths()}
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#getSeed()}
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int BrownianMotionFromMersenneRandomNumbers.getNumberOfFactors()",
    "int BrownianMotionFromMersenneRandomNumbers.getNumberOfPaths()",
    "int BrownianMotionFromMersenneRandomNumbers.getSeed()",
    "TimeDiscretization BrownianMotionFromMersenneRandomNumbers.getTimeDiscretization()",
    "java.lang.String BrownianMotionFromMersenneRandomNumbers.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    int actualNumberOfFactors = brownianMotionFromMersenneRandomNumbers.getNumberOfFactors();
    int actualNumberOfPaths = brownianMotionFromMersenneRandomNumbers.getNumberOfPaths();
    int actualSeed = brownianMotionFromMersenneRandomNumbers.getSeed();

    // Assert
    assertEquals(10, actualNumberOfPaths);
    assertEquals(3, actualNumberOfFactors);
    assertEquals(42, actualSeed);
    assertSame(timeDiscretization, brownianMotionFromMersenneRandomNumbers.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BrownianMotionFromMersenneRandomNumbers#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionFromMersenneRandomNumbers.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    RandomVariable actualRandomVariableForConstant =
        brownianMotionFromMersenneRandomNumbers.getRandomVariableForConstant(10.0d);

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

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}, and {@link
   * BrownianMotionFromMersenneRandomNumbers#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean BrownianMotionFromMersenneRandomNumbers.equals(Object)",
    "int BrownianMotionFromMersenneRandomNumbers.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers2 =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act and Assert
    assertEquals(brownianMotionFromMersenneRandomNumbers, brownianMotionFromMersenneRandomNumbers2);
    assertEquals(
        brownianMotionFromMersenneRandomNumbers.hashCode(),
        brownianMotionFromMersenneRandomNumbers2.hashCode());
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}, and {@link
   * BrownianMotionFromMersenneRandomNumbers#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}
   *   <li>{@link BrownianMotionFromMersenneRandomNumbers#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean BrownianMotionFromMersenneRandomNumbers.equals(Object)",
    "int BrownianMotionFromMersenneRandomNumbers.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act and Assert
    assertEquals(brownianMotionFromMersenneRandomNumbers, brownianMotionFromMersenneRandomNumbers);
    int expectedHashCodeResult = brownianMotionFromMersenneRandomNumbers.hashCode();
    assertEquals(expectedHashCodeResult, brownianMotionFromMersenneRandomNumbers.hashCode());
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean BrownianMotionFromMersenneRandomNumbers.equals(Object)",
    "int BrownianMotionFromMersenneRandomNumbers.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotionFromMersenneRandomNumbers =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            3,
            10,
            42);

    // Act and Assert
    assertNotEquals(
        brownianMotionFromMersenneRandomNumbers,
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42));
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean BrownianMotionFromMersenneRandomNumbers.equals(Object)",
    "int BrownianMotionFromMersenneRandomNumbers.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42),
        null);
  }

  /**
   * Test {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionFromMersenneRandomNumbers#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean BrownianMotionFromMersenneRandomNumbers.equals(Object)",
    "int BrownianMotionFromMersenneRandomNumbers.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42),
        "Different type to BrownianMotionFromMersenneRandomNumbers");
  }
}
