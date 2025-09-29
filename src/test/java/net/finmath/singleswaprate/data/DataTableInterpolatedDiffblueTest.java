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

public class DataTableInterpolatedDiffblueTest {
  /**
   * Test {@link DataTableInterpolated#interpolateDataTable(DataTableBasic)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#interpolateDataTable(DataTableBasic)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DataTableInterpolated DataTableInterpolated.interpolateDataTable(DataTableBasic)"
  })
  public void testInterpolateDataTable_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    DataTableLight baseTable = new DataTableLight("Name", TableConvention.MONTHS);
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

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act
    DataTableInterpolated actualInterpolateDataTableResult =
        DataTableInterpolated.interpolateDataTable(baseTable2);

    // Assert
    LocalDate referenceDate2 = actualInterpolateDataTableResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualInterpolateDataTableResult.getName());
    assertEquals(0, actualInterpolateDataTableResult.size());
    assertEquals(TableConvention.MONTHS, actualInterpolateDataTableResult.getConvention());
    TreeSet<Integer> maturities = actualInterpolateDataTableResult.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualInterpolateDataTableResult.getTerminations());
    assertSame(scheduleMetaData, actualInterpolateDataTableResult.getScheduleMetaData());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataTableInterpolated#DataTableInterpolated(String, TableConvention, LocalDate,
   *       SchedulePrototype)}
   *   <li>{@link DataTableInterpolated#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableInterpolated.<init>(String, TableConvention, LocalDate, SchedulePrototype)",
    "String DataTableInterpolated.toString()"
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
    DataTableInterpolated actualDataTableInterpolated =
        new DataTableInterpolated("Name", TableConvention.MONTHS, referenceDate, scheduleMetaData);
    actualDataTableInterpolated.toString();

    // Assert
    LocalDate referenceDate2 = actualDataTableInterpolated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualDataTableInterpolated.getName());
    assertEquals(TableConvention.MONTHS, actualDataTableInterpolated.getConvention());
    assertSame(scheduleMetaData, actualDataTableInterpolated.getScheduleMetaData());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link DataTableInterpolated#DataTableInterpolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return Maturities contains one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#DataTableInterpolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableInterpolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableInterpolated_givenOne_thenReturnMaturitiesContainsOne() {
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
    DataTableInterpolated actualDataTableInterpolated =
        new DataTableInterpolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableInterpolated.getName());
    TreeSet<Integer> maturities2 = actualDataTableInterpolated.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableInterpolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableInterpolated.getConvention());
    assertTrue(maturities2.contains(1));
    assertEquals(maturities2, actualDataTableInterpolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableInterpolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableInterpolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableInterpolated#DataTableInterpolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Maturities contains two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#DataTableInterpolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableInterpolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableInterpolated_givenTen_thenReturnMaturitiesContainsTwo() {
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
    DataTableInterpolated actualDataTableInterpolated =
        new DataTableInterpolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableInterpolated.getName());
    TreeSet<Integer> maturities2 = actualDataTableInterpolated.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableInterpolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableInterpolated.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableInterpolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableInterpolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableInterpolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableInterpolated#DataTableInterpolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given three hundred sixty.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#DataTableInterpolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableInterpolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableInterpolated_givenThreeHundredSixty() {
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
    DataTableInterpolated actualDataTableInterpolated =
        new DataTableInterpolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableInterpolated.getName());
    TreeSet<Integer> maturities2 = actualDataTableInterpolated.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableInterpolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableInterpolated.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableInterpolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableInterpolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableInterpolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableInterpolated#DataTableInterpolated(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#DataTableInterpolated(String,
   * TableConvention, LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableInterpolated.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableInterpolated_whenArrayList_thenReturnSizeIsZero() {
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
    DataTableInterpolated actualDataTableInterpolated =
        new DataTableInterpolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            new ArrayList<>());

    // Assert
    assertEquals("Name", actualDataTableInterpolated.getName());
    assertEquals(0, actualDataTableInterpolated.size());
    assertEquals(TableConvention.MONTHS, actualDataTableInterpolated.getConvention());
    TreeSet<Integer> maturities2 = actualDataTableInterpolated.getMaturities();
    assertTrue(maturities2.isEmpty());
    assertEquals(maturities2, actualDataTableInterpolated.getTerminations());
    assertSame(scheduleMetaData, actualDataTableInterpolated.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableInterpolated.getReferenceDate());
  }

  /**
   * Test {@link DataTableInterpolated#getValue(double, double)} with {@code double}, {@code
   * double}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableInterpolated.getValue(double, double)"})
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
   * Test {@link DataTableInterpolated#clone()}.
   *
   * <ul>
   *   <li>Then return {@link DataTableExtrapolated}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableInterpolated DataTableInterpolated.clone()"})
  public void testClone_thenReturnDataTableExtrapolated() {
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
    assertTrue(actualCloneResult instanceof DataTableExtrapolated);
    assertEquals("Name", actualCloneResult.getName());
    assertEquals(0, actualCloneResult.size());
    assertEquals(TableConvention.MONTHS, actualCloneResult.getConvention());
    TreeSet<Integer> maturities = actualCloneResult.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualCloneResult.getTerminations());
    assertSame(scheduleMetaData, actualCloneResult.getScheduleMetaData());
    assertSame(referenceDate, actualCloneResult.getReferenceDate());
  }

  /**
   * Test {@link DataTableInterpolated#clone()}.
   *
   * <ul>
   *   <li>Then ScheduleMetaData BusinessdayCalendar return {@link BusinessdayCalendarAny}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableInterpolated#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableInterpolated DataTableInterpolated.clone()"})
  public void testClone_thenScheduleMetaDataBusinessdayCalendarReturnBusinessdayCalendarAny() {
    // Arrange
    DataTableLight baseTable = new DataTableLight("Name", TableConvention.MONTHS);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny businessdayCalendar = new BusinessdayCalendarAny();
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            businessdayCalendar,
            1,
            1,
            true);

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act
    DataTableInterpolated actualCloneResult =
        DataTableInterpolated.interpolateDataTable(baseTable2).clone();

    // Assert
    SchedulePrototype scheduleMetaData2 = actualCloneResult.getScheduleMetaData();
    BusinessdayCalendar businessdayCalendar2 = scheduleMetaData2.getBusinessdayCalendar();
    assertTrue(businessdayCalendar2 instanceof BusinessdayCalendarAny);
    assertEquals("1970-01-01", actualCloneResult.getReferenceDate().toString());
    assertEquals(1, scheduleMetaData2.getFixingOffsetDays());
    assertEquals(1, scheduleMetaData2.getPaymentOffsetDays());
    assertEquals(DaycountConvention.E30_360_ISDA, scheduleMetaData2.getDaycountConvention());
    assertEquals(Frequency.DAILY, scheduleMetaData2.getFrequency());
    assertEquals(ShortPeriodConvention.FIRST, scheduleMetaData2.getShortPeriodConvention());
    assertEquals(DateRollConvention.UNADJUSTED, scheduleMetaData2.getDateRollConvention());
    assertTrue(scheduleMetaData2.isUseEndOfMonth());
    assertSame(businessdayCalendar, businessdayCalendar2);
  }
}
