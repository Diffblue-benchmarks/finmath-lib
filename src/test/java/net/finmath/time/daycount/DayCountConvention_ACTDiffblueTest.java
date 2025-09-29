package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_ACTDiffblueTest {
  /**
   * Test {@link DayCountConvention_ACT#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_ACT.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_whenLocalDateWith1970AndOneAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_ACT_360()
            .getDaycount(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT#daysBetween(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT#daysBetween(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_ACT.daysBetween(LocalDate, LocalDate)"})
  public void testDaysBetween_whenLocalDateWith1970AndOneAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        DayCountConvention_ACT.daysBetween(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }
}
