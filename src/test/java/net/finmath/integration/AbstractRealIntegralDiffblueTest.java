package net.finmath.integration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractRealIntegralDiffblueTest {
  /**
   * Test {@link AbstractRealIntegral#getLowerBound()}.
   *
   * <p>Method under test: {@link AbstractRealIntegral#getLowerBound()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractRealIntegral.getLowerBound()"})
  public void testGetLowerBound() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new MonteCarloIntegrator(10.0d, 10.0d, 10).getLowerBound(), 0.0);
  }

  /**
   * Test {@link AbstractRealIntegral#getUpperBound()}.
   *
   * <p>Method under test: {@link AbstractRealIntegral#getUpperBound()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractRealIntegral.getUpperBound()"})
  public void testGetUpperBound() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new MonteCarloIntegrator(10.0d, 10.0d, 10).getUpperBound(), 0.0);
  }
}
