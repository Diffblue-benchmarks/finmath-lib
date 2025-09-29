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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScheduleFromPeriodsDiffblueTest {
  /**
   * Test {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, List, DayCountConvention)}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, List,
   * DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleFromPeriods.<init>(LocalDate, List, DayCountConvention)"})
  public void testNewScheduleFromPeriods() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);

    // Act
    ScheduleFromPeriods actualScheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, daycountconvention);

    // Assert
    DayCountConvention daycountconvention2 = actualScheduleFromPeriods.getDaycountconvention();
    assertTrue(daycountconvention2 instanceof DayCountConvention_30E_360);
    assertEquals(0, actualScheduleFromPeriods.getNumberOfPeriods());
    assertFalse(actualScheduleFromPeriods.iterator().hasNext());
    assertTrue(actualScheduleFromPeriods.getPeriods().isEmpty());
    assertSame(daycountconvention, daycountconvention2);
    assertSame(referenceDate, actualScheduleFromPeriods.getReferenceDate());
  }

  /**
   * Test {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, DayCountConvention, Period[])}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate,
   * DayCountConvention, Period[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleFromPeriods.<init>(LocalDate, DayCountConvention, Period[])"})
  public void testNewScheduleFromPeriods2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act
    ScheduleFromPeriods actualScheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    // Assert
    DayCountConvention daycountconvention2 = actualScheduleFromPeriods.getDaycountconvention();
    assertTrue(daycountconvention2 instanceof DayCountConvention_30E_360);
    assertSame(daycountconvention, daycountconvention2);
    assertEquals(1, actualScheduleFromPeriods.getNumberOfPeriods());
    List<Period> periods = actualScheduleFromPeriods.getPeriods();
    assertEquals(1, periods.size());
    assertSame(period, periods.get(0));
    assertSame(referenceDate, actualScheduleFromPeriods.getReferenceDate());
    Iterator<Period> iteratorResult = actualScheduleFromPeriods.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(period, actualNextResult);
  }

  /**
   * Test {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, List, DayCountConvention)}.
   *
   * <ul>
   *   <li>Then return NumberOfPeriods is one.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, List,
   * DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleFromPeriods.<init>(LocalDate, List, DayCountConvention)"})
  public void testNewScheduleFromPeriods_thenReturnNumberOfPeriodsIsOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ArrayList<Period> periods = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period);

    // Act
    ScheduleFromPeriods actualScheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Assert
    assertEquals(1, actualScheduleFromPeriods.getNumberOfPeriods());
    assertSame(periods, actualScheduleFromPeriods.getPeriods());
    Iterator<Period> iteratorResult = actualScheduleFromPeriods.iterator();
    Period actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(period, actualNextResult);
  }

  /**
   * Test {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, List, DayCountConvention)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, List,
   * DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleFromPeriods.<init>(LocalDate, List, DayCountConvention)"})
  public void testNewScheduleFromPeriods_whenNull_thenThrowIllegalArgumentException() {
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
    Period period3 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period3);
    Period period4 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period4);
    Period period5 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period5);
    Period period6 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period6);
    Period period7 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period7);
    Period period8 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period8);
    Period period9 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period9);
    Period period10 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period10);
    Period period11 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period11);
    Period period12 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period12);
    Period period13 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period13);
    Period period14 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period14);
    Period period15 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period15);
    Period period16 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period16);
    Period period17 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period17);
    Period period18 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period18);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new ScheduleFromPeriods(null, periods, new DayCountConvention_30E_360(true)));
  }

  /**
   * Test {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate, DayCountConvention, Period[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#ScheduleFromPeriods(LocalDate,
   * DayCountConvention, Period[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScheduleFromPeriods.<init>(LocalDate, DayCountConvention, Period[])"})
  public void testNewScheduleFromPeriods_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new ScheduleFromPeriods(null, daycountconvention, period));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ScheduleFromPeriods#getDaycountconvention()}
   *   <li>{@link ScheduleFromPeriods#getPeriods()}
   *   <li>{@link ScheduleFromPeriods#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DayCountConvention ScheduleFromPeriods.getDaycountconvention()",
    "List ScheduleFromPeriods.getPeriods()",
    "LocalDate ScheduleFromPeriods.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, daycountconvention);

    // Act
    DayCountConvention actualDaycountconvention = scheduleFromPeriods.getDaycountconvention();
    List<Period> actualPeriods = scheduleFromPeriods.getPeriods();
    LocalDate actualReferenceDate = scheduleFromPeriods.getReferenceDate();

    // Assert
    assertTrue(actualDaycountconvention instanceof DayCountConvention_30E_360);
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertTrue(actualPeriods.isEmpty());
    assertSame(periods, actualPeriods);
    assertSame(daycountconvention, actualDaycountconvention);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link ScheduleFromPeriods#getNumberOfPeriods()}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getNumberOfPeriods()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ScheduleFromPeriods.getNumberOfPeriods()"})
  public void testGetNumberOfPeriods() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0, scheduleFromPeriods.getNumberOfPeriods());
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriod(int)}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Period ScheduleFromPeriods.getPeriod(int)"})
  public void testGetPeriod() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertSame(period2, scheduleFromPeriods.getPeriod(1));
  }

  /**
   * Test {@link ScheduleFromPeriods#getFixing(int)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getFixing(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ScheduleFromPeriods.getFixing(int)"})
  public void testGetFixing_thenReturnZero() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0.0d, scheduleFromPeriods.getFixing(1), 0.0);
  }

  /**
   * Test {@link ScheduleFromPeriods#getPayment(int)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPayment(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ScheduleFromPeriods.getPayment(int)"})
  public void testGetPayment_thenReturnZero() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0.0d, scheduleFromPeriods.getPayment(1), 0.0);
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodStart(int)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodStart(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ScheduleFromPeriods.getPeriodStart(int)"})
  public void testGetPeriodStart_thenReturnZero() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0.0d, scheduleFromPeriods.getPeriodStart(1), 0.0);
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodEnd(int)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodEnd(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ScheduleFromPeriods.getPeriodEnd(int)"})
  public void testGetPeriodEnd_thenReturnZero() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0.0d, scheduleFromPeriods.getPeriodEnd(1), 0.0);
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodLength(int)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodLength(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ScheduleFromPeriods.getPeriodLength(int)"})
  public void testGetPeriodLength_thenReturnZero() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertEquals(0.0d, scheduleFromPeriods.getPeriodLength(1), 0.0);
  }

  /**
   * Test {@link ScheduleFromPeriods#iterator()}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#iterator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Iterator ScheduleFromPeriods.iterator()"})
  public void testIterator() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act and Assert
    assertFalse(scheduleFromPeriods.iterator().hasNext());
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodIndex(LocalDate)} with {@code date}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodIndex(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ScheduleFromPeriods.getPeriodIndex(LocalDate)"})
  public void testGetPeriodIndexWithDate() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> scheduleFromPeriods.getPeriodIndex(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodIndex(LocalDate)} with {@code date}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodIndex(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ScheduleFromPeriods.getPeriodIndex(LocalDate)"})
  public void testGetPeriodIndexWithDate2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> scheduleFromPeriods.getPeriodIndex(LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodIndex(double)} with {@code time}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ScheduleFromPeriods.getPeriodIndex(double)"})
  public void testGetPeriodIndexWithTime() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> scheduleFromPeriods.getPeriodIndex(10.0d));
  }

  /**
   * Test {@link ScheduleFromPeriods#getPeriodIndex(double)} with {@code time}.
   *
   * <p>Method under test: {@link ScheduleFromPeriods#getPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ScheduleFromPeriods.getPeriodIndex(double)"})
  public void testGetPeriodIndexWithTime2() {
    // Arrange
    LocalDate referenceDate = LocalDate.ofYearDay(1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> scheduleFromPeriods.getPeriodIndex(10.0d));
  }
}
