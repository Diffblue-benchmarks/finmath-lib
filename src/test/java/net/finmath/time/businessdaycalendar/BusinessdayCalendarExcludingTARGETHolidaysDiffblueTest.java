package net.finmath.time.businessdaycalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BusinessdayCalendarExcludingTARGETHolidaysDiffblueTest {
  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingTARGETHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingTARGETHolidays(new BusinessdayCalendarAny())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingTARGETHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday2() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingTARGETHolidays(
                new BusinessdayCalendarExcludingTARGETHolidays())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingTARGETHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday_whenLocalDateWith1970AndOneAndOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingTARGETHolidays().isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingTARGETHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday_whenOfEpochDayOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new BusinessdayCalendarExcludingTARGETHolidays().isBusinessday(LocalDate.ofEpochDay(1L)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay twenty-five.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingTARGETHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingTARGETHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday_whenOfEpochDayTwentyFive_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new BusinessdayCalendarExcludingTARGETHolidays().isBusinessday(LocalDate.ofEpochDay(25L)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#isEasterSunday(LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingTARGETHolidays#isEasterSunday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean BusinessdayCalendarExcludingTARGETHolidays.isEasterSunday(LocalDate)"
  })
  public void testIsEasterSunday_whenLocalDateWith1970AndOneAndOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        BusinessdayCalendarExcludingTARGETHolidays.isEasterSunday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingTARGETHolidays#toString()}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingTARGETHolidays#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BusinessdayCalendarExcludingTARGETHolidays.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "BusinessdayCalendarExcludingTARGETHolidays [baseCalendar=null]",
        new BusinessdayCalendarExcludingTARGETHolidays().toString());
  }
}
