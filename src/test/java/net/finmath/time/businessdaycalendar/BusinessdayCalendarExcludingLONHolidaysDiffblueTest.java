package net.finmath.time.businessdaycalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BusinessdayCalendarExcludingLONHolidaysDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then BaseCalendar return {@link BusinessdayCalendarAny}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       BusinessdayCalendarExcludingLONHolidays#BusinessdayCalendarExcludingLONHolidays(BusinessdayCalendar)}
   *   <li>{@link BusinessdayCalendarExcludingLONHolidays#getHolidays()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarExcludingLONHolidays.<init>()",
    "void BusinessdayCalendarExcludingLONHolidays.<init>(BusinessdayCalendar)",
    "Set BusinessdayCalendarExcludingLONHolidays.getHolidays()"
  })
  public void testGettersAndSetters_thenBaseCalendarReturnBusinessdayCalendarAny() {
    // Arrange
    BusinessdayCalendarAny baseCalendar = new BusinessdayCalendarAny();

    // Act
    BusinessdayCalendarExcludingLONHolidays actualBusinessdayCalendarExcludingLONHolidays =
        new BusinessdayCalendarExcludingLONHolidays(baseCalendar);
    Set<LocalDate> actualHolidays = actualBusinessdayCalendarExcludingLONHolidays.getHolidays();

    // Assert
    BusinessdayCalendar baseCalendar2 =
        actualBusinessdayCalendarExcludingLONHolidays.getBaseCalendar();
    assertTrue(baseCalendar2 instanceof BusinessdayCalendarAny);
    assertEquals("London", actualBusinessdayCalendarExcludingLONHolidays.getName());
    assertEquals(491, actualHolidays.size());
    assertTrue(actualBusinessdayCalendarExcludingLONHolidays.isExcludingWeekends());
    assertSame(baseCalendar, baseCalendar2);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return BaseCalendar is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BusinessdayCalendarExcludingLONHolidays#BusinessdayCalendarExcludingLONHolidays()}
   *   <li>{@link BusinessdayCalendarExcludingLONHolidays#getHolidays()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarExcludingLONHolidays.<init>()",
    "void BusinessdayCalendarExcludingLONHolidays.<init>(BusinessdayCalendar)",
    "Set BusinessdayCalendarExcludingLONHolidays.getHolidays()"
  })
  public void testGettersAndSetters_thenReturnBaseCalendarIsNull() {
    // Arrange and Act
    BusinessdayCalendarExcludingLONHolidays actualBusinessdayCalendarExcludingLONHolidays =
        new BusinessdayCalendarExcludingLONHolidays();
    Set<LocalDate> actualHolidays = actualBusinessdayCalendarExcludingLONHolidays.getHolidays();

    // Assert
    assertEquals("London", actualBusinessdayCalendarExcludingLONHolidays.getName());
    assertNull(actualBusinessdayCalendarExcludingLONHolidays.getBaseCalendar());
    assertEquals(491, actualHolidays.size());
    assertTrue(actualBusinessdayCalendarExcludingLONHolidays.isExcludingWeekends());
  }
}
