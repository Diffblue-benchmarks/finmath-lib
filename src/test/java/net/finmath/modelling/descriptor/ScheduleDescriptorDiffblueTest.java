package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import net.finmath.time.Period;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.ScheduleGenerator;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.businessdaycalendar.AbstractBusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScheduleDescriptorDiffblueTest {
  /**
   * Test {@link ScheduleDescriptor#ScheduleDescriptor(LocalDate, LocalDate, Frequency,
   * DaycountConvention, ShortPeriodConvention, DateRollConvention, AbstractBusinessdayCalendar,
   * int, int)}.
   *
   * <p>Method under test: {@link ScheduleDescriptor#ScheduleDescriptor(LocalDate, LocalDate,
   * ScheduleGenerator.Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * AbstractBusinessdayCalendar, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScheduleDescriptor.<init>(LocalDate, LocalDate, ScheduleGenerator.Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, AbstractBusinessdayCalendar, int, int)"
  })
  public void testNewScheduleDescriptor() {
    // Arrange
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    ScheduleDescriptor actualScheduleDescriptor =
        new ScheduleDescriptor(
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
    assertEquals(0, actualScheduleDescriptor.getNumberOfPeriods());
    assertTrue(actualScheduleDescriptor.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleDescriptor#ScheduleDescriptor(LocalDate, LocalDate, Frequency,
   * DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int,
   * boolean)}.
   *
   * <p>Method under test: {@link ScheduleDescriptor#ScheduleDescriptor(LocalDate, LocalDate,
   * ScheduleGenerator.Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention,
   * BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScheduleDescriptor.<init>(LocalDate, LocalDate, ScheduleGenerator.Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void testNewScheduleDescriptor2() {
    // Arrange
    LocalDate startDate = LocalDate.of(1970, 1, 1);
    LocalDate maturityDate = LocalDate.of(1970, 1, 1);

    // Act
    ScheduleDescriptor actualScheduleDescriptor =
        new ScheduleDescriptor(
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
    assertEquals(0, actualScheduleDescriptor.getNumberOfPeriods());
    assertTrue(actualScheduleDescriptor.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleDescriptor#ScheduleDescriptor(List, DayCountConvention)}.
   *
   * <ul>
   *   <li>Then return NumberOfPeriods is one.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleDescriptor#ScheduleDescriptor(List, DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleDescriptor.<init>(List, DayCountConvention)"})
  public void testNewScheduleDescriptor_thenReturnNumberOfPeriodsIsOne() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period);

    // Act
    ScheduleDescriptor actualScheduleDescriptor =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));

    // Assert
    assertEquals(1, actualScheduleDescriptor.getNumberOfPeriods());
    assertSame(periods, actualScheduleDescriptor.getPeriods());
  }

  /**
   * Test {@link ScheduleDescriptor#ScheduleDescriptor(List, DayCountConvention)}.
   *
   * <ul>
   *   <li>Then return Periods size is two.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleDescriptor#ScheduleDescriptor(List, DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleDescriptor.<init>(List, DayCountConvention)"})
  public void testNewScheduleDescriptor_thenReturnPeriodsSizeIsTwo() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period);
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period2);

    // Act
    ScheduleDescriptor actualScheduleDescriptor =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));

    // Assert
    List<Period> periods2 = actualScheduleDescriptor.getPeriods();
    assertEquals(2, periods2.size());
    assertEquals(2, actualScheduleDescriptor.getNumberOfPeriods());
    assertSame(period2, periods2.get(1));
  }

  /**
   * Test {@link ScheduleDescriptor#ScheduleDescriptor(List, DayCountConvention)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return NumberOfPeriods is zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleDescriptor#ScheduleDescriptor(List, DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleDescriptor.<init>(List, DayCountConvention)"})
  public void testNewScheduleDescriptor_whenArrayList_thenReturnNumberOfPeriodsIsZero() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();

    // Act
    ScheduleDescriptor actualScheduleDescriptor =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));

    // Assert
    assertEquals(0, actualScheduleDescriptor.getNumberOfPeriods());
    assertTrue(actualScheduleDescriptor.getPeriods().isEmpty());
  }

  /**
   * Test {@link ScheduleDescriptor#getSchedule(LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@link ScheduleFromPeriods}.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleDescriptor#getSchedule(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule ScheduleDescriptor.getSchedule(LocalDate)"})
  public void testGetSchedule_thenReturnScheduleFromPeriods() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    DayCountConvention_30E_360 daycountConvention = new DayCountConvention_30E_360(true);

    ScheduleDescriptor scheduleDescriptor = new ScheduleDescriptor(periods, daycountConvention);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualSchedule = scheduleDescriptor.getSchedule(referenceDate);

    // Assert
    assertTrue(actualSchedule instanceof ScheduleFromPeriods);
    DayCountConvention daycountconvention = actualSchedule.getDaycountconvention();
    assertTrue(daycountconvention instanceof DayCountConvention_30E_360);
    assertEquals(0, actualSchedule.getNumberOfPeriods());
    assertFalse(actualSchedule.iterator().hasNext());
    assertTrue(actualSchedule.getPeriods().isEmpty());
    assertSame(daycountConvention, daycountconvention);
    assertSame(referenceDate, actualSchedule.getReferenceDate());
  }

  /**
   * Test {@link ScheduleDescriptor#getNumberOfPeriods()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleDescriptor#getNumberOfPeriods()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ScheduleDescriptor.getNumberOfPeriods()"})
  public void testGetNumberOfPeriods_thenReturnZero() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    ScheduleDescriptor scheduleDescriptor =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0, scheduleDescriptor.getNumberOfPeriods());
  }

  /**
   * Test {@link ScheduleDescriptor#getPeriods()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleDescriptor#getPeriods()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ScheduleDescriptor.getPeriods()"})
  public void testGetPeriods_thenReturnEmpty() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    ScheduleDescriptor scheduleDescriptor =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertTrue(scheduleDescriptor.getPeriods().isEmpty());
  }
}
