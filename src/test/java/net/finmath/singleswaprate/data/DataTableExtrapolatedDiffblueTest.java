package net.finmath.singleswaprate.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import net.finmath.time.ScheduleGenerator;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.SchedulePrototype;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataTableExtrapolatedDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataTableExtrapolated#DataTableExtrapolated(String, TableConvention, LocalDate,
   *       SchedulePrototype)}
   *   <li>{@link DataTableExtrapolated#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableExtrapolated.<init>(String, TableConvention, LocalDate, SchedulePrototype)",
    "String DataTableExtrapolated.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
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
    DataTableExtrapolated actualDataTableExtrapolated =
        new DataTableExtrapolated("Name", TableConvention.MONTHS, referenceDate, scheduleMetaData);
    actualDataTableExtrapolated.toString();

    // Assert
    LocalDate referenceDate2 = actualDataTableExtrapolated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualDataTableExtrapolated.getName());
    assertEquals(TableConvention.MONTHS, actualDataTableExtrapolated.getConvention());
    assertSame(scheduleMetaData, actualDataTableExtrapolated.getScheduleMetaData());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link DataTableExtrapolated#DataTableExtrapolated(String, TableConvention, LocalDate,
   * SchedulePrototype, int[], int[], double[])}.
   *
   * <p>Method under test: {@link DataTableExtrapolated#DataTableExtrapolated(String,
   * TableConvention, LocalDate, SchedulePrototype, int[], int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableExtrapolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, int[], int[], double[])"
  })
  public void testNewDataTableExtrapolated() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
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
    DataTableExtrapolated actualDataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            new int[] {1, 52, 1, 52},
            new int[] {1, 52, 1, 52},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals("Name", actualDataTableExtrapolated.getName());
    TreeSet<Integer> maturities = actualDataTableExtrapolated.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(2, actualDataTableExtrapolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableExtrapolated.getConvention());
    assertEquals(maturities, actualDataTableExtrapolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableExtrapolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableExtrapolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableExtrapolated#DataTableExtrapolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return Maturities contains one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableExtrapolated#DataTableExtrapolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableExtrapolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableExtrapolated_givenOne_thenReturnMaturitiesContainsOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    ArrayList<Integer> maturities = new ArrayList<>();
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);

    ArrayList<Integer> terminations = new ArrayList<>();
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);

    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);

    // Act
    DataTableExtrapolated actualDataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableExtrapolated.getName());
    TreeSet<Integer> maturities2 = actualDataTableExtrapolated.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableExtrapolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableExtrapolated.getConvention());
    assertTrue(maturities2.contains(1));
    assertEquals(maturities2, actualDataTableExtrapolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableExtrapolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableExtrapolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableExtrapolated#DataTableExtrapolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Maturities contains two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableExtrapolated#DataTableExtrapolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableExtrapolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableExtrapolated_givenTen_thenReturnMaturitiesContainsTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    ArrayList<Integer> maturities = new ArrayList<>();
    maturities.add(2);

    ArrayList<Integer> terminations = new ArrayList<>();
    terminations.add(2);

    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);

    // Act
    DataTableExtrapolated actualDataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableExtrapolated.getName());
    TreeSet<Integer> maturities2 = actualDataTableExtrapolated.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableExtrapolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableExtrapolated.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableExtrapolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableExtrapolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableExtrapolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableExtrapolated#DataTableExtrapolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given three hundred sixty.
   * </ul>
   *
   * <p>Method under test: {@link DataTableExtrapolated#DataTableExtrapolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableExtrapolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableExtrapolated_givenThreeHundredSixty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    ArrayList<Integer> maturities = new ArrayList<>();
    maturities.add(2);

    ArrayList<Integer> terminations = new ArrayList<>();
    terminations.add(2);

    ArrayList<Double> values = new ArrayList<>();
    values.add(360.0d);
    values.add(10.0d);

    // Act
    DataTableExtrapolated actualDataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableExtrapolated.getName());
    TreeSet<Integer> maturities2 = actualDataTableExtrapolated.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableExtrapolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableExtrapolated.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableExtrapolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableExtrapolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableExtrapolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableExtrapolated#DataTableExtrapolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableExtrapolated#DataTableExtrapolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableExtrapolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableExtrapolated_whenArrayList_thenReturnSizeIsZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    ArrayList<Integer> maturities = new ArrayList<>();
    ArrayList<Integer> terminations = new ArrayList<>();

    // Act
    DataTableExtrapolated actualDataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            new ArrayList<>());

    // Assert
    assertEquals("Name", actualDataTableExtrapolated.getName());
    assertEquals(0, actualDataTableExtrapolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableExtrapolated.getConvention());
    TreeSet<Integer> maturities2 = actualDataTableExtrapolated.getMaturities();
    assertTrue(maturities2.isEmpty());
    assertEquals(maturities2, actualDataTableExtrapolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableExtrapolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableExtrapolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableExtrapolated#getValue(double, double)} with {@code double}, {@code
   * double}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableExtrapolated#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableExtrapolated.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_thenReturnTen() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            10,
            10,
            true);

    DataTableExtrapolated dataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            new int[] {10, 1, 10, 1},
            new int[] {1, 10, 1, 10},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableExtrapolated.getValue(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link DataTableExtrapolated#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableExtrapolated#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableExtrapolated.getValue(int, int)"})
  public void testGetValueWithIntInt_thenReturnTen() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            3,
            3,
            true);

    DataTableExtrapolated dataTableExtrapolated =
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            new int[] {3, 1, 3, 1},
            new int[] {1, 3, 1, 3},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableExtrapolated.getValue(3, 1), 0.0);
  }

  /**
   * Test {@link DataTableExtrapolated#clone()}.
   *
   * <p>Method under test: {@link DataTableExtrapolated#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableExtrapolated DataTableExtrapolated.clone()"})
  public void testClone() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    DataTableExtrapolated dataTableExtrapolated =
        new DataTableExtrapolated("Name", TableConvention.MONTHS, referenceDate, scheduleMetaData);

    // Act
    DataTableExtrapolated actualCloneResult = dataTableExtrapolated.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertEquals(0, actualCloneResult.size());
    assertEquals(TableConvention.MONTHS, actualCloneResult.getConvention());
    TreeSet<Integer> maturities = actualCloneResult.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualCloneResult.getTerminations());
    assertSame(scheduleMetaData, actualCloneResult.getScheduleMetaData());
    assertSame(referenceDate, referenceDate2);
  }
}
