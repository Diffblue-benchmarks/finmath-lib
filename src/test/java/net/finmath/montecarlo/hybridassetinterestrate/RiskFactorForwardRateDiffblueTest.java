package net.finmath.montecarlo.hybridassetinterestrate;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RiskFactorForwardRateDiffblueTest {
  /**
   * Test {@link RiskFactorForwardRate#RiskFactorForwardRate(String, double, double)}.
   *
   * <p>Method under test: {@link RiskFactorForwardRate#RiskFactorForwardRate(String, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RiskFactorForwardRate.<init>(String, double, double)"})
  public void testNewRiskFactorForwardRate() {
    // Arrange and Act
    RiskFactorForwardRate actualRiskFactorForwardRate =
        new RiskFactorForwardRate("Name", 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualRiskFactorForwardRate.getName());
    assertEquals(10.0d, actualRiskFactorForwardRate.getPeriodEnd(), 0.0);
    assertEquals(10.0d, actualRiskFactorForwardRate.getPeriodStart(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RiskFactorForwardRate#getName()}
   *   <li>{@link RiskFactorForwardRate#getPeriodEnd()}
   *   <li>{@link RiskFactorForwardRate#getPeriodStart()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String RiskFactorForwardRate.getName()",
    "double RiskFactorForwardRate.getPeriodEnd()",
    "double RiskFactorForwardRate.getPeriodStart()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RiskFactorForwardRate riskFactorForwardRate = new RiskFactorForwardRate("Name", 10.0d, 10.0d);

    // Act
    String actualName = riskFactorForwardRate.getName();
    double actualPeriodEnd = riskFactorForwardRate.getPeriodEnd();

    // Assert
    assertEquals("Name", actualName);
    assertEquals(10.0d, actualPeriodEnd, 0.0);
    assertEquals(10.0d, riskFactorForwardRate.getPeriodStart(), 0.0);
  }
}
