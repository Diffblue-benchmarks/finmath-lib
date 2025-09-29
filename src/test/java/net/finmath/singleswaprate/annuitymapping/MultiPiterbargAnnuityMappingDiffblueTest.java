package net.finmath.singleswaprate.annuitymapping;

import static org.junit.Assert.assertEquals;
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
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveNelsonSiegelSvensson;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.singleswaprate.model.volatilities.ScaledVolatilityCube;
import net.finmath.singleswaprate.model.volatilities.StaticVolatilityCube;
import net.finmath.singleswaprate.model.volatilities.VolatilityCube;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MultiPiterbargAnnuityMappingDiffblueTest {
  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    LocalDate fixing = LocalDate.ofYearDay(1, 1);
    Period period =
        new Period(
            fixing, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve2);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods floatSchedule =
        new ScheduleFromPeriods(referenceDate2, periods, new DayCountConvention_30E_360(true));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve2);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "(?<=\\D)(?=\\d)",
            referenceDate2,
            "(?<=\\D)(?=\\d)",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {1.0d, Double.NaN, 1.0d, Double.NaN},
            1.0d);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveNelsonSiegelSvensson);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve(Mockito.<String>any());
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "(?<=\\D)(?=\\d)");
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve(forwardCurve));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve2);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    ScaledVolatilityCube scaledVolatilityCube =
        new ScaledVolatilityCube(
            "(?<=\\D)(?=\\d)", LocalDate.of(1970, 1, 1), "(?<=\\D)(?=\\d)", 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(scaledVolatilityCube);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping6() {
    // Arrange
    Schedule fixSchedule = mock(Schedule.class);
    when(fixSchedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(fixSchedule.getPeriod(anyInt())).thenReturn(period);
    when(fixSchedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    when(fixSchedule.getFixing(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getNumberOfPeriods()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(1.0d, 10, 0.5d));

    DiscountCurve discountCurve = mock(DiscountCurve.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurve);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule, atLeast(1)).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriod(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodEnd(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodLength(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodStart(anyInt());
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link Schedule} {@link Schedule#getFixing(int)} return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping_givenOne_whenScheduleGetFixingReturnOne() {
    // Arrange
    Schedule fixSchedule = mock(Schedule.class);
    when(fixSchedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(fixSchedule.getPeriod(anyInt())).thenReturn(period);
    when(fixSchedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    when(fixSchedule.getFixing(anyInt())).thenReturn(1.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getNumberOfPeriods()).thenReturn(10);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    DiscountCurve discountCurve = mock(DiscountCurve.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurve);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule, atLeast(1)).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriod(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodEnd(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodLength(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodStart(anyInt());
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link DiscountCurve#getDiscountFactor(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping_thenCallsGetDiscountFactor() {
    // Arrange
    Schedule fixSchedule = mock(Schedule.class);
    when(fixSchedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(fixSchedule.getPeriod(anyInt())).thenReturn(period);
    when(fixSchedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    when(fixSchedule.getFixing(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getNumberOfPeriods()).thenReturn(10);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    DiscountCurve discountCurve = mock(DiscountCurve.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurve);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule, atLeast(1)).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriod(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodEnd(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodLength(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodStart(anyInt());
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping_thenCallsGetForward() {
    // Arrange
    Schedule fixSchedule = mock(Schedule.class);
    when(fixSchedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(fixSchedule.getPeriod(anyInt())).thenReturn(period);
    when(fixSchedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    when(fixSchedule.getFixing(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getNumberOfPeriods()).thenReturn(10);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    ForwardCurve forwardCurve = mock(ForwardCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule, atLeast(1)).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriod(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodEnd(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodLength(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodStart(anyInt());
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping_thenCallsGetForward2() {
    // Arrange
    Schedule fixSchedule = mock(Schedule.class);
    when(fixSchedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    when(fixSchedule.getPeriod(anyInt())).thenReturn(period);
    when(fixSchedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    when(fixSchedule.getFixing(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getNumberOfPeriods()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(1.0d, 10, 0.5d));

    ForwardCurve forwardCurve = mock(ForwardCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule, atLeast(1)).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriod(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodEnd(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodLength(anyInt());
    verify(fixSchedule, atLeast(1)).getPeriodStart(anyInt());
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link AnalyticModelWithVolatilityCubes#getForwardCurve(String)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiPiterbargAnnuityMapping#MultiPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, String, double, double, int)"
  })
  public void testNewMultiPiterbargAnnuityMapping_thenCallsGetForwardCurve() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(analyticModelWithVolatilityCubes.getVolatilityCube(Mockito.<String>any()))
        .thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve2);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube2 =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube2);

    // Act
    MultiPiterbargAnnuityMapping actualMultiPiterbargAnnuityMapping =
        new MultiPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes, atLeast(1))
        .getVolatilityCube("MultiPiterbargCubeFromVolatility Cube Name");
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualMultiPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }
}
