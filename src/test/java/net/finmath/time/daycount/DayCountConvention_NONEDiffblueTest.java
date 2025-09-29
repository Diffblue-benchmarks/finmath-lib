package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_NONEDiffblueTest {
  /**
   * Test {@link DayCountConvention_NONE#getDaycount(LocalDate, LocalDate)}.
   *
   * <p>Method under test: {@link DayCountConvention_NONE#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_NONE.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_NONE()
            .getDaycount(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_NONE#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <p>Method under test: {@link DayCountConvention_NONE#getDaycountFraction(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_NONE.getDaycountFraction(LocalDate, LocalDate)"})
  public void testGetDaycountFraction() {
    // Arrange, Act and Assert
    assertEquals(
        1.0d,
        new DayCountConvention_NONE()
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }
}
