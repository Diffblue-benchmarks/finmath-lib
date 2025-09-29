package net.finmath.marketdata.model.cds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.cds.CDS.DirtyCleanPrice;
import net.finmath.marketdata.model.cds.CDS.ValuationModel;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CDSDiffblueTest {
  /**
   * Test {@link CDS#CDS(Schedule, String, String, String, double, double, LocalDate,
   * ValuationModel, DirtyCleanPrice, boolean)}.
   *
   * <p>Method under test: {@link CDS#CDS(Schedule, String, String, String, double, double,
   * LocalDate, ValuationModel, DirtyCleanPrice, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CDS.<init>(Schedule, String, String, String, double, double, LocalDate, ValuationModel, DirtyCleanPrice, boolean)"
  })
  public void testNewCds() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    LocalDate tradeDate = LocalDate.of(1970, 1, 1);

    // Act
    CDS actualCds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            10.0d,
            tradeDate,
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Assert
    Schedule schedule2 = actualCds.getSchedule();
    assertTrue(schedule2 instanceof RegularSchedule);
    assertEquals("3", actualCds.getDiscountCurveName());
    assertEquals("Doe", actualCds.getSurvivalProbabilityCurveName());
    assertEquals("Recovery Rate Curve Name", actualCds.getRecoveryRateCurveName());
    assertNull(actualCds.getForwardCurveName());
    assertEquals(0.0d, actualCds.getFloatingFeeSpread(), 0.0);
    assertEquals(10.0d, actualCds.getFixedFee(), 0.0);
    assertEquals(10.0d, actualCds.getUpfrontPayment(), 0.0);
    assertEquals(DirtyCleanPrice.CLEAN, actualCds.getDirtyCleanPrice());
    assertEquals(ValuationModel.DISCRETE, actualCds.getValuationModel());
    assertTrue(actualCds.isUseFinerDiscretization());
    assertSame(schedule, schedule2);
    assertSame(tradeDate, actualCds.getTradeDate());
  }

  /**
   * Test {@link CDS#CDS(Schedule, String, String, String, double, LocalDate, ValuationModel,
   * DirtyCleanPrice, boolean)}.
   *
   * <p>Method under test: {@link CDS#CDS(Schedule, String, String, String, double, LocalDate,
   * ValuationModel, DirtyCleanPrice, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CDS.<init>(Schedule, String, String, String, double, LocalDate, ValuationModel, DirtyCleanPrice, boolean)"
  })
  public void testNewCds2() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    LocalDate tradeDate = LocalDate.of(1970, 1, 1);

    // Act
    CDS actualCds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            tradeDate,
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Assert
    Schedule schedule2 = actualCds.getSchedule();
    assertTrue(schedule2 instanceof RegularSchedule);
    assertEquals("3", actualCds.getDiscountCurveName());
    assertEquals("Doe", actualCds.getSurvivalProbabilityCurveName());
    assertEquals("Recovery Rate Curve Name", actualCds.getRecoveryRateCurveName());
    assertNull(actualCds.getForwardCurveName());
    assertEquals(0.0d, actualCds.getFloatingFeeSpread(), 0.0);
    assertEquals(0.0d, actualCds.getUpfrontPayment(), 0.0);
    assertEquals(10.0d, actualCds.getFixedFee(), 0.0);
    assertEquals(DirtyCleanPrice.CLEAN, actualCds.getDirtyCleanPrice());
    assertEquals(ValuationModel.DISCRETE, actualCds.getValuationModel());
    assertTrue(actualCds.isUseFinerDiscretization());
    assertSame(schedule, schedule2);
    assertSame(tradeDate, actualCds.getTradeDate());
  }

  /**
   * Test {@link CDS#CDS(ScheduleFromPeriods, String, String, String, String, double, double,
   * LocalDate, ValuationModel, DirtyCleanPrice, boolean)}.
   *
   * <p>Method under test: {@link CDS#CDS(ScheduleFromPeriods, String, String, String, String,
   * double, double, LocalDate, ValuationModel, DirtyCleanPrice, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CDS.<init>(ScheduleFromPeriods, String, String, String, String, double, double, LocalDate, ValuationModel, DirtyCleanPrice, boolean)"
  })
  public void testNewCds3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    LocalDate tradeDate = LocalDate.of(1970, 1, 1);

    // Act
    CDS actualCds =
        new CDS(
            schedule,
            "3",
            "Forward Curve Name",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            10.0d,
            tradeDate,
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Assert
    Schedule schedule2 = actualCds.getSchedule();
    assertTrue(schedule2 instanceof ScheduleFromPeriods);
    assertEquals("3", actualCds.getDiscountCurveName());
    assertEquals("Doe", actualCds.getSurvivalProbabilityCurveName());
    assertEquals("Forward Curve Name", actualCds.getForwardCurveName());
    assertEquals("Recovery Rate Curve Name", actualCds.getRecoveryRateCurveName());
    assertEquals(0.0d, actualCds.getFixedFee(), 0.0);
    assertEquals(10.0d, actualCds.getFloatingFeeSpread(), 0.0);
    assertEquals(10.0d, actualCds.getUpfrontPayment(), 0.0);
    assertEquals(DirtyCleanPrice.CLEAN, actualCds.getDirtyCleanPrice());
    assertEquals(ValuationModel.DISCRETE, actualCds.getValuationModel());
    assertTrue(actualCds.isUseFinerDiscretization());
    assertSame(schedule, schedule2);
    assertSame(tradeDate, actualCds.getTradeDate());
  }

  /**
   * Test {@link CDS#CDS(ScheduleFromPeriods, String, String, String, String, double, LocalDate,
   * ValuationModel, DirtyCleanPrice, boolean)}.
   *
   * <p>Method under test: {@link CDS#CDS(ScheduleFromPeriods, String, String, String, String,
   * double, LocalDate, ValuationModel, DirtyCleanPrice, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CDS.<init>(ScheduleFromPeriods, String, String, String, String, double, LocalDate, ValuationModel, DirtyCleanPrice, boolean)"
  })
  public void testNewCds4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    LocalDate tradeDate = LocalDate.of(1970, 1, 1);

    // Act
    CDS actualCds =
        new CDS(
            schedule,
            "3",
            "Forward Curve Name",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            tradeDate,
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Assert
    Schedule schedule2 = actualCds.getSchedule();
    assertTrue(schedule2 instanceof ScheduleFromPeriods);
    assertEquals("3", actualCds.getDiscountCurveName());
    assertEquals("Doe", actualCds.getSurvivalProbabilityCurveName());
    assertEquals("Forward Curve Name", actualCds.getForwardCurveName());
    assertEquals("Recovery Rate Curve Name", actualCds.getRecoveryRateCurveName());
    assertEquals(0.0d, actualCds.getFixedFee(), 0.0);
    assertEquals(0.0d, actualCds.getUpfrontPayment(), 0.0);
    assertEquals(10.0d, actualCds.getFloatingFeeSpread(), 0.0);
    assertEquals(DirtyCleanPrice.CLEAN, actualCds.getDirtyCleanPrice());
    assertEquals(ValuationModel.DISCRETE, actualCds.getValuationModel());
    assertTrue(actualCds.isUseFinerDiscretization());
    assertSame(schedule, schedule2);
    assertSame(tradeDate, actualCds.getTradeDate());
  }

  /**
   * Test {@link CDS#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link CDS#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    CDS cds =
        new CDS(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link CDS#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Forward Curve Name",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link CDS#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_whenNull() {
    // Arrange
    CDS cds =
        new CDS(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> cds.getValue(10.0d, (AnalyticModel) null));
  }

  /**
   * Test {@link CDS#getConventionalSpread(double, AnalyticModel)}.
   *
   * <p>Method under test: {@link CDS#getConventionalSpread(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getConventionalSpread(double, AnalyticModel)"})
  public void testGetConventionalSpread() {
    // Arrange
    CDS cds =
        new CDS(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getConventionalSpread(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getConventionalSpread(double, AnalyticModel)}.
   *
   * <p>Method under test: {@link CDS#getConventionalSpread(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getConventionalSpread(double, AnalyticModel)"})
  public void testGetConventionalSpread2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Forward Curve Name",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getConventionalSpread(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getConventionalSpread(double, AnalyticModel)}.
   *
   * <p>Method under test: {@link CDS#getConventionalSpread(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getConventionalSpread(double, AnalyticModel)"})
  public void testGetConventionalSpread3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getConventionalSpread(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getFeePayment(int, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getFeePayment(int, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getFeePayment(int, AnalyticModel)"})
  public void testGetFeePayment_thenReturnFive() {
    // Arrange
    CDS cds =
        new CDS(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertEquals(5.0d, cds.getFeePayment(1, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link CDS#getFeePayment(int, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getFeePayment(int, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getFeePayment(int, AnalyticModel)"})
  public void testGetFeePayment_thenThrowIllegalArgumentException() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Forward Curve Name",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cds.getFeePayment(1, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CDS#getAccruedFee(LocalDate, AnalyticModel)} with {@code date}, {@code model}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(LocalDate, AnalyticModel)"})
  public void testGetAccruedFeeWithDateModel_thenCallsGetForward() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getFixing(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);
    LocalDate date = LocalDate.of(1970, 1, 1);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    double actualAccruedFee = cds.getAccruedFee(date, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(10.0d));
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    assertEquals(0.0d, actualAccruedFee, 0.0);
  }

  /**
   * Test {@link CDS#getAccruedFee(LocalDate, AnalyticModel)} with {@code date}, {@code model}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(LocalDate, AnalyticModel)"})
  public void testGetAccruedFeeWithDateModel_thenReturn00() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getFixing(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);
    LocalDate date = LocalDate.of(1970, 1, 1);

    AnalyticModel model = mock(AnalyticModel.class);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            365.0d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);

    // Act
    double actualAccruedFee = cds.getAccruedFee(date, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    assertEquals(-0.0d, actualAccruedFee, 0.0);
  }

  /**
   * Test {@link CDS#getAccruedFee(LocalDate, AnalyticModel)} with {@code date}, {@code model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(LocalDate, AnalyticModel)"})
  public void testGetAccruedFeeWithDateModel_thenThrowIllegalArgumentException() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getFixing(anyInt())).thenThrow(new IllegalArgumentException());
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);
    LocalDate date = LocalDate.of(1970, 1, 1);

    AnalyticModel model = mock(AnalyticModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> cds.getAccruedFee(date, model));
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule).getPeriodLength(1);
  }

  /**
   * Test {@link CDS#getAccruedFee(LocalDate, AnalyticModel)} with {@code date}, {@code model}.
   *
   * <ul>
   *   <li>When {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(LocalDate, AnalyticModel)"})
  public void testGetAccruedFeeWithDateModel_whenAnalyticModelFromCurvesAndVols_thenReturnZero() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    double actualAccruedFee = cds.getAccruedFee(date, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    assertEquals(0.0d, actualAccruedFee, 0.0);
  }

  /**
   * Test {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)} with {@code time},
   * {@code model}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(double, AnalyticModelFromCurvesAndVols)"})
  public void testGetAccruedFeeWithTimeModel_thenCallsGetForward() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getFixing(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    when(schedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    double actualAccruedFee = cds.getAccruedFee(10.0d, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(10.0d));
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    verify(schedule).getReferenceDate();
    assertEquals(99.97222222222221d, actualAccruedFee, 0.0);
  }

  /**
   * Test {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)} with {@code time},
   * {@code model}.
   *
   * <ul>
   *   <li>Then return {@code 99.97222222222221}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(double, AnalyticModelFromCurvesAndVols)"})
  public void testGetAccruedFeeWithTimeModel_thenReturn9997222222222221() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    when(schedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act
    double actualAccruedFee = cds.getAccruedFee(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    verify(schedule).getReferenceDate();
    assertEquals(99.97222222222221d, actualAccruedFee, 0.0);
  }

  /**
   * Test {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)} with {@code time},
   * {@code model}.
   *
   * <ul>
   *   <li>Then return {@code -0.027389649923896496}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(double, AnalyticModelFromCurvesAndVols)"})
  public void testGetAccruedFeeWithTimeModel_thenReturn0027389649923896496() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getFixing(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    when(schedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            365.0d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);

    // Act
    double actualAccruedFee = cds.getAccruedFee(10.0d, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    verify(schedule).getReferenceDate();
    assertEquals(-0.027389649923896496d, actualAccruedFee, 0.0);
  }

  /**
   * Test {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)} with {@code time},
   * {@code model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CDS#getAccruedFee(double, AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CDS.getAccruedFee(double, AnalyticModelFromCurvesAndVols)"})
  public void testGetAccruedFeeWithTimeModel_thenThrowIllegalArgumentException() {
    // Arrange
    RegularSchedule schedule = mock(RegularSchedule.class);
    when(schedule.getFixing(anyInt())).thenThrow(new IllegalArgumentException());
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodIndex(Mockito.<LocalDate>any())).thenReturn(1);
    when(schedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(schedule.getPeriod(anyInt())).thenReturn(period);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            LocalDate.of(1970, 1, 1),
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> cds.getAccruedFee(10.0d, model));
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule).getPeriodLength(1);
    verify(schedule).getReferenceDate();
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CDS#toString()}
   *   <li>{@link CDS#getDirtyCleanPrice()}
   *   <li>{@link CDS#getDiscountCurveName()}
   *   <li>{@link CDS#getFixedFee()}
   *   <li>{@link CDS#getFloatingFeeSpread()}
   *   <li>{@link CDS#getForwardCurveName()}
   *   <li>{@link CDS#getRecoveryRateCurveName()}
   *   <li>{@link CDS#getSchedule()}
   *   <li>{@link CDS#getSurvivalProbabilityCurveName()}
   *   <li>{@link CDS#getTradeDate()}
   *   <li>{@link CDS#getUpfrontPayment()}
   *   <li>{@link CDS#getValuationModel()}
   *   <li>{@link CDS#isUseFinerDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DirtyCleanPrice CDS.getDirtyCleanPrice()",
    "String CDS.getDiscountCurveName()",
    "double CDS.getFixedFee()",
    "double CDS.getFloatingFeeSpread()",
    "String CDS.getForwardCurveName()",
    "String CDS.getRecoveryRateCurveName()",
    "Schedule CDS.getSchedule()",
    "String CDS.getSurvivalProbabilityCurveName()",
    "LocalDate CDS.getTradeDate()",
    "double CDS.getUpfrontPayment()",
    "ValuationModel CDS.getValuationModel()",
    "boolean CDS.isUseFinerDiscretization()",
    "String CDS.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    LocalDate tradeDate = LocalDate.of(1970, 1, 1);

    CDS cds =
        new CDS(
            schedule,
            "3",
            "Doe",
            "Recovery Rate Curve Name",
            10.0d,
            tradeDate,
            ValuationModel.DISCRETE,
            DirtyCleanPrice.CLEAN,
            true);

    // Act
    cds.toString();
    DirtyCleanPrice actualDirtyCleanPrice = cds.getDirtyCleanPrice();
    String actualDiscountCurveName = cds.getDiscountCurveName();
    double actualFixedFee = cds.getFixedFee();
    double actualFloatingFeeSpread = cds.getFloatingFeeSpread();
    String actualForwardCurveName = cds.getForwardCurveName();
    String actualRecoveryRateCurveName = cds.getRecoveryRateCurveName();
    Schedule actualSchedule = cds.getSchedule();
    String actualSurvivalProbabilityCurveName = cds.getSurvivalProbabilityCurveName();
    LocalDate actualTradeDate = cds.getTradeDate();
    double actualUpfrontPayment = cds.getUpfrontPayment();
    ValuationModel actualValuationModel = cds.getValuationModel();

    // Assert
    assertEquals("1970-01-01", actualTradeDate.toString());
    assertEquals("3", actualDiscountCurveName);
    assertEquals("Doe", actualSurvivalProbabilityCurveName);
    assertEquals("Recovery Rate Curve Name", actualRecoveryRateCurveName);
    assertNull(actualForwardCurveName);
    assertEquals(0.0d, actualFloatingFeeSpread, 0.0);
    assertEquals(0.0d, actualUpfrontPayment, 0.0);
    assertEquals(10.0d, actualFixedFee, 0.0);
    assertEquals(DirtyCleanPrice.CLEAN, actualDirtyCleanPrice);
    assertEquals(ValuationModel.DISCRETE, actualValuationModel);
    assertTrue(cds.isUseFinerDiscretization());
    assertSame(schedule, actualSchedule);
    assertSame(tradeDate, actualTradeDate);
  }
}
