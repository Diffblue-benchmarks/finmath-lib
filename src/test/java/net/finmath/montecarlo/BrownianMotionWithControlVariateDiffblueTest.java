package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BrownianMotionWithControlVariateDiffblueTest {
  @Mock private BrownianMotion brownianMotion;

  @InjectMocks private BrownianMotionWithControlVariate brownianMotionWithControlVariate;

  /**
   * Test {@link BrownianMotionWithControlVariate#BrownianMotionWithControlVariate(BrownianMotion)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionWithControlVariate#BrownianMotionWithControlVariate(BrownianMotion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BrownianMotionWithControlVariate.<init>(BrownianMotion)"})
  public void testNewBrownianMotionWithControlVariate() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    BrownianMotionWithControlVariate actualBrownianMotionWithControlVariate =
        new BrownianMotionWithControlVariate(brownianMotion);

    // Assert
    assertEquals(10, actualBrownianMotionWithControlVariate.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionWithControlVariate.getNumberOfFactors());
    assertSame(timeDiscretization, actualBrownianMotionWithControlVariate.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getBrownianIncrement(int, int)} with {@code
   * timeIndex}, {@code factorIndex}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionWithControlVariate.getBrownianIncrement(int, int)"
  })
  public void testGetBrownianIncrementWithTimeIndexFactorIndex() {
    // Arrange
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualBrownianIncrement =
        brownianMotionWithControlVariate.getBrownianIncrement(1, 3);

    // Assert
    verify(brownianMotion).getBrownianIncrement(1, 3);
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getTimeDiscretization();
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
    assertArrayEquals(new double[] {0.0d}, actualBrownianIncrement.getRealizations(), 0.0);
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getBrownianIncrement(int, int)} with {@code
   * timeIndex}, {@code factorIndex}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionWithControlVariate.getBrownianIncrement(int, int)"
  })
  public void testGetBrownianIncrementWithTimeIndexFactorIndex2() {
    // Arrange
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(0.0d);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(randomVariableFromDoubleArray);

    // Act
    RandomVariable actualBrownianIncrement =
        brownianMotionWithControlVariate.getBrownianIncrement(1, 3);

    // Assert
    verify(brownianMotion).getBrownianIncrement(1, 3);
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualBrownianIncrement);
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization BrownianMotionWithControlVariate.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act and Assert
    assertSame(
        timeDiscretization,
        new BrownianMotionWithControlVariate(brownianMotion).getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization BrownianMotionWithControlVariate.getTimeDiscretization()"})
  public void testGetTimeDiscretization2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);

    // Act and Assert
    assertSame(
        timeDiscretization,
        new BrownianMotionWithControlVariate(brownianMotion2).getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionWithControlVariate.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act and Assert
    assertEquals(3, new BrownianMotionWithControlVariate(brownianMotion).getNumberOfFactors());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionWithControlVariate.getNumberOfFactors()"})
  public void testGetNumberOfFactors2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);

    // Act and Assert
    assertEquals(3, new BrownianMotionWithControlVariate(brownianMotion2).getNumberOfFactors());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionWithControlVariate.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act and Assert
    assertEquals(10, new BrownianMotionWithControlVariate(brownianMotion).getNumberOfPaths());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianMotionWithControlVariate.getNumberOfPaths()"})
  public void testGetNumberOfPaths2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);

    // Act and Assert
    assertEquals(10, new BrownianMotionWithControlVariate(brownianMotion2).getNumberOfPaths());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionWithControlVariate#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionWithControlVariate.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    RandomVariable actualRandomVariableForConstant =
        new BrownianMotionWithControlVariate(brownianMotion).getRandomVariableForConstant(10.0d);

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
   * Test {@link BrownianMotionWithControlVariate#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionWithControlVariate#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BrownianMotionWithControlVariate.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    RandomVariable actualRandomVariableForConstant =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion))
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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionWithControlVariate.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion))
            .getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionWithControlVariate);
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionWithControlVariate.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        new BrownianMotionWithControlVariate(brownianMotion2).getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionWithControlVariate);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionWithControlVariate.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnNumberOfFactorsIsOne2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    BrownianMotionWithControlVariate generator2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion3 = new BrownianBridge(generator2, start2, end2);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        new BrownianMotionWithControlVariate(brownianMotion3).getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionWithControlVariate);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is three.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionWithControlVariate.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnNumberOfFactorsIsThree() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        new BrownianMotionWithControlVariate(brownianMotion).getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianMotionWithControlVariate);
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link
   * BrownianMotionWithControlVariate#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionWithControlVariate#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionWithControlVariate.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotionWithControlVariate =
        new BrownianMotionWithControlVariate(brownianMotion);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianMotionWithControlVariate.getCloneWithModifiedTimeDiscretization(
            newTimeDiscretization);

    // Assert
    assertTrue(
        actualCloneWithModifiedTimeDiscretization instanceof BrownianMotionWithControlVariate);
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link
   * BrownianMotionWithControlVariate#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianMotionWithControlVariate#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianMotionWithControlVariate.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    BrownianMotionWithControlVariate brownianMotionWithControlVariate =
        new BrownianMotionWithControlVariate(brownianMotion2);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianMotionWithControlVariate.getCloneWithModifiedTimeDiscretization(
            newTimeDiscretization);

    // Assert
    assertTrue(
        actualCloneWithModifiedTimeDiscretization instanceof BrownianMotionWithControlVariate);
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotionWithControlVariate.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor() {
    // Arrange
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(0.0d);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(randomVariableFromDoubleArray);

    // Act
    RandomVariable actualIncrement = brownianMotionWithControlVariate.getIncrement(1, 3);

    // Assert
    verify(brownianMotion).getBrownianIncrement(1, 3);
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualIncrement);
  }

  /**
   * Test {@link BrownianMotionWithControlVariate#getIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionWithControlVariate#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotionWithControlVariate.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualIncrement = brownianMotionWithControlVariate.getIncrement(1, 3);

    // Assert
    verify(brownianMotion).getBrownianIncrement(1, 3);
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getTimeDiscretization();
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
    assertArrayEquals(new double[] {0.0d}, actualIncrement.getRealizations(), 0.0);
  }
}
