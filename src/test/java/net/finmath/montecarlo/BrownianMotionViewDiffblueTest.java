package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BrownianMotionViewDiffblueTest {
  /**
   * Test {@link BrownianMotionView#BrownianMotionView(BrownianMotion, Integer[])}.
   *
   * <p>Method under test: {@link BrownianMotionView#BrownianMotionView(BrownianMotion, Integer[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BrownianMotionView.<init>(BrownianMotion, Integer[])"})
  public void testNewBrownianMotionView() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    Integer[] factors = new Integer[] {3};

    // Act
    BrownianMotionView actualBrownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Assert
    assertEquals(1, actualBrownianMotionView.getNumberOfFactors());
    assertEquals(10, actualBrownianMotionView.getNumberOfPaths());
    assertSame(timeDiscretization, actualBrownianMotionView.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getBrownianIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <p>Method under test: {@link BrownianMotionView#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotionView.getBrownianIncrement(int, int)"})
  public void testGetBrownianIncrementWithTimeIndexFactor() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 10, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act
    RandomVariable actualBrownianIncrement = brownianMotionView.getBrownianIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
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
    assertEquals(-4.6953300744045885d, actualBrownianIncrement.getMin(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getAverage(), 0.0);
    assertEquals(0.9999999999999997d, actualBrownianIncrement.getStandardError(), 0.0);
    assertEquals(1, actualBrownianIncrement.getTypePriority());
    assertEquals(10, actualBrownianIncrement.size());
    assertEquals(10.0d, actualBrownianIncrement.getFiltrationTime(), 0.0);
    assertEquals(11.111111111111107d, actualBrownianIncrement.getSampleVariance(), 0.0);
    assertEquals(3.1622776601683786d, actualBrownianIncrement.getStandardDeviation(), 0.0);
    assertEquals(4.059054704810805d, actualBrownianIncrement.getMax(), 0.0);
    assertEquals(9.999999999999996d, actualBrownianIncrement.getVariance(), 0.0);
    assertFalse(actualBrownianIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          -3.8955230476301863d,
          3.5625597143342684d,
          -1.9193769723102765d,
          -0.4751688347373467d,
          -4.6953300744045885d,
          2.1299488019203805d,
          1.0856362376037572d,
          -3.388194013081752d,
          3.5363934834949386d,
          4.059054704810805d
        },
        actualBrownianIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianMotionView#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link BrownianMotionView#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization BrownianMotionView.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion2), factors2);

    // Act and Assert
    assertSame(timeDiscretization, brownianMotionView.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link BrownianMotionView#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionView.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act and Assert
    assertEquals(1, brownianMotionView.getNumberOfFactors());
  }

  /**
   * Test {@link BrownianMotionView#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link BrownianMotionView#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionView.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act and Assert
    assertEquals(10, brownianMotionView.getNumberOfPaths());
  }

  /**
   * Test {@link BrownianMotionView#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link BrownianMotionView#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionView.getNumberOfPaths()"})
  public void testGetNumberOfPaths2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion2), factors2);

    // Act and Assert
    assertEquals(10, brownianMotionView.getNumberOfPaths());
  }

  /**
   * Test {@link BrownianMotionView#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link BrownianMotionView#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotionView.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act
    RandomVariable actualRandomVariableForConstant =
        brownianMotionView.getRandomVariableForConstant(10.0d);

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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link BrownianMotionView#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link BrownianMotionView#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotionView.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion2), factors2);

    // Act
    RandomVariable actualRandomVariableForConstant =
        brownianMotionView.getRandomVariableForConstant(10.0d);

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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link BrownianMotionView#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link BrownianMotionView#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion BrownianMotionView.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion2), factors2);

    // Act
    BrownianMotion actualCloneWithModifiedSeed = brownianMotionView.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionView);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link BrownianMotionView#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion BrownianMotionView.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion3 = new BrownianBridge(generator, start, end);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion3), factors2);

    // Act
    BrownianMotion actualCloneWithModifiedSeed = brownianMotionView.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionView);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link BrownianMotionView#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion BrownianMotionView.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotion3 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion2), factors2);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion3);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion4 = new BrownianBridge(generator, start, end);
    Integer[] factors3 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion4), factors3);

    // Act
    BrownianMotion actualCloneWithModifiedSeed = brownianMotionView.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionView);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionView#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion BrownianMotionView.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act
    BrownianMotion actualCloneWithModifiedSeed = brownianMotionView.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionView);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
  }

  /**
   * Test {@link BrownianMotionView#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionView#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionView.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianMotionView.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof BrownianMotionView);
    assertEquals(1, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionView#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionView.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    Integer[] factors2 = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion2), factors2);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianMotionView.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof BrownianMotionView);
    assertEquals(1, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionView#getIncrement(int, int)} with {@code timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionView#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotionView.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 10, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotionView =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);

    // Act
    RandomVariable actualIncrement = brownianMotionView.getIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
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
    assertEquals(-4.6953300744045885d, actualIncrement.getMin(), 0.0);
    assertEquals(0.0d, actualIncrement.getAverage(), 0.0);
    assertEquals(0.9999999999999997d, actualIncrement.getStandardError(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(10, actualIncrement.size());
    assertEquals(10.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertEquals(11.111111111111107d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(3.1622776601683786d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(4.059054704810805d, actualIncrement.getMax(), 0.0);
    assertEquals(9.999999999999996d, actualIncrement.getVariance(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          -3.8955230476301863d,
          3.5625597143342684d,
          -1.9193769723102765d,
          -0.4751688347373467d,
          -4.6953300744045885d,
          2.1299488019203805d,
          1.0856362376037572d,
          -3.388194013081752d,
          3.5363934834949386d,
          4.059054704810805d
        },
        actualIncrement.getRealizations(),
        0.0);
  }
}
