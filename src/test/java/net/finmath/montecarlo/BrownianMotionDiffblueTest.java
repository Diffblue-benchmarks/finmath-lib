package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BrownianMotionDiffblueTest {
  @Mock private BrownianMotion brownianMotion;

  @InjectMocks private BrownianMotionWithControlVariate brownianMotionWithControlVariate;

  /**
   * Test {@link BrownianMotion#getBrownianIncrement(double, int)} with {@code double}, {@code int}.
   *
   * <ul>
   *   <li>Then return Max is zero.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotion#getBrownianIncrement(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotion.getBrownianIncrement(double, int)"})
  public void testGetBrownianIncrementWithDoubleInt_thenReturnMaxIsZero() {
    // Arrange
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable actualBrownianIncrement =
        brownianMotionWithControlVariate.getBrownianIncrement(10.0d, 3);

    // Assert
    verify(brownianMotion).getBrownianIncrement(0, 3);
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
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
    assertEquals(0.0d, actualBrownianIncrement.getAverage(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getMax(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getMin(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getStandardError(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getVariance(), 0.0);
    assertEquals(1, actualBrownianIncrement.getTypePriority());
    assertEquals(1, actualBrownianIncrement.size());
    assertTrue(actualBrownianIncrement.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBrownianIncrement.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBrownianIncrement.getRealizations(), 0.0);
  }

  /**
   * Test {@link BrownianMotion#getBrownianIncrement(double, int)} with {@code double}, {@code int}.
   *
   * <ul>
   *   <li>Then return Min is {@code -0.8350992788719885}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotion#getBrownianIncrement(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotion.getBrownianIncrement(double, int)"})
  public void testGetBrownianIncrementWithDoubleInt_thenReturnMinIs08350992788719885() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 4, 10, 42);

    // Act
    RandomVariable actualBrownianIncrement =
        new BrownianMotionWithControlVariate(brownianMotion).getBrownianIncrement(10.0d, 3);

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
    assertEquals(-0.8350992788719885d, actualBrownianIncrement.getMin(), 0.0);
    assertEquals(0.0d, actualBrownianIncrement.getAverage(), 0.0);
    assertEquals(0.22360679774997896d, actualBrownianIncrement.getStandardError(), 0.0);
    assertEquals(0.5555555555555556d, actualBrownianIncrement.getSampleVariance(), 0.0);
    assertEquals(0.5d, actualBrownianIncrement.getVariance(), 0.0);
    assertEquals(0.7071067811865476d, actualBrownianIncrement.getStandardDeviation(), 0.0);
    assertEquals(1, actualBrownianIncrement.getTypePriority());
    assertEquals(1.1360884346362217d, actualBrownianIncrement.getMax(), 0.0);
    assertEquals(10, actualBrownianIncrement.size());
    assertEquals(10.5d, actualBrownianIncrement.getFiltrationTime(), 0.0);
    assertFalse(actualBrownianIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          0.5850432098858473d,
          0.7459997721521757d,
          -0.8053950805245993d,
          -0.781252653188144d,
          0.5700249683857929d,
          -0.5620295390622784d,
          1.1360884346362217d,
          0.2987895228227354d,
          -0.35216935623576284d,
          -0.8350992788719885d
        },
        actualBrownianIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BrownianMotion#getIncrement(int, int)} with {@code timeIndex}, {@code factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotion#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BrownianMotion.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10, 42);

    // Act
    RandomVariable actualIncrement =
        new BrownianMotionWithControlVariate(brownianMotion).getIncrement(1, 3);

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
    assertEquals(-1.0499077223167805d, actualIncrement.getMin(), 0.0);
    assertEquals(0.22360679774997896d, actualIncrement.getStandardError(), 0.0);
    assertEquals(0.5000000000000001d, actualIncrement.getVariance(), 0.0);
    assertEquals(0.5555555555555557d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(0.7071067811865476d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(0.9076322244347305d, actualIncrement.getMax(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(10, actualIncrement.size());
    assertEquals(11.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertEquals(2.2204460492503132E-17d, actualIncrement.getAverage(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {
          -0.8710654342418249d,
          0.7966125695153659d,
          -0.42918573845335106d,
          -0.10625098152620709d,
          -1.0499077223167805d,
          0.4762710309688207d,
          0.24275564261191152d,
          -0.757623213420861d,
          0.7907616224281965d,
          0.9076322244347305d
        },
        actualIncrement.getRealizations(),
        0.0);
  }
}
