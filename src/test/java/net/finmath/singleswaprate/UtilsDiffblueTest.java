package net.finmath.singleswaprate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
import net.finmath.singleswaprate.data.DataTable;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import net.finmath.singleswaprate.data.DataTableLight;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
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

public class UtilsDiffblueTest {
  /**
   * Test {@link Utils#convertTableToLattice(DataTable, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertTableToLattice(DataTable,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertTableToLattice(DataTable, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertTableToLattice_thenReturnSizeIsThree() {
    // Arrange
    DataTableLight table =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, Integer.MIN_VALUE, 1, 0},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
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
    SwaptionDataLattice actualConvertTableToLatticeResult =
        Utils.convertTableToLattice(
            table,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualConvertTableToLatticeResult.getGridNodesPerMoneyness();
    assertEquals(1, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    assertEquals(3, actualConvertTableToLatticeResult.size());
    assertArrayEquals(
        new double[] {0.0d}, actualConvertTableToLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualConvertTableToLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {0, 1}, actualConvertTableToLatticeResult.getMaturities());
    assertArrayEquals(new int[] {0, 1}, getResult[0]);
    assertArrayEquals(
        new int[] {Integer.MIN_VALUE, 0, 1}, actualConvertTableToLatticeResult.getTenors());
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 1}, getResult[1]);
  }

  /**
   * Test {@link Utils#convertTableToLattice(DataTable, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertTableToLattice(DataTable,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertTableToLattice(DataTable, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertTableToLattice_thenReturnSizeIsTwo() {
    // Arrange
    DataTableLight table =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
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
    SwaptionDataLattice actualConvertTableToLatticeResult =
        Utils.convertTableToLattice(
            table,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualConvertTableToLatticeResult.getGridNodesPerMoneyness();
    assertEquals(1, gridNodesPerMoneyness.size());
    assertEquals(2, actualConvertTableToLatticeResult.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    assertArrayEquals(
        new double[] {0.0d}, actualConvertTableToLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualConvertTableToLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {0, 1}, actualConvertTableToLatticeResult.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualConvertTableToLatticeResult.getTenors());
    assertArrayEquals(new int[] {0, 1}, getResult[0]);
    assertArrayEquals(new int[] {0, 1}, getResult[1]);
  }

  /**
   * Test {@link Utils#convertTableToLattice(DataTable, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertTableToLattice(DataTable,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertTableToLattice(DataTable, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertTableToLattice_thenReturnSizeIsZero() {
    // Arrange
    DataTableLight table = new DataTableLight("Name", TableConvention.MONTHS);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
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
    SwaptionDataLattice actualConvertTableToLatticeResult =
        Utils.convertTableToLattice(
            table,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Assert
    assertEquals(0, actualConvertTableToLatticeResult.size());
    assertTrue(actualConvertTableToLatticeResult.getGridNodesPerMoneyness().isEmpty());
    assertTrue(actualConvertTableToLatticeResult.getMoneynessPerGridNode().isEmpty());
    assertArrayEquals(
        new double[] {}, actualConvertTableToLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualConvertTableToLatticeResult.getMaturities());
    assertArrayEquals(new int[] {}, actualConvertTableToLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertTableToLatticeResult.getTenors());
  }

  /**
   * Test {@link Utils#convertTableToLattice(DataTable, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertTableToLattice(DataTable,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertTableToLattice(DataTable, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertTableToLattice_thenThrowIllegalArgumentException() {
    // Arrange
    DataTableLight table = new DataTableLight("Name", TableConvention.YEARS);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Utils.convertTableToLattice(
                table,
                QuotingConvention.PAYERVOLATILITYLOGNORMAL,
                referenceDate,
                "3",
                "Forward Curve Name",
                fixMetaSchedule,
                floatMetaSchedule));
  }

  /**
   * Test {@link Utils#convertMapOfTablesToLattice(Map, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <p>Method under test: {@link Utils#convertMapOfTablesToLattice(Map,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertMapOfTablesToLattice(Map, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertMapOfTablesToLattice() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    tables.put(1, new DataTableLight("Name", TableConvention.MONTHS));
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
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
    SwaptionDataLattice actualConvertMapOfTablesToLatticeResult =
        Utils.convertMapOfTablesToLattice(
            tables,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Assert
    assertArrayEquals(
        new double[] {}, actualConvertMapOfTablesToLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualConvertMapOfTablesToLatticeResult.getMaturities());
    assertArrayEquals(new int[] {}, actualConvertMapOfTablesToLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertMapOfTablesToLatticeResult.getTenors());
  }

  /**
   * Test {@link Utils#convertMapOfTablesToLattice(Map, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then return GridNodesPerMoneyness size is one.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertMapOfTablesToLattice(Map,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertMapOfTablesToLattice(Map, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertMapOfTablesToLattice_thenReturnGridNodesPerMoneynessSizeIsOne() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    tables.put(1, dataTableLight);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
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
    SwaptionDataLattice actualConvertMapOfTablesToLatticeResult =
        Utils.convertMapOfTablesToLattice(
            tables,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualConvertMapOfTablesToLatticeResult.getGridNodesPerMoneyness();
    assertEquals(1, gridNodesPerMoneyness.size());
    assertEquals(2, actualConvertMapOfTablesToLatticeResult.size());
    int[][] getResult = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult.length);
    assertArrayEquals(
        new double[] {0.01d}, actualConvertMapOfTablesToLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {1}, actualConvertMapOfTablesToLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {0, 1}, actualConvertMapOfTablesToLatticeResult.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualConvertMapOfTablesToLatticeResult.getTenors());
    assertArrayEquals(new int[] {0, 1}, getResult[0]);
    assertArrayEquals(new int[] {0, 1}, getResult[1]);
  }

  /**
   * Test {@link Utils#convertMapOfTablesToLattice(Map, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertMapOfTablesToLattice(Map,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertMapOfTablesToLattice(Map, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertMapOfTablesToLattice_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    tables.put(1, new DataTableLight("Name", TableConvention.YEARS));
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Utils.convertMapOfTablesToLattice(
                tables,
                QuotingConvention.PAYERVOLATILITYLOGNORMAL,
                referenceDate,
                "3",
                "Forward Curve Name",
                fixMetaSchedule,
                floatMetaSchedule));
  }

  /**
   * Test {@link Utils#convertMapOfTablesToLattice(Map, QuotingConvention, LocalDate, String,
   * String, SchedulePrototype, SchedulePrototype)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertMapOfTablesToLattice(Map,
   * SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype,
   * SchedulePrototype)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertMapOfTablesToLattice(Map, SwaptionDataLattice.QuotingConvention, LocalDate, String, String, SchedulePrototype, SchedulePrototype)"
  })
  public void testConvertMapOfTablesToLattice_whenHashMap() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
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
    SwaptionDataLattice actualConvertMapOfTablesToLatticeResult =
        Utils.convertMapOfTablesToLattice(
            tables,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Assert
    assertArrayEquals(
        new double[] {}, actualConvertMapOfTablesToLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualConvertMapOfTablesToLatticeResult.getMaturities());
    assertArrayEquals(new int[] {}, actualConvertMapOfTablesToLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertMapOfTablesToLatticeResult.getTenors());
  }

  /**
   * Test {@link Utils#convertCashLatticeToNormalVolatility(SwaptionDataLattice,
   * VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link Utils#convertCashLatticeToNormalVolatility(SwaptionDataLattice,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertCashLatticeToNormalVolatility(SwaptionDataLattice, VolatilityCubeModel)"
  })
  public void testConvertCashLatticeToNormalVolatility() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice cashLattice =
        Utils.convertMapOfTablesToLattice(
            tables,
            QuotingConvention.PAYERPRICE,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Act
    SwaptionDataLattice actualConvertCashLatticeToNormalVolatilityResult =
        Utils.convertCashLatticeToNormalVolatility(
            cashLattice, new AnalyticModelWithVolatilityCubes());

    // Assert
    assertArrayEquals(
        new double[] {},
        actualConvertCashLatticeToNormalVolatilityResult.getMoneynessAsOffsets(),
        0.0);
    assertArrayEquals(
        new int[] {}, actualConvertCashLatticeToNormalVolatilityResult.getMaturities());
    assertArrayEquals(
        new int[] {}, actualConvertCashLatticeToNormalVolatilityResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertCashLatticeToNormalVolatilityResult.getTenors());
  }

  /**
   * Test {@link Utils#convertCashLatticeToNormalVolatility(SwaptionDataLattice,
   * VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link Utils#convertCashLatticeToNormalVolatility(SwaptionDataLattice,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertCashLatticeToNormalVolatility(SwaptionDataLattice, VolatilityCubeModel)"
  })
  public void testConvertCashLatticeToNormalVolatility2() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice cashLattice =
        Utils.convertMapOfTablesToLattice(
            tables,
            QuotingConvention.RECEIVERPRICE,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Act
    SwaptionDataLattice actualConvertCashLatticeToNormalVolatilityResult =
        Utils.convertCashLatticeToNormalVolatility(
            cashLattice, new AnalyticModelWithVolatilityCubes());

    // Assert
    assertArrayEquals(
        new double[] {},
        actualConvertCashLatticeToNormalVolatilityResult.getMoneynessAsOffsets(),
        0.0);
    assertArrayEquals(
        new int[] {}, actualConvertCashLatticeToNormalVolatilityResult.getMaturities());
    assertArrayEquals(
        new int[] {}, actualConvertCashLatticeToNormalVolatilityResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertCashLatticeToNormalVolatilityResult.getTenors());
  }

  /**
   * Test {@link Utils#convertCashLatticeToNormalVolatility(SwaptionDataLattice,
   * VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Utils#convertCashLatticeToNormalVolatility(SwaptionDataLattice,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice Utils.convertCashLatticeToNormalVolatility(SwaptionDataLattice, VolatilityCubeModel)"
  })
  public void testConvertCashLatticeToNormalVolatility_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<Integer, DataTable> tables = new HashMap<>();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice cashLattice =
        Utils.convertMapOfTablesToLattice(
            tables,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            referenceDate,
            "3",
            "Forward Curve Name",
            fixMetaSchedule,
            floatMetaSchedule);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Utils.convertCashLatticeToNormalVolatility(
                cashLattice, new AnalyticModelWithVolatilityCubes()));
  }
}
