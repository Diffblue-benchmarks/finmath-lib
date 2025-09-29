package net.finmath.time.businessdaycalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BusinessdayCalendarExcludingGivenHolidaysDiffblueTest {
  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#getName()}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingGivenHolidays#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BusinessdayCalendarExcludingGivenHolidays.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("London", new BusinessdayCalendarExcludingLONHolidays().getName());
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#getBaseCalendar()}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingGivenHolidays#getBaseCalendar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.businessdaycalendar.BusinessdayCalendar BusinessdayCalendarExcludingGivenHolidays.getBaseCalendar()"
  })
  public void testGetBaseCalendar() {
    // Arrange, Act and Assert
    assertNull(new BusinessdayCalendarExcludingLONHolidays().getBaseCalendar());
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#isExcludingWeekends()}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingGivenHolidays#isExcludingWeekends()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingGivenHolidays.isExcludingWeekends()"})
  public void testIsExcludingWeekends() {
    // Arrange, Act and Assert
    assertTrue(new BusinessdayCalendarExcludingLONHolidays().isExcludingWeekends());
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingGivenHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday() {
    // Arrange, Act and Assert
    assertTrue(
        new BusinessdayCalendarExcludingLONHolidays(new BusinessdayCalendarAny())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingGivenHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday2() {
    // Arrange, Act and Assert
    assertTrue(
        new BusinessdayCalendarExcludingLONHolidays(new BusinessdayCalendarExcludingLONHolidays())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>Given {@link
   *       BusinessdayCalendarExcludingLONHolidays#BusinessdayCalendarExcludingLONHolidays()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingGivenHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday_givenBusinessdayCalendarExcludingLONHolidays_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new BusinessdayCalendarExcludingLONHolidays().isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BusinessdayCalendarExcludingGivenHolidays#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingGivenHolidays.isBusinessday(LocalDate)"})
  public void testIsBusinessday_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingLONHolidays(
                new BusinessdayCalendarExcludingTARGETHolidays())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#toString()}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingGivenHolidays#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BusinessdayCalendarExcludingGivenHolidays.toString()"})
  public void testToString_thenReturnAString() {
    // Arrange, Act and Assert
    assertEquals(
        "BusinessdayCalendarExcludingLONHolidays [baseCalendar=BusinessdayCalendarExcludingLONHolidays"
            + " [baseCalendar=null]]",
        new BusinessdayCalendarExcludingLONHolidays(new BusinessdayCalendarExcludingLONHolidays())
            .toString());
  }

  /**
   * Test {@link BusinessdayCalendarExcludingGivenHolidays#toString()}.
   *
   * <ul>
   *   <li>Then return {@code BusinessdayCalendarExcludingLONHolidays [baseCalendar=null]}.
   * </ul>
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingGivenHolidays#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BusinessdayCalendarExcludingGivenHolidays.toString()"})
  public void testToString_thenReturnBusinessdayCalendarExcludingLONHolidaysBaseCalendarNull() {
    // Arrange, Act and Assert
    assertEquals(
        "BusinessdayCalendarExcludingLONHolidays [baseCalendar=null]",
        new BusinessdayCalendarExcludingLONHolidays().toString());
  }
}
