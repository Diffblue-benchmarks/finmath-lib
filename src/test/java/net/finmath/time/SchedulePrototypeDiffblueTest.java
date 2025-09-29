package net.finmath.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.finmath.modelling.descriptor.ScheduleDescriptor;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.SchedulePrototype.OffsetUnit;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import net.finmath.time.daycount.DayCountConvention_30E_360_ISDA;
import net.finmath.time.daycount.DayCountConvention_30U_360;
import net.finmath.time.daycount.DayCountConvention_ACT_360;
import net.finmath.time.daycount.DayCountConvention_ACT_365;
import net.finmath.time.daycount.DayCountConvention_ACT_ACT_AFB;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SchedulePrototypeDiffblueTest {
  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromSchedule(Schedule)}.
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromSchedule(Schedule)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromSchedule(Schedule)"})
  public void testGetOffsetCodeFromSchedule() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals("0M", SchedulePrototype.getOffsetCodeFromSchedule(schedule));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromSchedule(Schedule)}.
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromSchedule(Schedule)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromSchedule(Schedule)"})
  public void testGetOffsetCodeFromSchedule2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    // Act and Assert
    assertEquals("0M", SchedulePrototype.getOffsetCodeFromSchedule(schedule));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromSchedule(Schedule)}.
   *
   * <ul>
   *   <li>Then return {@code 6M}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromSchedule(Schedule)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromSchedule(Schedule)"})
  public void testGetOffsetCodeFromSchedule_thenReturn6m() {
    // Arrange, Act and Assert
    assertEquals(
        "6M",
        SchedulePrototype.getOffsetCodeFromSchedule(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}.
   *
   * <ul>
   *   <li>When {@code 42(?<=\D)(?=\d)}.
   *   <li>Then return {@code 42D}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromCurveName(String)"})
  public void testGetOffsetCodeFromCurveName_when42DD_thenReturn42d() {
    // Arrange, Act and Assert
    assertEquals("42D", SchedulePrototype.getOffsetCodeFromCurveName("42(?<=\\D)(?=\\d)"));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromCurveName(String)"})
  public void testGetOffsetCodeFromCurveName_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", SchedulePrototype.getOffsetCodeFromCurveName("42"));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}.
   *
   * <ul>
   *   <li>When {@code 4242(?<=\D)(?=\d)}.
   *   <li>Then return {@code 4242D}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromCurveName(String)"})
  public void testGetOffsetCodeFromCurveName_when4242DD_thenReturn4242d() {
    // Arrange, Act and Assert
    assertEquals("4242D", SchedulePrototype.getOffsetCodeFromCurveName("4242(?<=\\D)(?=\\d)"));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}.
   *
   * <ul>
   *   <li>When {@code Curve Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromCurveName(String)"})
  public void testGetOffsetCodeFromCurveName_whenCurveName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SchedulePrototype.getOffsetCodeFromCurveName("Curve Name"));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromCurveName(String)"})
  public void testGetOffsetCodeFromCurveName_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SchedulePrototype.getOffsetCodeFromCurveName(""));
  }

  /**
   * Test {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulePrototype#getOffsetCodeFromCurveName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SchedulePrototype.getOffsetCodeFromCurveName(String)"})
  public void testGetOffsetCodeFromCurveName_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SchedulePrototype.getOffsetCodeFromCurveName(null));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SchedulePrototype#SchedulePrototype(Frequency, DaycountConvention,
   *       ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)}
   *   <li>{@link SchedulePrototype#getBusinessdayCalendar()}
   *   <li>{@link SchedulePrototype#getDateRollConvention()}
   *   <li>{@link SchedulePrototype#getDaycountConvention()}
   *   <li>{@link SchedulePrototype#getFixingOffsetDays()}
   *   <li>{@link SchedulePrototype#getFrequency()}
   *   <li>{@link SchedulePrototype#getPaymentOffsetDays()}
   *   <li>{@link SchedulePrototype#getShortPeriodConvention()}
   *   <li>{@link SchedulePrototype#isUseEndOfMonth()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SchedulePrototype.<init>(Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)",
    "BusinessdayCalendar SchedulePrototype.getBusinessdayCalendar()",
    "DateRollConvention SchedulePrototype.getDateRollConvention()",
    "DaycountConvention SchedulePrototype.getDaycountConvention()",
    "int SchedulePrototype.getFixingOffsetDays()",
    "Frequency SchedulePrototype.getFrequency()",
    "int SchedulePrototype.getPaymentOffsetDays()",
    "ShortPeriodConvention SchedulePrototype.getShortPeriodConvention()",
    "boolean SchedulePrototype.isUseEndOfMonth()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BusinessdayCalendarAny businessdayCalendar = new BusinessdayCalendarAny();

    // Act
    SchedulePrototype actualSchedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            businessdayCalendar,
            1,
            1,
            true);
    BusinessdayCalendar actualBusinessdayCalendar =
        actualSchedulePrototype.getBusinessdayCalendar();
    DateRollConvention actualDateRollConvention = actualSchedulePrototype.getDateRollConvention();
    DaycountConvention actualDaycountConvention = actualSchedulePrototype.getDaycountConvention();
    int actualFixingOffsetDays = actualSchedulePrototype.getFixingOffsetDays();
    Frequency actualFrequency = actualSchedulePrototype.getFrequency();
    int actualPaymentOffsetDays = actualSchedulePrototype.getPaymentOffsetDays();
    ShortPeriodConvention actualShortPeriodConvention =
        actualSchedulePrototype.getShortPeriodConvention();

    // Assert
    assertTrue(actualBusinessdayCalendar instanceof BusinessdayCalendarAny);
    assertEquals(1, actualFixingOffsetDays);
    assertEquals(1, actualPaymentOffsetDays);
    assertEquals(DaycountConvention.E30_360_ISDA, actualDaycountConvention);
    assertEquals(Frequency.DAILY, actualFrequency);
    assertEquals(ShortPeriodConvention.FIRST, actualShortPeriodConvention);
    assertEquals(DateRollConvention.UNADJUSTED, actualDateRollConvention);
    assertTrue(actualSchedulePrototype.isUseEndOfMonth());
    assertSame(businessdayCalendar, actualBusinessdayCalendar);
  }

  /**
   * Test {@link SchedulePrototype#generateScheduleDescriptor(LocalDate, LocalDate)}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateScheduleDescriptor(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ScheduleDescriptor SchedulePrototype.generateScheduleDescriptor(LocalDate, LocalDate)"
  })
  public void testGenerateScheduleDescriptor() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    ScheduleDescriptor actualGenerateScheduleDescriptorResult =
        schedulePrototype.generateScheduleDescriptor(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(0, actualGenerateScheduleDescriptorResult.getNumberOfPeriods());
    assertTrue(actualGenerateScheduleDescriptorResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link SchedulePrototype#generateScheduleDescriptor(LocalDate, LocalDate)}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateScheduleDescriptor(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ScheduleDescriptor SchedulePrototype.generateScheduleDescriptor(LocalDate, LocalDate)"
  })
  public void testGenerateScheduleDescriptor2() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Act
    ScheduleDescriptor actualGenerateScheduleDescriptorResult =
        schedulePrototype.generateScheduleDescriptor(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(0, actualGenerateScheduleDescriptorResult.getNumberOfPeriods());
    assertTrue(actualGenerateScheduleDescriptorResult.getPeriods().isEmpty());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination2() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(4, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(4, periods.size());
    Period expectedNextResult = periods.get(0);
    Period expectedNextResult2 = periods.get(1);
    Period expectedNextResult3 = periods.get(2);
    Period expectedNextResult4 = periods.get(3);
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    Period actualNextResult2 = iteratorResult.next();
    Period actualNextResult3 = iteratorResult.next();
    Period actualNextResult4 = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(expectedNextResult, actualNextResult);
    assertSame(expectedNextResult2, actualNextResult2);
    assertSame(expectedNextResult3, actualNextResult3);
    assertSame(expectedNextResult4, actualNextResult4);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination3() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.MONTHLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination4() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination5() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_30E_360);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination6() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.U30_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_30U_360);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination7() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_ACT_360);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination8() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination9() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination10() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.SEMIANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination11() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination12() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_365,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_ACT_365);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination13() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination14() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.ANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Schedule SchedulePrototype.generateSchedule(LocalDate, int, int)"})
  public void testGenerateScheduleWithReferenceDateMaturityTermination15() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_ACT_AFB,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_ACT_ACT_AFB);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit2() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(4, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(4, periods.size());
    Period expectedNextResult = periods.get(0);
    Period expectedNextResult2 = periods.get(1);
    Period expectedNextResult3 = periods.get(2);
    Period expectedNextResult4 = periods.get(3);
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    Period actualNextResult2 = iteratorResult.next();
    Period actualNextResult3 = iteratorResult.next();
    Period actualNextResult4 = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(expectedNextResult, actualNextResult);
    assertSame(expectedNextResult2, actualNextResult2);
    assertSame(expectedNextResult3, actualNextResult3);
    assertSame(expectedNextResult4, actualNextResult4);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit3() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.MONTHLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit4() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit5() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_30E_360);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit6() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.U30_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_30U_360);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit7() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_ACT_360);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit8() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit9() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit10() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.SEMIANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit11() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertEquals(1, actualGenerateScheduleResult.getNumberOfPeriods());
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-03-02", getResult.getPayment().toString());
    assertEquals("1970-03-01", getResult.getPeriodEnd().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit12() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_365,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_ACT_365);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit13() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.MONTHS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(28, periods.size());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit14() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.DAYS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(1, periods.size());
    Period getResult = periods.get(0);
    assertEquals("1970-01-03", getResult.getFixing().toString());
    assertEquals("1970-01-04", getResult.getPayment().toString());
    assertEquals("1970-01-03", getResult.getPeriodEnd().toString());
    assertEquals("1970-01-02", getResult.getPeriodStart().toString());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(getResult, actualNextResult);
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, int, int, OffsetUnit)} with {@code
   * referenceDate}, {@code maturity}, {@code termination}, {@code unit}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, int, int,
   * OffsetUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, int, int, OffsetUnit)"
  })
  public void testGenerateScheduleWithReferenceDateMaturityTerminationUnit15() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(LocalDate.of(1970, 1, 1), 1, 1, OffsetUnit.YEARS);

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    List<Period> periods = actualGenerateScheduleResult.getPeriods();
    assertEquals(53, periods.size());
    assertEquals(53, actualGenerateScheduleResult.getNumberOfPeriods());
    Iterator<Period> iteratorResult = actualGenerateScheduleResult.iterator();
    assertTrue(iteratorResult.hasNext());
    Period expectedNextResult = periods.get(0);
    assertSame(expectedNextResult, iteratorResult.next());
    Period expectedNextResult2 = periods.get(1);
    assertSame(expectedNextResult2, iteratorResult.next());
    Period expectedNextResult3 = periods.get(2);
    assertSame(expectedNextResult3, iteratorResult.next());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate2() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate3() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate4() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.MONTHLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate5() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate6() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.SEMIANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate7() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.ANNUAL,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate8() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.TENOR,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention()
            instanceof DayCountConvention_30E_360_ISDA);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate9() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_30E_360);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate10() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.U30_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_30U_360);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate11() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_360,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            false);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_ACT_360);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }

  /**
   * Test {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate, LocalDate)} with {@code
   * referenceDate}, {@code startDate}, {@code endDate}.
   *
   * <p>Method under test: {@link SchedulePrototype#generateSchedule(LocalDate, LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Schedule SchedulePrototype.generateSchedule(LocalDate, LocalDate, LocalDate)"
  })
  public void testGenerateScheduleWithReferenceDateStartDateEndDate12() {
    // Arrange
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_365,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    Schedule actualGenerateScheduleResult =
        schedulePrototype.generateSchedule(
            referenceDate, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Assert
    assertTrue(actualGenerateScheduleResult instanceof ScheduleFromPeriods);
    assertTrue(
        actualGenerateScheduleResult.getDaycountconvention() instanceof DayCountConvention_ACT_365);
    assertEquals(0, actualGenerateScheduleResult.getNumberOfPeriods());
    assertFalse(actualGenerateScheduleResult.iterator().hasNext());
    assertTrue(actualGenerateScheduleResult.getPeriods().isEmpty());
    assertSame(referenceDate, actualGenerateScheduleResult.getReferenceDate());
  }
}
