package net.finmath.time.daycount;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_UNKNOWNDiffblueTest {
  /**
   * Test {@link DayCountConvention_UNKNOWN#getDaycount(LocalDate, LocalDate)}.
   *
   * <p>Method under test: {@link DayCountConvention_UNKNOWN#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_UNKNOWN.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DayCountConvention_UNKNOWN("Name")
                .getDaycount(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link DayCountConvention_UNKNOWN#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <p>Method under test: {@link DayCountConvention_UNKNOWN#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_UNKNOWN.getDaycountFraction(LocalDate, LocalDate)"})
  public void testGetDaycountFraction() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DayCountConvention_UNKNOWN("Name")
                .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)));
  }
}
