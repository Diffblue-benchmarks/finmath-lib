package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NumerairePerformanceOnScheduleIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NumerairePerformanceOnScheduleIndex#NumerairePerformanceOnScheduleIndex(String,
   *       String, Schedule)}
   *   <li>{@link NumerairePerformanceOnScheduleIndex#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NumerairePerformanceOnScheduleIndex.<init>(String, String, Schedule)",
    "String NumerairePerformanceOnScheduleIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    NumerairePerformanceOnScheduleIndex actualNumerairePerformanceOnScheduleIndex =
        new NumerairePerformanceOnScheduleIndex(
            "Name", "GBP", new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    actualNumerairePerformanceOnScheduleIndex.toString();

    // Assert
    assertEquals("GBP", actualNumerairePerformanceOnScheduleIndex.getCurrency());
    assertEquals("Name", actualNumerairePerformanceOnScheduleIndex.getName());
  }

  /**
   * Test {@link NumerairePerformanceOnScheduleIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link NumerairePerformanceOnScheduleIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set NumerairePerformanceOnScheduleIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    NumerairePerformanceOnScheduleIndex numerairePerformanceOnScheduleIndex =
        new NumerairePerformanceOnScheduleIndex(
            "Name", "GBP", new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    // Act
    Set<String> actualQueryUnderlyingsResult =
        numerairePerformanceOnScheduleIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
