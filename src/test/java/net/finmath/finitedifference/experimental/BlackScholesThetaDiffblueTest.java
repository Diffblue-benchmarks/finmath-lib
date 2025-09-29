package net.finmath.finitedifference.experimental;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesThetaDiffblueTest {
  /**
   * Test new {@link BlackScholesTheta} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link BlackScholesTheta}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlackScholesTheta.<init>()"})
  public void testNewBlackScholesTheta() {
    // Arrange, Act and Assert
    double[][] solveResult = new BlackScholesTheta().solve();
    assertEquals(119, solveResult[0].length);
    assertEquals(119, solveResult[1].length);
    assertEquals(2, solveResult.length);
  }
}
