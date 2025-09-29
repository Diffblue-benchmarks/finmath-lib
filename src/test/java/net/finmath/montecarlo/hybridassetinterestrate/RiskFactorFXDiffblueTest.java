package net.finmath.montecarlo.hybridassetinterestrate;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RiskFactorFXDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RiskFactorFX#RiskFactorFX(String)}
   *   <li>{@link RiskFactorFX#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RiskFactorFX.<init>(String)", "String RiskFactorFX.getName()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Name", new RiskFactorFX("Name").getName());
  }
}
