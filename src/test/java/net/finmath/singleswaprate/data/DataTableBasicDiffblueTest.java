package net.finmath.singleswaprate.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
import net.finmath.singleswaprate.data.DataTableBasic.DoubleKey;
import net.finmath.time.RegularSchedule;
import net.finmath.time.ScheduleGenerator;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.ScheduleMetaData;
import net.finmath.time.SchedulePrototype;
import net.finmath.time.TenorFromArray;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataTableBasicDiffblueTest {
  /**
   * Test DoubleKey {@link DoubleKey#equals(Object)}, and {@link DoubleKey#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DoubleKey#equals(Object)}
   *   <li>{@link DoubleKey#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DoubleKey.equals(Object)", "int DoubleKey.hashCode()"})
  public void testDoubleKeyEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);
    DoubleKey doubleKey = upgradeDataTableLightResult.new DoubleKey(10.0d, 10.0d);
    DataTableLight baseTable2 = new DataTableLight("Name", TableConvention.MONTHS);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    DataTableBasic upgradeDataTableLightResult2 =
        DataTableBasic.upgradeDataTableLight(baseTable2, referenceDate2, scheduleMetaData2);
    DoubleKey doubleKey2 = upgradeDataTableLightResult2.new DoubleKey(10.0d, 10.0d);

    // Act and Assert
    assertEquals(doubleKey, doubleKey2);
    assertEquals(doubleKey.hashCode(), doubleKey2.hashCode());
  }

  /**
   * Test DoubleKey {@link DoubleKey#equals(Object)}, and {@link DoubleKey#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DoubleKey#equals(Object)}
   *   <li>{@link DoubleKey#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DoubleKey.equals(Object)", "int DoubleKey.hashCode()"})
  public void testDoubleKeyEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);
    DoubleKey doubleKey = upgradeDataTableLightResult.new DoubleKey(10.0d, 10.0d);

    // Act and Assert
    assertEquals(doubleKey, doubleKey);
    int expectedHashCodeResult = doubleKey.hashCode();
    assertEquals(expectedHashCodeResult, doubleKey.hashCode());
  }

  /**
   * Test DoubleKey {@link DoubleKey#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DoubleKey#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DoubleKey.equals(Object)", "int DoubleKey.hashCode()"})
  public void testDoubleKeyEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);
    DoubleKey doubleKey = upgradeDataTableLightResult.new DoubleKey(0.5d, 10.0d);
    DataTableLight baseTable2 = new DataTableLight("Name", TableConvention.MONTHS);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    DataTableBasic upgradeDataTableLightResult2 =
        DataTableBasic.upgradeDataTableLight(baseTable2, referenceDate2, scheduleMetaData2);

    // Act and Assert
    assertNotEquals(doubleKey, upgradeDataTableLightResult2.new DoubleKey(10.0d, 10.0d));
  }

  /**
   * Test DoubleKey {@link DoubleKey#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DoubleKey#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DoubleKey.equals(Object)", "int DoubleKey.hashCode()"})
  public void testDoubleKeyEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);
    DoubleKey doubleKey = upgradeDataTableLightResult.new DoubleKey(10.0d, 0.5d);
    DataTableLight baseTable2 = new DataTableLight("Name", TableConvention.MONTHS);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    DataTableBasic upgradeDataTableLightResult2 =
        DataTableBasic.upgradeDataTableLight(baseTable2, referenceDate2, scheduleMetaData2);

    // Act and Assert
    assertNotEquals(doubleKey, upgradeDataTableLightResult2.new DoubleKey(10.0d, 10.0d));
  }

  /**
   * Test DoubleKey {@link DoubleKey#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DoubleKey#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DoubleKey.equals(Object)", "int DoubleKey.hashCode()"})
  public void testDoubleKeyEquals_whenOtherIsNull_thenReturnNotEqual() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertNotEquals(upgradeDataTableLightResult.new DoubleKey(10.0d, 10.0d), null);
  }

  /**
   * Test DoubleKey {@link DoubleKey#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DoubleKey#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DoubleKey.equals(Object)", "int DoubleKey.hashCode()"})
  public void testDoubleKeyEquals_whenOtherIsWrongType_thenReturnNotEqual() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertNotEquals(
        upgradeDataTableLightResult.new DoubleKey(10.0d, 10.0d), "Different type to DoubleKey");
  }

  /**
   * Test DoubleKey {@link DoubleKey#toString()}.
   *
   * <p>Method under test: {@link DoubleKey#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DoubleKey.toString()"})
  public void testDoubleKeyToString() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertEquals(
        "DoubleKey [maturity=10.0, termination=10.0]",
        upgradeDataTableLightResult.new DoubleKey(10.0d, 10.0d).toString());
  }

  /**
   * Test {@link DataTableBasic#upgradeDataTableLight(DataTableLight, LocalDate,
   * SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#upgradeDataTableLight(DataTableLight, LocalDate,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DataTableBasic DataTableBasic.upgradeDataTableLight(DataTableLight, LocalDate, SchedulePrototype)"
  })
  public void testUpgradeDataTableLight_thenReturnName() {
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

    // Act
    DataTableBasic actualUpgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Assert
    assertEquals("Name", actualUpgradeDataTableLightResult.getName());
    assertEquals(0, actualUpgradeDataTableLightResult.size());
    assertEquals(TableConvention.MONTHS, actualUpgradeDataTableLightResult.getConvention());
    TreeSet<Integer> maturities = actualUpgradeDataTableLightResult.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualUpgradeDataTableLightResult.getTerminations());
    assertSame(scheduleMetaData, actualUpgradeDataTableLightResult.getScheduleMetaData());
    assertSame(referenceDate, actualUpgradeDataTableLightResult.getReferenceDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   *       SchedulePrototype)}
   *   <li>{@link DataTableBasic#toString()}
   *   <li>{@link DataTableBasic#getConvention()}
   *   <li>{@link DataTableBasic#getName()}
   *   <li>{@link DataTableBasic#getReferenceDate()}
   *   <li>{@link DataTableBasic#getScheduleMetaData()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableBasic.<init>(String, TableConvention, LocalDate, SchedulePrototype)",
    "TableConvention DataTableBasic.getConvention()",
    "String DataTableBasic.getName()",
    "LocalDate DataTableBasic.getReferenceDate()",
    "SchedulePrototype DataTableBasic.getScheduleMetaData()",
    "String DataTableBasic.toString()"
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
    DataTableBasic actualDataTableBasic =
        new DataTableBasic("Name", TableConvention.MONTHS, referenceDate, scheduleMetaData);
    actualDataTableBasic.toString();
    TableConvention actualConvention = actualDataTableBasic.getConvention();
    String actualName = actualDataTableBasic.getName();
    LocalDate actualReferenceDate = actualDataTableBasic.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals(TableConvention.MONTHS, actualConvention);
    assertSame(scheduleMetaData, actualDataTableBasic.getScheduleMetaData());
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return Maturities contains one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableBasic.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableBasic_givenOne_thenReturnMaturitiesContainsOne() {
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
    DataTableBasic actualDataTableBasic =
        new DataTableBasic(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableBasic.getName());
    TreeSet<Integer> maturities2 = actualDataTableBasic.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableBasic.size());
    assertEquals(TableConvention.MONTHS, actualDataTableBasic.getConvention());
    assertTrue(maturities2.contains(1));
    assertEquals(maturities2, actualDataTableBasic.getTerminations());
    assertSame(scheduleMetaData, actualDataTableBasic.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableBasic.getReferenceDate());
  }

  /**
   * Test {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Maturities contains two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableBasic.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableBasic_givenTen_thenReturnMaturitiesContainsTwo() {
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
    DataTableBasic actualDataTableBasic =
        new DataTableBasic(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableBasic.getName());
    TreeSet<Integer> maturities2 = actualDataTableBasic.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableBasic.size());
    assertEquals(TableConvention.MONTHS, actualDataTableBasic.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableBasic.getTerminations());
    assertSame(scheduleMetaData, actualDataTableBasic.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableBasic.getReferenceDate());
  }

  /**
   * Test {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>Given three hundred sixty.
   *   <li>When {@link ArrayList#ArrayList()} add three hundred sixty.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableBasic.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableBasic_givenThreeHundredSixty_whenArrayListAddThreeHundredSixty() {
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
    DataTableBasic actualDataTableBasic =
        new DataTableBasic(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            values);

    // Assert
    assertEquals("Name", actualDataTableBasic.getName());
    TreeSet<Integer> maturities2 = actualDataTableBasic.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableBasic.size());
    assertEquals(TableConvention.MONTHS, actualDataTableBasic.getConvention());
    assertTrue(maturities2.contains(2));
    assertEquals(maturities2, actualDataTableBasic.getTerminations());
    assertSame(scheduleMetaData, actualDataTableBasic.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableBasic.getReferenceDate());
  }

  /**
   * Test {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#DataTableBasic(String, TableConvention, LocalDate,
   * SchedulePrototype, List, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableBasic.<init>(String, TableConvention, LocalDate, SchedulePrototype, List, List, List)"
  })
  public void testNewDataTableBasic_whenArrayList_thenReturnSizeIsZero() {
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
    DataTableBasic actualDataTableBasic =
        new DataTableBasic(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            maturities,
            terminations,
            new ArrayList<>());

    // Assert
    assertEquals("Name", actualDataTableBasic.getName());
    assertEquals(0, actualDataTableBasic.size());
    assertEquals(TableConvention.MONTHS, actualDataTableBasic.getConvention());
    TreeSet<Integer> maturities2 = actualDataTableBasic.getMaturities();
    assertTrue(maturities2.isEmpty());
    assertEquals(maturities2, actualDataTableBasic.getTerminations());
    assertSame(scheduleMetaData, actualDataTableBasic.getScheduleMetaData());
    assertSame(referenceDate, actualDataTableBasic.getReferenceDate());
  }

  /**
   * Test {@link DataTableBasic#addPoint(int, int, double)}.
   *
   * <p>Method under test: {@link DataTableBasic#addPoint(int, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTable DataTableBasic.addPoint(int, int, double)"})
  public void testAddPoint() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act
    DataTable actualAddPointResult = upgradeDataTableLightResult.addPoint(2, 1, 10.0d);

    // Assert
    assertTrue(actualAddPointResult instanceof DataTableBasic);
    assertEquals("Name", actualAddPointResult.getName());
    assertEquals(1, actualAddPointResult.getMaturities().size());
    assertEquals(1, actualAddPointResult.getTerminations().size());
    assertEquals(1, actualAddPointResult.size());
    assertEquals(TableConvention.MONTHS, actualAddPointResult.getConvention());
    assertSame(scheduleMetaData, actualAddPointResult.getScheduleMetaData());
    assertSame(referenceDate, actualAddPointResult.getReferenceDate());
  }

  /**
   * Test {@link DataTableBasic#addPoints(int[], int[], double[])}.
   *
   * <p>Method under test: {@link DataTableBasic#addPoints(int[], int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTable DataTableBasic.addPoints(int[], int[], double[])"})
  public void testAddPoints() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act
    DataTable actualAddPointsResult =
        upgradeDataTableLightResult.addPoints(
            new int[] {2, 1, 2, 1},
            new int[] {1, 2, 1, 2},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualAddPointsResult instanceof DataTableBasic);
    assertEquals("Name", actualAddPointsResult.getName());
    TreeSet<Integer> maturities = actualAddPointsResult.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(2, actualAddPointsResult.size());
    assertEquals(TableConvention.MONTHS, actualAddPointsResult.getConvention());
    assertEquals(maturities, actualAddPointsResult.getTerminations());
    assertSame(scheduleMetaData, actualAddPointsResult.getScheduleMetaData());
    assertSame(referenceDate, actualAddPointsResult.getReferenceDate());
  }

  /**
   * Test {@link DataTableBasic#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(double, double)"})
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
            3,
            3,
            true);

    DataTableExtrapolated dataTableExtrapolated =
        new DataTableExtrapolated(
            "Key not found.",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            new int[] {3, 1, 3, 1},
            new int[] {1, 3, 1, 3},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableExtrapolated.getValue(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link DataTableBasic#getValue(DoubleKey)} with {@code DoubleKey}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(DoubleKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(DoubleKey)"})
  public void testGetValueWithDoubleKey_thenReturnTen() {
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
        new DataTableExtrapolated(
            "Name",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            new int[] {1, 52, 1, 52},
            new int[] {1, 52, 1, 52},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    dataTableExtrapolated.addPoint(2, 1, 10.0d);
    DataTableLight baseTable = new DataTableLight("Name", TableConvention.MONTHS);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate2, scheduleMetaData2);

    // Act
    double actualValue =
        dataTableExtrapolated.getValue(upgradeDataTableLightResult.new DoubleKey(1, 1));

    // Assert
    assertEquals(10.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code DAYS}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_givenDataTableLightGetConventionReturnDays_thenReturnZero() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(3, 1);

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
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code
   *       MONTHS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_givenDataTableLightGetConventionReturnMonths() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(1, 1);

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
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code
   *       MONTHS}.
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_givenDataTableLightGetConventionReturnMonths_whenMinusOne() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(-1, 1);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), isA(LocalDate.class));
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code
   *       MONTHS}.
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_givenDataTableLightGetConventionReturnMonths_whenZero() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(0, 1);

    // Assert
    verify(baseTable).getConvention();
    verify(baseTable).getMaturities();
    verify(baseTable).getName();
    verify(baseTable, atLeast(1)).size();
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), isA(LocalDate.class), Mockito.<LocalDate>any());
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code WEEKS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_givenDataTableLightGetConventionReturnWeeks() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(3, 1);

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
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight} {@link DataTableLight#getConvention()} return {@code YEARS}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_givenDataTableLightGetConventionReturnYears() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(3, 1);

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
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
  public void testGetValueWithIntInt_thenReturn05() {
    // Arrange
    ScheduleMetaData scheduleMetaData = mock(ScheduleMetaData.class);
    when(scheduleMetaData.generateSchedule(
            Mockito.<LocalDate>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    DataTableLight baseTable =
        new DataTableLight(
            "Key not found.",
            TableConvention.MONTHS,
            new int[] {1, 2, 1, 2},
            new int[] {1, 2, 1, 2},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, LocalDate.of(1970, 1, 1), scheduleMetaData);

    // Act
    double actualValue = upgradeDataTableLightResult.getValue(3, 1);

    // Assert
    verify(scheduleMetaData, atLeast(1))
        .generateSchedule(isA(LocalDate.class), Mockito.<LocalDate>any(), Mockito.<LocalDate>any());
    assertEquals(0.5d, actualValue, 0.0);
  }

  /**
   * Test {@link DataTableBasic#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableBasic.getValue(int, int)"})
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
            1,
            1,
            true);

    DataTableExtrapolated dataTableExtrapolated =
        new DataTableExtrapolated(
            "Key not found.",
            TableConvention.MONTHS,
            referenceDate,
            scheduleMetaData,
            new int[] {1, 2, 1, 2},
            new int[] {1, 2, 1, 2},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableExtrapolated.getValue(Integer.MIN_VALUE, 1), 0.0);
  }

  /**
   * Test {@link DataTableBasic#size()}.
   *
   * <p>Method under test: {@link DataTableBasic#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DataTableBasic.size()"})
  public void testSize() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertEquals(0, upgradeDataTableLightResult.size());
  }

  /**
   * Test {@link DataTableBasic#containsEntryFor(double, double)} with {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link DataTableBasic#containsEntryFor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableBasic.containsEntryFor(double, double)"})
  public void testContainsEntryForWithDoubleDouble() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertFalse(upgradeDataTableLightResult.containsEntryFor(10.0d, 10.0d));
  }

  /**
   * Test {@link DataTableBasic#containsEntryFor(double, double)} with {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link DataTableBasic#containsEntryFor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableBasic.containsEntryFor(double, double)"})
  public void testContainsEntryForWithDoubleDouble2() {
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
    assertFalse(dataTableExtrapolated.containsEntryFor(10.0d, 10.0d));
  }

  /**
   * Test {@link DataTableBasic#containsEntryFor(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link DataTableBasic#containsEntryFor(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableBasic.containsEntryFor(int, int)"})
  public void testContainsEntryForWithIntInt() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertFalse(upgradeDataTableLightResult.containsEntryFor(1, 1));
  }

  /**
   * Test {@link DataTableBasic#getMaturities()}.
   *
   * <p>Method under test: {@link DataTableBasic#getMaturities()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableBasic.getMaturities()"})
  public void testGetMaturities() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertTrue(upgradeDataTableLightResult.getMaturities().isEmpty());
  }

  /**
   * Test {@link DataTableBasic#getTerminations()}.
   *
   * <p>Method under test: {@link DataTableBasic#getTerminations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableBasic.getTerminations()"})
  public void testGetTerminations() {
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act and Assert
    assertTrue(upgradeDataTableLightResult.getTerminations().isEmpty());
  }

  /**
   * Test {@link DataTableBasic#clone()}.
   *
   * <ul>
   *   <li>Then return {@link DataTableExtrapolated}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableBasic DataTableBasic.clone()"})
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
   * Test {@link DataTableBasic#clone()}.
   *
   * <ul>
   *   <li>Then ScheduleMetaData BusinessdayCalendar return {@link BusinessdayCalendarAny}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableBasic#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableBasic DataTableBasic.clone()"})
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

    DataTableBasic upgradeDataTableLightResult =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act
    DataTableBasic actualCloneResult = upgradeDataTableLightResult.clone();

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
