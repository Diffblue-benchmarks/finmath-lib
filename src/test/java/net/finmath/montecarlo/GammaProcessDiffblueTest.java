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

public class GammaProcessDiffblueTest {
  /**
   * Test {@link GammaProcess#GammaProcess(TimeDiscretization, int, int, int, double)}.
   *
   * <p>Method under test: {@link GammaProcess#GammaProcess(TimeDiscretization, int, int, int,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GammaProcess.<init>(TimeDiscretization, int, int, int, double)"})
  public void testNewGammaProcess() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    GammaProcess actualGammaProcess = new GammaProcess(timeDiscretization, 3, 10, 42, 10.0d);

    // Assert
    assertEquals(10, actualGammaProcess.getNumberOfPaths());
    assertEquals(3, actualGammaProcess.getNumberOfFactors());
    assertEquals(42, actualGammaProcess.getSeed());
    assertSame(timeDiscretization, actualGammaProcess.getTimeDiscretization());
  }

  /**
   * Test {@link GammaProcess#GammaProcess(TimeDiscretization, int, int, int, double, double)}.
   *
   * <p>Method under test: {@link GammaProcess#GammaProcess(TimeDiscretization, int, int, int,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GammaProcess.<init>(TimeDiscretization, int, int, int, double, double)"})
  public void testNewGammaProcess2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    GammaProcess actualGammaProcess = new GammaProcess(timeDiscretization, 3, 10, 42, 10.0d, 10.0d);

    // Assert
    assertEquals(10, actualGammaProcess.getNumberOfPaths());
    assertEquals(3, actualGammaProcess.getNumberOfFactors());
    assertEquals(42, actualGammaProcess.getSeed());
    assertSame(timeDiscretization, actualGammaProcess.getTimeDiscretization());
  }

  /**
   * Test {@link GammaProcess#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link GammaProcess#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IndependentIncrements GammaProcess.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d);

    // Act
    IndependentIncrements actualCloneWithModifiedSeed = gammaProcess.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof GammaProcess);
    assertEquals(gammaProcess, actualCloneWithModifiedSeed);
  }

  /**
   * Test {@link GammaProcess#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * GammaProcess#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "IndependentIncrements GammaProcess.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d);

    // Act
    IndependentIncrements actualCloneWithModifiedTimeDiscretization =
        gammaProcess.getCloneWithModifiedTimeDiscretization(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof GammaProcess);
    assertEquals(gammaProcess, actualCloneWithModifiedTimeDiscretization);
  }

  /**
   * Test {@link GammaProcess#getIncrement(int, int)} with {@code timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable GammaProcess.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 10, 10, 42, 10.0d);

    // Act
    RandomVariable actualIncrement = gammaProcess.getIncrement(1, 3);

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
    assertEquals(0.611007803297691d, actualIncrement.getStandardError(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(1.9321763265568437d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(10, actualIncrement.size());
    assertEquals(11.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertEquals(2.538506394561785d, actualIncrement.getMin(), 0.0);
    assertEquals(3.733305356906699d, actualIncrement.getVariance(), 0.0);
    assertEquals(4.148117063229666d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(5.144092581837784d, actualIncrement.getAverage(), 0.0);
    assertEquals(7.827650985690867d, actualIncrement.getMax(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          2.866086413347129d,
          7.418859621627185d,
          3.799679843892829d,
          4.600621413230095d,
          2.538506394561785d,
          6.317406606295547d,
          5.585848893729235d,
          3.088559501555079d,
          7.397706144448092d,
          7.827650985690867d
        },
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link GammaProcess#getNumberOfFactors()}
   *   <li>{@link GammaProcess#getNumberOfPaths()}
   *   <li>{@link GammaProcess#getSeed()}
   *   <li>{@link GammaProcess#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int GammaProcess.getNumberOfFactors()",
    "int GammaProcess.getNumberOfPaths()",
    "int GammaProcess.getSeed()",
    "TimeDiscretization GammaProcess.getTimeDiscretization()",
    "java.lang.String GammaProcess.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    GammaProcess gammaProcess = new GammaProcess(timeDiscretization, 3, 10, 42, 10.0d);

    // Act
    int actualNumberOfFactors = gammaProcess.getNumberOfFactors();
    int actualNumberOfPaths = gammaProcess.getNumberOfPaths();
    int actualSeed = gammaProcess.getSeed();

    // Assert
    assertEquals(10, actualNumberOfPaths);
    assertEquals(3, actualNumberOfFactors);
    assertEquals(42, actualSeed);
    assertSame(timeDiscretization, gammaProcess.getTimeDiscretization());
  }

  /**
   * Test {@link GammaProcess#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link GammaProcess#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable GammaProcess.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        gammaProcess.getRandomVariableForConstant(10.0d);

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
   * Test {@link GammaProcess#equals(Object)}, and {@link GammaProcess#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link GammaProcess#equals(Object)}
   *   <li>{@link GammaProcess#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d);
    GammaProcess gammaProcess2 =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d);

    // Act and Assert
    assertEquals(gammaProcess, gammaProcess2);
    assertEquals(gammaProcess.hashCode(), gammaProcess2.hashCode());
  }

  /**
   * Test {@link GammaProcess#equals(Object)}, and {@link GammaProcess#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link GammaProcess#equals(Object)}
   *   <li>{@link GammaProcess#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d);

    // Act and Assert
    assertEquals(gammaProcess, gammaProcess);
    int expectedHashCodeResult = gammaProcess.hashCode();
    assertEquals(expectedHashCodeResult, gammaProcess.hashCode());
  }

  /**
   * Test {@link GammaProcess#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            3,
            10,
            42,
            10.0d);

    // Act and Assert
    assertNotEquals(
        gammaProcess, new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d));
  }

  /**
   * Test {@link GammaProcess#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 10, 10, 42, 10.0d);

    // Act and Assert
    assertNotEquals(
        gammaProcess, new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d));
  }

  /**
   * Test {@link GammaProcess#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 3, 42, 10.0d);

    // Act and Assert
    assertNotEquals(
        gammaProcess, new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d));
  }

  /**
   * Test {@link GammaProcess#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    GammaProcess gammaProcess =
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 3, 10.0d);

    // Act and Assert
    assertNotEquals(
        gammaProcess, new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d));
  }

  /**
   * Test {@link GammaProcess#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d), null);
  }

  /**
   * Test {@link GammaProcess#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link GammaProcess#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GammaProcess.equals(Object)", "int GammaProcess.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new GammaProcess(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, 10.0d),
        "Different type to GammaProcess");
  }
}
