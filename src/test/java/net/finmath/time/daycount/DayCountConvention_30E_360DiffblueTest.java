package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_30E_360DiffblueTest {
  /**
   * Test {@link DayCountConvention_30E_360#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Given {@link DayCountConvention_30E_360#DayCountConvention_30E_360(boolean)} with
   *       is30Eplus360 is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_givenDayCountConvention_30E_360WithIs30Eplus360IsFalse() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_30E_360(false)
            .getDaycount(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_30E_360(true)
            .getDaycount(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Given {@link DayCountConvention_30E_360#DayCountConvention_30E_360(boolean)} with
   *       is30Eplus360 is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360.getDaycountFraction(LocalDate, LocalDate)"})
  public void testGetDaycountFraction_givenDayCountConvention_30E_360WithIs30Eplus360IsFalse() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_30E_360(false)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360.getDaycountFraction(LocalDate, LocalDate)"})
  public void testGetDaycountFraction_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_30E_360(true)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }
}
