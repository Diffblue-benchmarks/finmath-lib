package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DigitalOptionDeltaLikelihoodDiffblueTest {
  /**
   * Test {@link DigitalOptionDeltaLikelihood#DigitalOptionDeltaLikelihood(double, double)}.
   *
   * <p>Method under test: {@link DigitalOptionDeltaLikelihood#DigitalOptionDeltaLikelihood(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DigitalOptionDeltaLikelihood.<init>(double, double)"})
  public void testNewDigitalOptionDeltaLikelihood() {
    // Arrange, Act and Assert
    assertNull(new DigitalOptionDeltaLikelihood(10.0d, 10.0d).getCurrency());
  }
}
