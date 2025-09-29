package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Map;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
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

public class SwaptionDataLatticeDiffblueTest {
  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, double,
   * String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, int[], int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])"
  })
  public void testNewSwaptionDataLattice() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            10.0d,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new int[] {1, 0, 1, 0},
            new int[] {1, Integer.MIN_VALUE, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult2.length);
    assertArrayEquals(
        new double[] {0.0d, 0.01d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {1}, getResult2[0]);
    assertArrayEquals(new int[] {1}, getResult2[1]);
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0}, getResult[1]);
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 1}, actualSwaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, String,
   * String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[],
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])"
  })
  public void testNewSwaptionDataLattice2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new int[] {1, 0, 1, 0},
            new int[] {1, Integer.MIN_VALUE, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult2.length);
    assertArrayEquals(
        new double[] {0.0d, 0.01d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {1}, getResult2[0]);
    assertArrayEquals(new int[] {1}, getResult2[1]);
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0}, getResult[1]);
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 1}, actualSwaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, double,
   * String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])}.
   *
   * <ul>
   *   <li>Then return GridNodesPerMoneyness size is three.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, int[], int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])"
  })
  public void testNewSwaptionDataLattice_thenReturnGridNodesPerMoneynessSizeIsThree() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            10.0d,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new int[] {1, Integer.MIN_VALUE, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(3, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult2.length);
    int[][] getResult3 = gridNodesPerMoneyness.get(Integer.MIN_VALUE);
    assertEquals(2, getResult3.length);
    assertArrayEquals(
        new double[] {-2.147483648E7d, 0.0d, 0.01d},
        actualSwaptionDataLattice.getMoneynessAsOffsets(),
        0.0);
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult3[0]);
    assertArrayEquals(new int[] {0}, getResult3[1]);
    assertArrayEquals(new int[] {1}, getResult2[0]);
    assertArrayEquals(new int[] {1}, getResult2[1]);
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getTenors());
    assertArrayEquals(
        new int[] {Integer.MIN_VALUE, 0, 1}, actualSwaptionDataLattice.getMoneyness());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, String,
   * String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])}.
   *
   * <ul>
   *   <li>Then return GridNodesPerMoneyness size is three.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[],
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])"
  })
  public void testNewSwaptionDataLattice_thenReturnGridNodesPerMoneynessSizeIsThree2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new int[] {1, Integer.MIN_VALUE, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(3, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult2.length);
    int[][] getResult3 = gridNodesPerMoneyness.get(Integer.MIN_VALUE);
    assertEquals(2, getResult3.length);
    assertArrayEquals(
        new double[] {-2.147483648E7d, 0.0d, 0.01d},
        actualSwaptionDataLattice.getMoneynessAsOffsets(),
        0.0);
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult3[0]);
    assertArrayEquals(new int[] {0}, getResult3[1]);
    assertArrayEquals(new int[] {1}, getResult2[0]);
    assertArrayEquals(new int[] {1}, getResult2[1]);
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getTenors());
    assertArrayEquals(
        new int[] {Integer.MIN_VALUE, 0, 1}, actualSwaptionDataLattice.getMoneyness());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, double,
   * String, String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])}.
   *
   * <ul>
   *   <li>Then return QuotingConvention is {@code PAYERVOLATILITYLOGNORMAL}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, double[],
   * double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])"
  })
  public void testNewSwaptionDataLattice_thenReturnQuotingConventionIsPayervolatilitylognormal() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            10.0d,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(100);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1000);
    assertEquals(2, getResult2.length);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYLOGNORMAL,
        actualSwaptionDataLattice.getQuotingConvention());
    assertArrayEquals(
        new double[] {1.0d, 10.0d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualSwaptionDataLattice.getTenors());
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult2[1]);
    assertArrayEquals(new int[] {12}, getResult[0]);
    assertArrayEquals(new int[] {120}, getResult2[0]);
    assertArrayEquals(new int[] {100, 1000}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {12, 120}, actualSwaptionDataLattice.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, String,
   * String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])}.
   *
   * <ul>
   *   <li>Then return QuotingConvention is {@code PAYERVOLATILITYLOGNORMAL}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, double[], double[],
   * double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])"
  })
  public void testNewSwaptionDataLattice_thenReturnQuotingConventionIsPayervolatilitylognormal2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(100);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1000);
    assertEquals(2, getResult2.length);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYLOGNORMAL,
        actualSwaptionDataLattice.getQuotingConvention());
    assertArrayEquals(
        new double[] {1.0d, 10.0d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualSwaptionDataLattice.getTenors());
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult2[1]);
    assertArrayEquals(new int[] {12}, getResult[0]);
    assertArrayEquals(new int[] {120}, getResult2[0]);
    assertArrayEquals(new int[] {100, 1000}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {12, 120}, actualSwaptionDataLattice.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, double,
   * String, String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])}.
   *
   * <ul>
   *   <li>Then return QuotingConvention is {@code PAYERVOLATILITYNORMAL}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, double[],
   * double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])"
  })
  public void testNewSwaptionDataLattice_thenReturnQuotingConventionIsPayervolatilitynormal() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYNORMAL,
            10.0d,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(10000);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(100000);
    assertEquals(2, getResult2.length);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYNORMAL, actualSwaptionDataLattice.getQuotingConvention());
    assertArrayEquals(
        new double[] {1.0d, 10.0d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualSwaptionDataLattice.getTenors());
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult2[1]);
    assertArrayEquals(new int[] {12}, getResult[0]);
    assertArrayEquals(new int[] {120}, getResult2[0]);
    assertArrayEquals(new int[] {10000, 100000}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {12, 120}, actualSwaptionDataLattice.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, String,
   * String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])}.
   *
   * <ul>
   *   <li>Then return QuotingConvention is {@code PAYERVOLATILITYNORMAL}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, double[], double[],
   * double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, double[], double[], double[], double[])"
  })
  public void testNewSwaptionDataLattice_thenReturnQuotingConventionIsPayervolatilitynormal2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    int[][] getResult = gridNodesPerMoneyness.get(10000);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(100000);
    assertEquals(2, getResult2.length);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYNORMAL, actualSwaptionDataLattice.getQuotingConvention());
    assertArrayEquals(
        new double[] {1.0d, 10.0d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualSwaptionDataLattice.getTenors());
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult2[1]);
    assertArrayEquals(new int[] {12}, getResult[0]);
    assertArrayEquals(new int[] {120}, getResult2[0]);
    assertArrayEquals(new int[] {10000, 100000}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {12, 120}, actualSwaptionDataLattice.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, double,
   * String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and one.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, int[], int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])"
  })
  public void testNewSwaptionDataLattice_whenArrayOfDoubleWithTenAndOne_thenReturnSizeIsTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            10.0d,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    assertEquals(2, actualSwaptionDataLattice.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult2.length);
    assertArrayEquals(
        new double[] {0.0d, 0.01d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {1}, getResult2[0]);
    assertArrayEquals(new int[] {1}, getResult2[1]);
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, String,
   * String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and one.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[],
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, int[], int[], int[], double[])"
  })
  public void testNewSwaptionDataLattice_whenArrayOfDoubleWithTenAndOne_thenReturnSizeIsTwo2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualSwaptionDataLattice.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    assertEquals(2, actualSwaptionDataLattice.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1);
    assertEquals(2, getResult2.length);
    assertArrayEquals(
        new double[] {0.0d, 0.01d}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {1}, getResult2[0]);
    assertArrayEquals(new int[] {1}, getResult2[1]);
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {0, 1}, actualSwaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, double,
   * String, String, SchedulePrototype, SchedulePrototype, String[], int[], double[])}.
   *
   * <ul>
   *   <li>When empty array of {@link String}.
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, String[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, double, String, String, SchedulePrototype, SchedulePrototype, String[], int[], double[])"
  })
  public void testNewSwaptionDataLattice_whenEmptyArrayOfString_thenReturnDiscountCurveNameIs3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            10.0d,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertEquals("3", actualSwaptionDataLattice.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwaptionDataLattice.getForwardCurveName());
    assertEquals(0, actualSwaptionDataLattice.size());
    assertEquals(10.0d, actualSwaptionDataLattice.getDisplacement(), 0.0);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYLOGNORMAL,
        actualSwaptionDataLattice.getQuotingConvention());
    assertTrue(actualSwaptionDataLattice.getGridNodesPerMoneyness().isEmpty());
    assertTrue(actualSwaptionDataLattice.getMoneynessPerGridNode().isEmpty());
    assertSame(fixMetaSchedule, actualSwaptionDataLattice.getFixMetaSchedule());
    assertSame(floatMetaSchedule, actualSwaptionDataLattice.getFloatMetaSchedule());
    assertSame(referenceDate, actualSwaptionDataLattice.getReferenceDate());
    assertArrayEquals(new double[] {}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {}, actualSwaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate, QuotingConvention, String,
   * String, SchedulePrototype, SchedulePrototype, String[], int[], double[])}.
   *
   * <ul>
   *   <li>When empty array of {@link String}.
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#SwaptionDataLattice(LocalDate,
   * QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, String[], int[],
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionDataLattice.<init>(LocalDate, QuotingConvention, String, String, SchedulePrototype, SchedulePrototype, String[], int[], double[])"
  })
  public void testNewSwaptionDataLattice_whenEmptyArrayOfString_thenReturnDiscountCurveNameIs32() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    // Act
    SwaptionDataLattice actualSwaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertEquals("3", actualSwaptionDataLattice.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwaptionDataLattice.getForwardCurveName());
    assertEquals(0, actualSwaptionDataLattice.size());
    assertEquals(0.0d, actualSwaptionDataLattice.getDisplacement(), 0.0);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYLOGNORMAL,
        actualSwaptionDataLattice.getQuotingConvention());
    assertTrue(actualSwaptionDataLattice.getGridNodesPerMoneyness().isEmpty());
    assertTrue(actualSwaptionDataLattice.getMoneynessPerGridNode().isEmpty());
    assertSame(fixMetaSchedule, actualSwaptionDataLattice.getFixMetaSchedule());
    assertSame(floatMetaSchedule, actualSwaptionDataLattice.getFloatMetaSchedule());
    assertSame(referenceDate, actualSwaptionDataLattice.getReferenceDate());
    assertArrayEquals(new double[] {}, actualSwaptionDataLattice.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualSwaptionDataLattice.getMaturities());
    assertArrayEquals(new int[] {}, actualSwaptionDataLattice.getMoneyness());
    assertArrayEquals(new int[] {}, actualSwaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#convertLattice(QuotingConvention, double, AnalyticModel)} with
   * {@code targetConvention}, {@code displacement}, {@code model}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#convertLattice(QuotingConvention, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice SwaptionDataLattice.convertLattice(QuotingConvention, double, AnalyticModel)"
  })
  public void testConvertLatticeWithTargetConventionDisplacementModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act
    SwaptionDataLattice actualConvertLatticeResult =
        swaptionDataLattice.convertLattice(
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            10.0d,
            new AnalyticModelFromCurvesAndVols());

    // Assert
    assertEquals("3", actualConvertLatticeResult.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualConvertLatticeResult.getForwardCurveName());
    assertEquals(0, actualConvertLatticeResult.size());
    assertEquals(10.0d, actualConvertLatticeResult.getDisplacement(), 0.0);
    assertEquals(
        QuotingConvention.PAYERVOLATILITYLOGNORMAL,
        actualConvertLatticeResult.getQuotingConvention());
    assertTrue(actualConvertLatticeResult.getGridNodesPerMoneyness().isEmpty());
    assertTrue(actualConvertLatticeResult.getMoneynessPerGridNode().isEmpty());
    assertSame(fixMetaSchedule, actualConvertLatticeResult.getFixMetaSchedule());
    assertSame(floatMetaSchedule, actualConvertLatticeResult.getFloatMetaSchedule());
    assertSame(referenceDate, actualConvertLatticeResult.getReferenceDate());
    assertArrayEquals(new double[] {}, actualConvertLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualConvertLatticeResult.getMaturities());
    assertArrayEquals(new int[] {}, actualConvertLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertLatticeResult.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#convertLattice(QuotingConvention, AnalyticModel)} with {@code
   * targetConvention}, {@code model}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#convertLattice(QuotingConvention,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice SwaptionDataLattice.convertLattice(QuotingConvention, AnalyticModel)"
  })
  public void testConvertLatticeWithTargetConventionModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act
    SwaptionDataLattice actualConvertLatticeResult =
        swaptionDataLattice.convertLattice(
            QuotingConvention.PAYERVOLATILITYLOGNORMAL, new AnalyticModelFromCurvesAndVols());

    // Assert
    Map<Integer, int[][]> gridNodesPerMoneyness =
        actualConvertLatticeResult.getGridNodesPerMoneyness();
    assertEquals(2, gridNodesPerMoneyness.size());
    assertEquals(2, actualConvertLatticeResult.size());
    int[][] getResult = gridNodesPerMoneyness.get(0);
    assertEquals(2, getResult.length);
    int[][] getResult2 = gridNodesPerMoneyness.get(1000);
    assertEquals(2, getResult2.length);
    assertArrayEquals(
        new double[] {0.0d, 10.0d}, actualConvertLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {0}, actualConvertLatticeResult.getTenors());
    assertArrayEquals(new int[] {0}, getResult[0]);
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult2[1]);
    assertArrayEquals(new int[] {120}, getResult2[0]);
    assertArrayEquals(new int[] {0, 1000}, actualConvertLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {0, 120}, actualConvertLatticeResult.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#convertLattice(QuotingConvention, AnalyticModel)} with {@code
   * targetConvention}, {@code model}.
   *
   * <ul>
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#convertLattice(QuotingConvention,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice SwaptionDataLattice.convertLattice(QuotingConvention, AnalyticModel)"
  })
  public void testConvertLatticeWithTargetConventionModel_thenReturnSizeIsZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act
    SwaptionDataLattice actualConvertLatticeResult =
        swaptionDataLattice.convertLattice(
            QuotingConvention.PAYERVOLATILITYLOGNORMAL, new AnalyticModelFromCurvesAndVols());

    // Assert
    assertEquals(0, actualConvertLatticeResult.size());
    assertTrue(actualConvertLatticeResult.getGridNodesPerMoneyness().isEmpty());
    assertTrue(actualConvertLatticeResult.getMoneynessPerGridNode().isEmpty());
    assertArrayEquals(new double[] {}, actualConvertLatticeResult.getMoneynessAsOffsets(), 0.0);
    assertArrayEquals(new int[] {}, actualConvertLatticeResult.getMaturities());
    assertArrayEquals(new int[] {}, actualConvertLatticeResult.getMoneyness());
    assertArrayEquals(new int[] {}, actualConvertLatticeResult.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#getGridNodesPerMoneyness()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getGridNodesPerMoneyness()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SwaptionDataLattice.getGridNodesPerMoneyness()"})
  public void testGetGridNodesPerMoneyness_thenReturnEmpty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertTrue(swaptionDataLattice.getGridNodesPerMoneyness().isEmpty());
  }

  /**
   * Test {@link SwaptionDataLattice#getGridNodesPerMoneyness()}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getGridNodesPerMoneyness()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SwaptionDataLattice.getGridNodesPerMoneyness()"})
  public void testGetGridNodesPerMoneyness_thenReturnSizeIsTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act
    Map<Integer, int[][]> actualGridNodesPerMoneyness =
        swaptionDataLattice.getGridNodesPerMoneyness();

    // Assert
    assertEquals(2, actualGridNodesPerMoneyness.size());
    int[][] getResult = actualGridNodesPerMoneyness.get(100);
    assertEquals(2, getResult.length);
    int[][] getResult2 = actualGridNodesPerMoneyness.get(1000);
    assertEquals(2, getResult2.length);
    assertArrayEquals(new int[] {0}, getResult[1]);
    assertArrayEquals(new int[] {0}, getResult2[1]);
    assertArrayEquals(new int[] {12}, getResult[0]);
    assertArrayEquals(new int[] {120}, getResult2[0]);
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneynessPerGridNode()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneynessPerGridNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SwaptionDataLattice.getMoneynessPerGridNode()"})
  public void testGetMoneynessPerGridNode_thenReturnEmpty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertTrue(swaptionDataLattice.getMoneynessPerGridNode().isEmpty());
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneyness()}.
   *
   * <ul>
   *   <li>Then return array of {@code int} with one hundred and one thousand.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneyness()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getMoneyness()"})
  public void testGetMoneyness_thenReturnArrayOfIntWithOneHundredAndOneThousand() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {100, 1000}, swaptionDataLattice.getMoneyness());
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneyness()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code int}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneyness()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getMoneyness()"})
  public void testGetMoneyness_thenReturnEmptyArrayOfInt() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getMoneyness());
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneynessAsOffsets()}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneynessAsOffsets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMoneynessAsOffsets()"})
  public void testGetMoneynessAsOffsets() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {}, swaptionDataLattice.getMoneynessAsOffsets(), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneynessAsOffsets()}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneynessAsOffsets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMoneynessAsOffsets()"})
  public void testGetMoneynessAsOffsets2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {1.0d, 10.0d}, swaptionDataLattice.getMoneynessAsOffsets(), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneynessAsOffsets()}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneynessAsOffsets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMoneynessAsOffsets()"})
  public void testGetMoneynessAsOffsets3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            null,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {}, swaptionDataLattice.getMoneynessAsOffsets(), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneynessAsOffsets()}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneynessAsOffsets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMoneynessAsOffsets()"})
  public void testGetMoneynessAsOffsets4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            null,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {1.0d, 10.0d}, swaptionDataLattice.getMoneynessAsOffsets(), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMoneynessAsOffsets()}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMoneynessAsOffsets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMoneynessAsOffsets()"})
  public void testGetMoneynessAsOffsets5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {0.01d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {1.0d, 10.0d}, swaptionDataLattice.getMoneynessAsOffsets(), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.WEEKLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.MONTHLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.QUARTERLY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.U30_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.ACT_360,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.LAST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(int)} with {@code moneynessBP}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getMaturities(int)"})
  public void testGetMaturitiesWithMoneynessBP() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getMaturities(1));
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(int)} with {@code moneynessBP}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getMaturities(int)"})
  public void testGetMaturitiesWithMoneynessBP2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getMaturities(1));
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 10.008219178082191}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness_thenReturnArrayOfDoubleWith10008219178082191() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d},
            new double[] {100.0d, 10.0d, 100.0d, 10.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.008219178082191d}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities(double)} with {@code moneyness}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getMaturities(double)"})
  public void testGetMaturitiesWithMoneyness_thenReturnEmptyArrayOfDouble() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {}, swaptionDataLattice.getMaturities(10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities()}.
   *
   * <ul>
   *   <li>Then return array of {@code int} with twelve and one hundred twenty.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getMaturities()"})
  public void testGetMaturities_thenReturnArrayOfIntWithTwelveAndOneHundredTwenty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {12, 120}, swaptionDataLattice.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#getMaturities()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code int}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getMaturities()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getMaturities()"})
  public void testGetMaturities_thenReturnEmptyArrayOfInt() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getMaturities());
  }

  /**
   * Test {@link SwaptionDataLattice#getTenors(int, int)} with {@code moneynessBP}, {@code
   * maturityInMonths}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getTenors(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getTenors(int, int)"})
  public void testGetTenorsWithMoneynessBPMaturityInMonths() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getTenors(1, 1));
  }

  /**
   * Test {@link SwaptionDataLattice#getTenors(int, int)} with {@code moneynessBP}, {@code
   * maturityInMonths}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getTenors(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getTenors(int, int)"})
  public void testGetTenorsWithMoneynessBPMaturityInMonths2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getTenors(1, 1));
  }

  /**
   * Test {@link SwaptionDataLattice#getTenors(double, double)} with {@code moneyness}, {@code
   * maturity}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getTenors(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getTenors(double, double)"})
  public void testGetTenorsWithMoneynessMaturity() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new double[] {}, swaptionDataLattice.getTenors(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getTenors(double, double)} with {@code moneyness}, {@code
   * maturity}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#getTenors(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwaptionDataLattice.getTenors(double, double)"})
  public void testGetTenorsWithMoneynessMaturity2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {12.0d, 100.0d, 12.0d, 100.0d},
            new double[] {12.0d, 100.0d, 12.0d, 100.0d},
            new double[] {12.0d, 100.0d, 12.0d, 100.0d},
            new double[] {12.0d, 100.0d, 12.0d, 100.0d});

    // Act and Assert
    assertArrayEquals(new double[] {}, swaptionDataLattice.getTenors(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionDataLattice#getTenors()}.
   *
   * <ul>
   *   <li>Then return array of {@code int} with zero.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getTenors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getTenors()"})
  public void testGetTenors_thenReturnArrayOfIntWithZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {0}, swaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#getTenors()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code int}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#getTenors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] SwaptionDataLattice.getTenors()"})
  public void testGetTenors_thenReturnEmptyArrayOfInt() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertArrayEquals(new int[] {}, swaptionDataLattice.getTenors());
  }

  /**
   * Test {@link SwaptionDataLattice#containsEntryFor(int, int, int)}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#containsEntryFor(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SwaptionDataLattice.containsEntryFor(int, int, int)"})
  public void testContainsEntryFor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertFalse(swaptionDataLattice.containsEntryFor(1, 1, 1));
  }

  /**
   * Test {@link SwaptionDataLattice#containsEntryFor(int, int, int)}.
   *
   * <p>Method under test: {@link SwaptionDataLattice#containsEntryFor(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SwaptionDataLattice.containsEntryFor(int, int, int)"})
  public void testContainsEntryFor2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            11,
            11,
            true);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            11,
            11,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertFalse(swaptionDataLattice.containsEntryFor(1, 1, 1));
  }

  /**
   * Test {@link SwaptionDataLattice#size()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionDataLattice#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SwaptionDataLattice.size()"})
  public void testSize_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(0, swaptionDataLattice.size());
  }
}
