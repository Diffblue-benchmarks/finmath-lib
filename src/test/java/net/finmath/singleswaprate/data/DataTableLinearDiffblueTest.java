package net.finmath.singleswaprate.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import net.finmath.time.RegularSchedule;
import net.finmath.time.ScheduleGenerator;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.ScheduleMetaData;
import net.finmath.time.SchedulePrototype;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataTableLinearDiffblueTest {
  /**
   * Test {@link DataTableLinear#interpolateDataTable(DataTableBasic)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#interpolateDataTable(DataTableBasic)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLinear DataTableLinear.interpolateDataTable(DataTableBasic)"})
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
    DataTableLinear actualInterpolateDataTableResult =
        DataTableLinear.interpolateDataTable(baseTable2);

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
   *   <li>{@link DataTableLinear#DataTableLinear(String, TableConvention, LocalDate,
   *       SchedulePrototype)}
   *   <li>{@link DataTableLinear#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableLinear.<init>(String, TableConvention, LocalDate, SchedulePrototype)",
    "String DataTableLinear.toString()"
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
    DataTableLinear actualDataTableLinear =
        new DataTableLinear("Name", TableConvention.MONTHS, referenceDate, scheduleMetaData);
    actualDataTableLinear.toString();

    // Assert
    LocalDate referenceDate2 = actualDataTableLinear.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualDataTableLinear.getName());
    assertEquals(TableConvention.MONTHS, actualDataTableLinear.getConvention());
    assertSame(scheduleMetaData, actualDataTableLinear.getScheduleMetaData());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link DataTableLinear#DataTableLinear(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return Maturities contains one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#DataTableLinear(String, TableConvention,
   * LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableLinear.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableLinear_givenOne_thenReturnMaturitiesContainsOne() {
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
    DataTableLinear actualDataTableLinear =
        new DataTableLinear(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableLinear.getName());
    TreeSet<Integer> maturities2 = actualDataTableLinear.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableLinear.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLinear.getConvention());
    assertTrue(maturities2.contains(1));
    assertEquals(maturities2, actualDataTableLinear.getTerminations());
    assertSame(scheduleMetaData, actualDataTableLinear.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableLinear.getReferenceDate());
  }

  /**
   * Test {@link DataTableLinear#DataTableLinear(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Maturities contains two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#DataTableLinear(String, TableConvention,
   * LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableLinear.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableLinear_givenTen_thenReturnMaturitiesContainsTwo() {
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
    DataTableLinear actualDataTableLinear =
        new DataTableLinear(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableLinear.getName());
    TreeSet<Integer> maturities2 = actualDataTableLinear.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableLinear.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLinear.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableLinear.getTerminations());
    assertSame(scheduleMetaData, actualDataTableLinear.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableLinear.getReferenceDate());
  }

  /**
   * Test {@link DataTableLinear#DataTableLinear(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given three hundred sixty.
   *   <li>When {@link ArrayList#ArrayList()} add three hundred sixty.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#DataTableLinear(String, TableConvention,
   * LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableLinear.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableLinear_givenThreeHundredSixty_whenArrayListAddThreeHundredSixty() {
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
    DataTableLinear actualDataTableLinear =
        new DataTableLinear(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableLinear.getName());
    TreeSet<Integer> maturities2 = actualDataTableLinear.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableLinear.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLinear.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableLinear.getTerminations());
    assertSame(scheduleMetaData, actualDataTableLinear.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableLinear.getReferenceDate());
  }

  /**
   * Test {@link DataTableLinear#DataTableLinear(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#DataTableLinear(String, TableConvention,
   * LocalDate, SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableLinear.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableLinear_whenArrayList_thenReturnSizeIsZero() {
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
    DataTableLinear actualDataTableLinear =
        new DataTableLinear(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            new ArrayList<>());

    // Assert
    assertEquals("Name", actualDataTableLinear.getName());
    assertEquals(0, actualDataTableLinear.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLinear.getConvention());
    TreeSet<Integer> maturities2 = actualDataTableLinear.getMaturities();
    assertTrue(maturities2.isEmpty());
    assertEquals(maturities2, actualDataTableLinear.getTerminations());
    assertSame(scheduleMetaData, actualDataTableLinear.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableLinear.getReferenceDate());
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.MONTHS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(
            new RegularSchedule(
                new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(10.0d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), isA(LocalDate.class), isA(LocalDate.class));
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code DAYS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_givenDataTableLightGetConventionReturnDays() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.DAYS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(10.0d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code
   *       MONTHS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_givenDataTableLightGetConventionReturnMonths() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.MONTHS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(10.0d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code
   *       MONTHS}.
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_givenDataTableLightGetConventionReturnMonths_when05() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.MONTHS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(0.5d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code
   *       MONTHS}.
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_givenDataTableLightGetConventionReturnMonths_when052() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.MONTHS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(-0.5d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code WEEKS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_givenDataTableLightGetConventionReturnWeeks() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.WEEKS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(10.0d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code YEARS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_givenDataTableLightGetConventionReturnYears() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.YEARS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(10.0d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When fifteen.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLinear#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLinear.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_whenFifteen() {
    // Arrange
    DataTableLight baseTable = mock(DataTableLight.class);
    when(baseTable.size()).thenReturn(3);
    when(baseTable.getName()).thenReturn("Name");
    when(baseTable.getMaturities()).thenReturn(new TreeSet<>());
    when(baseTable.getConvention()).thenReturn(TableConvention.MONTHS);

    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));

    DataTableBasic baseTable2 =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = DataTableLinear.interpolateDataTable(baseTable2).getValue(15.0d, 10.0d);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableLinear#clone()}.
   *
   * <p>Method under test: {@link DataTableLinear#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLinear DataTableLinear.clone()"})
  public void testClone() {
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
    DataTableLinear actualCloneResult = DataTableLinear.interpolateDataTable(baseTable2).clone();

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
