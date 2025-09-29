package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwaptionFactoryDiffblueTest {
  /**
   * Test {@link SwaptionFactory#createSwaption(String, double, TimeDiscretization, String)}.
   *
   * <ul>
   *   <li>When {@code Class Name}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionFactory#createSwaption(String, double, TimeDiscretization,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.interestrate.products.TermStructureMonteCarloProduct SwaptionFactory.createSwaption(String, double, TimeDiscretization, String)"
  })
  public void testCreateSwaption_whenClassName_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            SwaptionFactory.createSwaption(
                "Class Name", 10.0d, new TenorFromArray(10.0d, 10, 0.5d), "42"));
  }
}
