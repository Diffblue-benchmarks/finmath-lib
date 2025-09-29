package net.finmath.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import net.finmath.time.daycount.DayCountConvention_30E_360_ISDA;
import net.finmath.time.daycount.DayCountConvention_30U_360;
import net.finmath.time.daycount.DayCountConvention_ACT_360;
import net.finmath.time.daycount.DayCountConvention_ACT_365;
import net.finmath.time.daycount.DayCountConvention_ACT_ACT_AFB;
import net.finmath.time.daycount.DayCountConvention_ACT_ACT_ISDA;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScheduleGeneratorDiffblueTest {
  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.U30_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30U_360);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_360);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_365,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_365);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_ACT_AFB,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_ACT_AFB);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_ACT_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_ACT_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt9() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt10() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.MONTHLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt11() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt12() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.SEMIANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt13() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.ANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt14() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.TENOR,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt15() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertEquals("1970-01-01", getResult.getPeriodStart().toString());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt16() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertSame(startDate, getResult.getPeriodStart());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt17() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertEquals("1970-01-02", getResult.getPeriodEnd().toString());
    assertSame(startDate, getResult.getPeriodStart());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code Frequency},
   * {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code DateRollConvention}, {@code
   * BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntInt18() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertSame(startDate, getResult.getPeriodStart());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.U30_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30U_360);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_360);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_365,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_365);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_ACT_AFB,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_ACT_AFB);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.ACT_ACT_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_ACT_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean9() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean10() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.MONTHLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean11() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean12() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.SEMIANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean13() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.ANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean14() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.TENOR,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualCreateScheduleFromConventionsResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    assertFalse(actualCreateScheduleFromConventionsResult.iterator().hasNext());
    assertTrue(actualCreateScheduleFromConventionsResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean15() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertEquals("1970-01-01", getResult.getPeriodStart().toString());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean16() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertSame(startDate, getResult.getPeriodStart());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean17() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertEquals("1970-01-02", getResult.getPeriodEnd().toString());
    assertSame(startDate, getResult.getPeriodStart());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean18() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertEquals("1970-01-01", getResult.getPeriodStart().toString());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, LocalDate, LocalDate,
   * Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar,
   * int, int, boolean)} with {@code LocalDate}, {@code LocalDate}, {@code LocalDate}, {@code
   * Frequency}, {@code DaycountConvention}, {@code ShortPeriodConvention}, {@code
   * DateRollConvention}, {@code BusinessdayCalendar}, {@code int}, {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate,
   * LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, LocalDate, LocalDate, Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateLocalDateLocalDateFrequencyDaycountConventionShortPeriodConventionDateRollConventionBusinessdayCalendarIntIntBoolean19() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.ofEpochDay(1L);

    // Act
    Schedule actualCreateScheduleFromConventionsResult =
        ScheduleGenerator.createScheduleFromConventions(
            referenceDate,
            startDate,
            maturityDate,
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Assert
    assertTrue(actualCreateScheduleFromConventionsResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualCreateScheduleFromConventionsResult.getNumberOfPeriods());
    List<Period> periods = actualCreateScheduleFromConventionsResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-02", getResult.getFixing().toString());
    assertEquals("1970-01-03", getResult.getPayment().toString());
    assertSame(maturityDate, getResult.getPeriodEnd());
    assertSame(startDate, getResult.getPeriodStart());
    Iterator<Period> iteratorResult = actualCreateScheduleFromConventionsResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, String, String, String,
   * String, String, String, String, BusinessdayCalendar, int, int)} with {@code LocalDate}, {@code
   * String}, {@code String}, {@code String}, {@code String}, {@code String}, {@code String}, {@code
   * String}, {@code BusinessdayCalendar}, {@code int}, {@code int}.
   *
   * <p>Method under test: {@link ScheduleGenerator#createScheduleFromConventions(LocalDate, String,
   * String, String, String, String, String, String, BusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule ScheduleGenerator.createScheduleFromConventions(LocalDate, String, String, String, String, String, String, String, BusinessdayCalendar, int, int)"
  })
  public void
      testCreateScheduleFromConventionsWithLocalDateStringStringStringStringStringStringStringBusinessdayCalendarIntInt() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            ScheduleGenerator.createScheduleFromConventions(
                referenceDate,
                "42",
                "Start Offset String",
                "Maturity String",
                "Frequency",
                "3",
                "Short Period Convention",
                "2020-03-01",
                new BusinessdayCalendarAny(),
                1,
                1));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code 30e/360}.
   *   <li>Then return {@code E30_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_when30e360_thenReturnE30360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.E30_360, DaycountConvention.getEnum("30e/360"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code 30u/360}.
   *   <li>Then return {@code U30_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_when30u360_thenReturnU30360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.U30_360, DaycountConvention.getEnum("30u/360"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code 30/360}.
   *   <li>Then return {@code E30_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_when30360_thenReturnE30360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.E30_360, DaycountConvention.getEnum("30/360"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code ACT_360}.
   *   <li>Then return {@code ACT_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenAct360_thenReturnAct360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.ACT_360, DaycountConvention.getEnum("ACT_360"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code act/360}.
   *   <li>Then return {@code ACT_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenAct360_thenReturnAct3602() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.ACT_360, DaycountConvention.getEnum("act/360"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code act/365}.
   *   <li>Then return {@code ACT_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenAct365_thenReturnAct360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.ACT_360, DaycountConvention.getEnum("act/365"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code act/act}.
   *   <li>Then return {@code ACT_ACT}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenActAct_thenReturnActAct() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.ACT_ACT, DaycountConvention.getEnum("act/act"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code e30/360}.
   *   <li>Then return {@code E30_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenE30360_thenReturnE30360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.E30_360, DaycountConvention.getEnum("e30/360"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DaycountConvention.getEnum(null));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DaycountConvention.getEnum("String"));
  }

  /**
   * Test DaycountConvention {@link DaycountConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code u30/360}.
   *   <li>Then return {@code U30_360}.
   * </ul>
   *
   * <p>Method under test: {@link DaycountConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DaycountConvention DaycountConvention.getEnum(String)"})
  public void testDaycountConventionGetEnum_whenU30360_thenReturnU30360() {
    // Arrange, Act and Assert
    assertEquals(DaycountConvention.U30_360, DaycountConvention.getEnum("u30/360"));
  }
}
