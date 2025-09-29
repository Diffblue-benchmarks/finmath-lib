package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GammaDistributionDiffblueTest {
  /**
   * Test {@link GammaDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>Then return {@code 96.68714614712741}.
   * </ul>
   *
   * <p>Method under test: {@link GammaDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GammaDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_thenReturn9668714614712741() {
    // Arrange, Act and Assert
    assertEquals(
        96.68714614712741d,
        new GammaDistribution(10.0d, 10.0d).inverseCumulativeDistribution(0.5d),
        0.0);
  }

  /**
   * Test {@link GammaDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>Then return {@code 2.2746821155978663}.
   * </ul>
   *
   * <p>Method under test: {@link GammaDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GammaDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_thenReturn22746821155978663() {
    // Arrange, Act and Assert
    assertEquals(
        2.2746821155978663d,
        new GammaDistribution(0.5d, 10.0d).inverseCumulativeDistribution(0.5d),
        0.0);
  }
}
