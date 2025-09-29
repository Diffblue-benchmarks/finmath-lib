package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BrownianBridgeDiffblueTest {
  /**
   * Test {@link BrownianBridge#BrownianBridge(BrownianMotion, RandomVariable[], RandomVariable[])}.
   *
   * <p>Method under test: {@link BrownianBridge#BrownianBridge(BrownianMotion, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianBridge.<init>(BrownianMotion, RandomVariable[], RandomVariable[])"
  })
  public void testNewBrownianBridge() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    BrownianBridge actualBrownianBridge = new BrownianBridge(generator, start, end);

    // Assert
    assertEquals(10, actualBrownianBridge.getNumberOfPaths());
    assertEquals(3, actualBrownianBridge.getNumberOfFactors());
    assertSame(timeDiscretization, actualBrownianBridge.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#BrownianBridge(TimeDiscretization, int, int, RandomVariable,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#BrownianBridge(TimeDiscretization, int, int,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianBridge.<init>(TimeDiscretization, int, int, RandomVariable, RandomVariable)"
  })
  public void testNewBrownianBridge_whenTen_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray start = new RandomVariableFromDoubleArray(10.0d);

    // Act
    BrownianBridge actualBrownianBridge =
        new BrownianBridge(
            timeDiscretization, 10, 42, start, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(1, actualBrownianBridge.getNumberOfFactors());
    assertEquals(10, actualBrownianBridge.getNumberOfPaths());
    assertSame(timeDiscretization, actualBrownianBridge.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#BrownianBridge(TimeDiscretization, int, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#BrownianBridge(TimeDiscretization, int, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianBridge.<init>(TimeDiscretization, int, int, RandomVariable[], RandomVariable[])"
  })
  public void testNewBrownianBridge_whenTen_thenReturnNumberOfFactorsIsOne2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    BrownianBridge actualBrownianBridge =
        new BrownianBridge(timeDiscretization, 10, 42, start, end);

    // Assert
    assertEquals(1, actualBrownianBridge.getNumberOfFactors());
    assertEquals(10, actualBrownianBridge.getNumberOfPaths());
    assertSame(timeDiscretization, actualBrownianBridge.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#getBrownianIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianBridge.getBrownianIncrement(int, int)"})
  public void testGetBrownianIncrementWithTimeIndexFactor_thenReturnNull() {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        mock(RandomVariableDifferentiableAAD.class);
    when(randomVariableDifferentiableAAD.mult(anyDouble())).thenReturn(scalar);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.sub(anyDouble())).thenReturn(randomVariableDifferentiableAAD);
    when(scalar2.getAverage()).thenReturn(10.0d);
    when(scalar2.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(scalar2);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar3.getTypePriority()).thenReturn(1);
    when(scalar3.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariable[] start = new RandomVariable[] {scalar3};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable actualBrownianIncrement = brownianBridge.getBrownianIncrement(1, 1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), eq(0));
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(randomVariableDifferentiableAAD, atLeast(1)).mult(0.07071067811865475d);
    verify(scalar3).getValues();
    verify(scalar2, atLeast(1)).getAverage();
    verify(scalar2, atLeast(1)).getStandardDeviation();
    verify(scalar3).getTypePriority();
    verify(scalar, atLeast(1)).mult(anyDouble());
    verify(scalar3).mult(0.9d);
    verify(scalar2, atLeast(1)).sub(10.0d);
    assertNull(actualBrownianIncrement);
  }

  /**
   * Test {@link BrownianBridge#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link BrownianBridge#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization BrownianBridge.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
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

    BrownianBridge brownianBridge = new BrownianBridge(generator2, start2, end2);

    // Act and Assert
    assertSame(timeDiscretization, brownianBridge.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link BrownianBridge#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianBridge.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act and Assert
    assertEquals(3, brownianBridge.getNumberOfFactors());
  }

  /**
   * Test {@link BrownianBridge#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link BrownianBridge#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianBridge.getNumberOfFactors()"})
  public void testGetNumberOfFactors2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    BrownianMotionWithControlVariate generator2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator2, start2, end2);

    // Act and Assert
    assertEquals(3, brownianBridge.getNumberOfFactors());
  }

  /**
   * Test {@link BrownianBridge#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianBridge.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenReturnOne() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    Integer[] factors = new Integer[] {3};
    BrownianMotionView brownianMotion2 =
        new BrownianMotionView(new BrownianMotionWithControlVariate(brownianMotion), factors);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act and Assert
    assertEquals(1, brownianBridge.getNumberOfFactors());
  }

  /**
   * Test {@link BrownianBridge#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link BrownianBridge#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianBridge.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act and Assert
    assertEquals(10, brownianBridge.getNumberOfPaths());
  }

  /**
   * Test {@link BrownianBridge#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link BrownianBridge#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BrownianBridge.getNumberOfPaths()"})
  public void testGetNumberOfPaths2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    BrownianMotionWithControlVariate generator2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator2, start2, end2);

    // Act and Assert
    assertEquals(10, brownianBridge.getNumberOfPaths());
  }

  /**
   * Test {@link BrownianBridge#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link BrownianBridge#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianBridge.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable actualRandomVariableForConstant =
        brownianBridge.getRandomVariableForConstant(10.0d);

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
   * Test {@link BrownianBridge#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link BrownianBridge#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion BrownianBridge.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
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

    BrownianBridge brownianBridge = new BrownianBridge(generator2, start2, end2);

    // Act
    BrownianMotion actualCloneWithModifiedSeed = brownianBridge.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianBridge);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion BrownianBridge.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    BrownianMotion actualCloneWithModifiedSeed = brownianBridge.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof BrownianBridge);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
  }

  /**
   * Test {@link BrownianBridge#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianBridge#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianBridge.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianBridge.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof BrownianBridge);
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * BrownianBridge#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion BrownianBridge.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
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
    BrownianMotionWithControlVariate generator2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator2, start2, end2);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        brownianBridge.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof BrownianBridge);
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianBridge#getIncrement(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link BrownianBridge#getIncrement(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BrownianBridge.getIncrement(int)"})
  public void testGetIncrementWithTimeIndex() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable[] actualIncrement = brownianBridge.getIncrement(1);

    // Assert
    RandomVariable randomVariable = actualIncrement[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(-1.0658141036401502E-15d, randomVariable.getAverage(), 0.0);
    assertEquals(-1.5760103279231448d, randomVariable.getMin(), 0.0);
    assertEquals(0.22238691221747786d, randomVariable.getStandardError(), 0.0);
    assertEquals(0.4945593872562421d, randomVariable.getVariance(), 0.0);
    assertEquals(0.5495104302847135d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(0.7032491644191567d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(0.922300549013114d, randomVariable.getMax(), 0.0);
    assertEquals(3, actualIncrement.length);
    assertArrayEquals(
        new double[] {
          0.025987056421902466d,
          0.10177893988664444d,
          0.7496687794298929d,
          0.1463670422852683d,
          -1.5760103279231448d,
          -0.08108948659003445d,
          0.922300549013114d,
          -0.6743281599381064d,
          0.6831494307019579d,
          -0.297823823287505d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianBridge#getIncrement(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link BrownianBridge#getIncrement(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BrownianBridge.getIncrement(int)"})
  public void testGetIncrementWithTimeIndex2() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(3);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {RandomVariableDifferentiableAAD.of(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable[] actualIncrement = brownianBridge.getIncrement(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    RandomVariable randomVariable = actualIncrement[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(3, actualIncrement.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianBridge#getIncrement(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link BrownianBridge#getIncrement(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BrownianBridge.getIncrement(int)"})
  public void testGetIncrementWithTimeIndex3() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(3);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {RandomVariableDifferentiableAAD.of(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable[] actualIncrement = brownianBridge.getIncrement(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    RandomVariable randomVariable = actualIncrement[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(3, actualIncrement.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianBridge#getIncrement(int, int)} with {@code timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianBridge.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnNull() {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        mock(RandomVariableDifferentiableAAD.class);
    when(randomVariableDifferentiableAAD.mult(anyDouble())).thenReturn(scalar);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.sub(anyDouble())).thenReturn(randomVariableDifferentiableAAD);
    when(scalar2.getAverage()).thenReturn(10.0d);
    when(scalar2.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(scalar2);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar3.getTypePriority()).thenReturn(1);
    when(scalar3.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    RandomVariable[] start = new RandomVariable[] {scalar3};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable actualIncrement = brownianBridge.getIncrement(1, 1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), eq(0));
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(randomVariableDifferentiableAAD, atLeast(1)).mult(0.07071067811865475d);
    verify(scalar3).getValues();
    verify(scalar2, atLeast(1)).getAverage();
    verify(scalar2, atLeast(1)).getStandardDeviation();
    verify(scalar3).getTypePriority();
    verify(scalar, atLeast(1)).mult(anyDouble());
    verify(scalar3).mult(0.9d);
    verify(scalar2, atLeast(1)).sub(10.0d);
    assertNull(actualIncrement);
  }

  /**
   * Test {@link BrownianBridge#getIncrement(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return first element FiltrationTime is ten.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#getIncrement(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BrownianBridge.getIncrement(int)"})
  public void testGetIncrementWithTimeIndex_thenReturnFirstElementFiltrationTimeIsTen() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(2);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator, start, end);

    // Act
    RandomVariable[] actualIncrement = brownianBridge.getIncrement(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    RandomVariable randomVariable = actualIncrement[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, randomVariable.getFiltrationTime(), 0.0);
    assertEquals(3, actualIncrement.length);
    assertEquals(Double.NaN, randomVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMax(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMin(), 0.0);
    assertEquals(Double.NaN, randomVariable.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, randomVariable.getStandardError(), 0.0);
    assertEquals(Double.NaN, randomVariable.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianBridge#getIncrement(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return first element Min is {@code -1.544791121980147}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianBridge#getIncrement(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BrownianBridge.getIncrement(int)"})
  public void testGetIncrementWithTimeIndex_thenReturnFirstElementMinIs1544791121980147() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    BrownianMotionWithControlVariate generator2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end2 = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianBridge = new BrownianBridge(generator2, start2, end2);

    // Act
    RandomVariable[] actualIncrement = brownianBridge.getIncrement(1);

    // Assert
    RandomVariable randomVariable = actualIncrement[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(-1.544791121980147d, randomVariable.getMin(), 0.0);
    assertEquals(-5.329070518200751E-16d, randomVariable.getAverage(), 0.0);
    assertEquals(0.22422635003623462d, randomVariable.getStandardError(), 0.0);
    assertEquals(0.5027745605057201d, randomVariable.getVariance(), 0.0);
    assertEquals(0.5586384005619113d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(0.70906597754068d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(1.0347284046178071d, randomVariable.getMax(), 0.0);
    assertEquals(3, actualIncrement.length);
    assertArrayEquals(
        new double[] {
          0.049641668907705494d,
          0.08555865238577987d,
          0.6561865241238536d,
          0.17164543989781578d,
          -1.544791121980147d,
          -0.11779367752101777d,
          1.0347284046178071d,
          -0.6392482209572083d,
          0.7142336411818313d,
          -0.41016131065642547d
        },
        randomVariable.getRealizations(),
        0.0);
  }
}
