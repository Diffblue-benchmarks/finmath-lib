package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PoissonDistributionDiffblueTest {
  /**
   * Test {@link PoissonDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link PoissonDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PoissonDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d, new PoissonDistribution(Double.NaN).inverseCumulativeDistribution(2.0d), 0.0);
  }

  /**
   * Test {@link PoissonDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code 4.993992273873334E-4}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link PoissonDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PoissonDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_when4993992273873334e4_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(
        1.0d,
        new PoissonDistribution(10.0d).inverseCumulativeDistribution(4.993992273873334E-4d),
        0.0);
  }
}
