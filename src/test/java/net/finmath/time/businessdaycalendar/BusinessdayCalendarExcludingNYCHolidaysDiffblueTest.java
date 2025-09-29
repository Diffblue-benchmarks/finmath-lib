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

public class BusinessdayCalendarExcludingNYCHolidaysDiffblueTest {
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
   *       BusinessdayCalendarExcludingNYCHolidays#BusinessdayCalendarExcludingNYCHolidays(BusinessdayCalendar)}
   *   <li>{@link BusinessdayCalendarExcludingNYCHolidays#getHolidays()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarExcludingNYCHolidays.<init>()",
    "void BusinessdayCalendarExcludingNYCHolidays.<init>(BusinessdayCalendar)",
    "Set BusinessdayCalendarExcludingNYCHolidays.getHolidays()"
  })
  public void testGettersAndSetters_thenBaseCalendarReturnBusinessdayCalendarAny() {
    // Arrange
    BusinessdayCalendarAny baseCalendar = new BusinessdayCalendarAny();

    // Act
    BusinessdayCalendarExcludingNYCHolidays actualBusinessdayCalendarExcludingNYCHolidays =
        new BusinessdayCalendarExcludingNYCHolidays(baseCalendar);
    Set<LocalDate> actualHolidays = actualBusinessdayCalendarExcludingNYCHolidays.getHolidays();

    // Assert
    BusinessdayCalendar baseCalendar2 =
        actualBusinessdayCalendarExcludingNYCHolidays.getBaseCalendar();
    assertTrue(baseCalendar2 instanceof BusinessdayCalendarAny);
    assertEquals("New York", actualBusinessdayCalendarExcludingNYCHolidays.getName());
    assertEquals(546, actualHolidays.size());
    assertTrue(actualBusinessdayCalendarExcludingNYCHolidays.isExcludingWeekends());
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
   *   <li>{@link BusinessdayCalendarExcludingNYCHolidays#BusinessdayCalendarExcludingNYCHolidays()}
   *   <li>{@link BusinessdayCalendarExcludingNYCHolidays#getHolidays()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarExcludingNYCHolidays.<init>()",
    "void BusinessdayCalendarExcludingNYCHolidays.<init>(BusinessdayCalendar)",
    "Set BusinessdayCalendarExcludingNYCHolidays.getHolidays()"
  })
  public void testGettersAndSetters_thenReturnBaseCalendarIsNull() {
    // Arrange and Act
    BusinessdayCalendarExcludingNYCHolidays actualBusinessdayCalendarExcludingNYCHolidays =
        new BusinessdayCalendarExcludingNYCHolidays();
    Set<LocalDate> actualHolidays = actualBusinessdayCalendarExcludingNYCHolidays.getHolidays();

    // Assert
    assertEquals("New York", actualBusinessdayCalendarExcludingNYCHolidays.getName());
    assertNull(actualBusinessdayCalendarExcludingNYCHolidays.getBaseCalendar());
    assertEquals(546, actualHolidays.size());
    assertTrue(actualBusinessdayCalendarExcludingNYCHolidays.isExcludingWeekends());
  }
}
