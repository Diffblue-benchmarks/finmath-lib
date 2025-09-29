package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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

public class MertonJumpProcessDiffblueTest {
  /**
   * Test {@link MertonJumpProcess#MertonJumpProcess(double, double, double, TimeDiscretization,
   * int, int)}.
   *
   * <p>Method under test: {@link MertonJumpProcess#MertonJumpProcess(double, double, double,
   * TimeDiscretization, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonJumpProcess.<init>(double, double, double, TimeDiscretization, int, int)"
  })
  public void testNewMertonJumpProcess() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MertonJumpProcess actualMertonJumpProcess =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 10, 42);

    // Assert
    assertEquals(10, actualMertonJumpProcess.getNumberOfPaths());
    assertEquals(10.0d, actualMertonJumpProcess.getJumpIntensity(), 0.0);
    assertEquals(10.0d, actualMertonJumpProcess.getJumpSizeMean(), 0.0);
    assertEquals(10.0d, actualMertonJumpProcess.getJumpSizeStDev(), 0.0);
    assertEquals(3, actualMertonJumpProcess.getNumberOfFactors());
    assertSame(timeDiscretization, actualMertonJumpProcess.getTimeDiscretization());
  }

  /**
   * Test {@link MertonJumpProcess#getIncrement(int, int)} with {@code timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.2630242403216885}.
   * </ul>
   *
   * <p>Method under test: {@link MertonJumpProcess#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MertonJumpProcess.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnAverageIs02630242403216885() {
    // Arrange and Act
    RandomVariable actualIncrement =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42)
            .getIncrement(1, 1);

    // Assert
    assertTrue(actualIncrement instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.2630242403216885d, actualIncrement.getAverage(), 0.0);
    assertEquals(-3.287293820567879d, actualIncrement.getMin(), 0.0);
    assertEquals(0.5504747484667605d, actualIncrement.getStandardError(), 0.0);
    assertEquals(1.7407539995632446d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(2.0278388587879d, actualIncrement.getMax(), 0.0);
    assertEquals(3.030224486995433d, actualIncrement.getVariance(), 0.0);
    assertEquals(3.3669160966615923d, actualIncrement.getSampleVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          -2.8174146625409504d,
          -1.199692650153814d,
          -3.287293820567879d,
          1.9462694342371731d,
          -0.7745829458211291d,
          1.3910379839474383d,
          -0.2538399623264943d,
          0.5252617052919093d,
          -0.18782634407103863d,
          2.0278388587879d
        },
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MertonJumpProcess#getIncrement(int, int)} with {@code timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.7042726744663603}.
   * </ul>
   *
   * <p>Method under test: {@link MertonJumpProcess#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MertonJumpProcess.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnStandardErrorIs07042726744663603() {
    // Arrange and Act
    RandomVariable actualIncrement =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42)
            .getIncrement(1, 2);

    // Assert
    assertTrue(actualIncrement instanceof RandomVariableFromDoubleArray);
    assertEquals(0.7042726744663603d, actualIncrement.getStandardError(), 0.0);
    assertEquals(1.0d, actualIncrement.getMin(), 0.0);
    assertEquals(2.227105745132009d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(4.8d, actualIncrement.getAverage(), 0.0);
    assertEquals(4.96d, actualIncrement.getVariance(), 0.0);
    assertEquals(5.511111111111111d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(9.0d, actualIncrement.getMax(), 0.0);
    assertArrayEquals(
        new double[] {7.0d, 4.0d, 6.0d, 4.0d, 9.0d, 1.0d, 5.0d, 4.0d, 2.0d, 6.0d},
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MertonJumpProcess#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link MertonJumpProcess#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization MertonJumpProcess.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertSame(
        timeDiscretization,
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 10, 42)
            .getTimeDiscretization());
  }

  /**
   * Test {@link MertonJumpProcess#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link MertonJumpProcess#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MertonJumpProcess.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange, Act and Assert
    assertEquals(
        3,
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42)
            .getNumberOfFactors());
  }

  /**
   * Test {@link MertonJumpProcess#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link MertonJumpProcess#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MertonJumpProcess.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange, Act and Assert
    assertEquals(
        10,
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42)
            .getNumberOfPaths());
  }

  /**
   * Test {@link MertonJumpProcess#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link MertonJumpProcess#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MertonJumpProcess.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42)
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

  /**
   * Test {@link MertonJumpProcess#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link MertonJumpProcess#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IndependentIncrements MertonJumpProcess.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    IndependentIncrements actualCloneWithModifiedSeed =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 10, 42)
            .getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof IndependentIncrementsFromICDF);
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(42, ((IndependentIncrementsFromICDF) actualCloneWithModifiedSeed).getSeed());
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link MertonJumpProcess#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * MertonJumpProcess#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "IndependentIncrements MertonJumpProcess.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    MertonJumpProcess mertonJumpProcess =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    IndependentIncrements actualCloneWithModifiedTimeDiscretization =
        mertonJumpProcess.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof IndependentIncrementsFromICDF);
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(
        42, ((IndependentIncrementsFromICDF) actualCloneWithModifiedTimeDiscretization).getSeed());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MertonJumpProcess#getJumpIntensity()}
   *   <li>{@link MertonJumpProcess#getJumpSizeMean()}
   *   <li>{@link MertonJumpProcess#getJumpSizeStDev()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double MertonJumpProcess.getJumpIntensity()",
    "double MertonJumpProcess.getJumpSizeMean()",
    "double MertonJumpProcess.getJumpSizeStDev()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MertonJumpProcess mertonJumpProcess =
        new MertonJumpProcess(10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 42);

    // Act
    double actualJumpIntensity = mertonJumpProcess.getJumpIntensity();
    double actualJumpSizeMean = mertonJumpProcess.getJumpSizeMean();

    // Assert
    assertEquals(10.0d, actualJumpIntensity, 0.0);
    assertEquals(10.0d, actualJumpSizeMean, 0.0);
    assertEquals(10.0d, mertonJumpProcess.getJumpSizeStDev(), 0.0);
  }
}
