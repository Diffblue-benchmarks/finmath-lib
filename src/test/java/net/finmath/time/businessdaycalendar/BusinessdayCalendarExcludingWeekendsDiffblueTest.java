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

public class BusinessdayCalendarExcludingWeekendsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BusinessdayCalendarExcludingWeekends#BusinessdayCalendarExcludingWeekends()}
   *   <li>{@link BusinessdayCalendarExcludingWeekends#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarExcludingWeekends.<init>()",
    "void BusinessdayCalendarExcludingWeekends.<init>(BusinessdayCalendar)",
    "java.lang.String BusinessdayCalendarExcludingWeekends.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "BusinessdayCalendarExcludingWeekends [baseCalendar=null]",
        new BusinessdayCalendarExcludingWeekends().toString());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       BusinessdayCalendarExcludingWeekends#BusinessdayCalendarExcludingWeekends(BusinessdayCalendar)}
   *   <li>{@link BusinessdayCalendarExcludingWeekends#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarExcludingWeekends.<init>()",
    "void BusinessdayCalendarExcludingWeekends.<init>(BusinessdayCalendar)",
    "java.lang.String BusinessdayCalendarExcludingWeekends.toString()"
  })
  public void testGettersAndSetters2() {
    // Arrange, Act and Assert
    assertEquals(
        "BusinessdayCalendarExcludingWeekends [baseCalendar=BusinessdayCalendarAny]",
        new BusinessdayCalendarExcludingWeekends(new BusinessdayCalendarAny()).toString());
  }

  /**
   * Test {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingWeekends.isBusinessday(LocalDate)"})
  public void testIsBusinessday() {
    // Arrange, Act and Assert
    assertTrue(
        new BusinessdayCalendarExcludingWeekends(new BusinessdayCalendarAny())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingWeekends.isBusinessday(LocalDate)"})
  public void testIsBusinessday2() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingWeekends(new BusinessdayCalendarExcludingTARGETHolidays())
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingWeekends.isBusinessday(LocalDate)"})
  public void testIsBusinessday3() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingWeekends(
                new BusinessdayCalendarExcludingTARGETHolidays(
                    new BusinessdayCalendarExcludingTARGETHolidays()))
            .isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>Given {@link
   *       BusinessdayCalendarExcludingWeekends#BusinessdayCalendarExcludingWeekends()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingWeekends.isBusinessday(LocalDate)"})
  public void testIsBusinessday_givenBusinessdayCalendarExcludingWeekends_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new BusinessdayCalendarExcludingWeekends().isBusinessday(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}.
   *
   * <ul>
   *   <li>When ofYearDay zero and one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link BusinessdayCalendarExcludingWeekends#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarExcludingWeekends.isBusinessday(LocalDate)"})
  public void testIsBusinessday_whenOfYearDayZeroAndOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new BusinessdayCalendarExcludingWeekends().isBusinessday(LocalDate.ofYearDay(0, 1)));
  }
}
