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
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.CurveInterpolation;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation.InterpolationEntityForward;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.singleswaprate.model.volatilities.StaticVolatilityCube;
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

public class BasicPiterbargAnnuityMappingDiffblueTest {
  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "6M",
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            365.0d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping2() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation createForwardCurveFromForwardsResult =
        ForwardCurveInterpolation.createForwardCurveFromForwards(
            "6M",
            referenceDate2,
            "6M",
            InterpolationEntityForward.FORWARD,
            "3",
            new AnalyticModelFromCurvesAndVols(),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            new double[] {365.0d, 360.0d, 365.0d, 360.0d});
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromForwardsResult);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping3() {
    // Arrange
    DayCountConvention_30E_360 daycountconvention = mock(DayCountConvention_30E_360.class);
    when(daycountconvention.getDaycountFraction(Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.ofEpochDay(-1L),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(daycountconvention, atLeast(1))
        .getDaycountFraction(isA(LocalDate.class), isA(LocalDate.class));
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping4() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods floatSchedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
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
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping5() {
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
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping6() {
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

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping7() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "6M",
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            365.0d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping8() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation createForwardCurveFromForwardsResult =
        ForwardCurveInterpolation.createForwardCurveFromForwards(
            "6M",
            referenceDate2,
            "6M",
            InterpolationEntityForward.FORWARD,
            "3",
            new AnalyticModelFromCurvesAndVols(),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            new double[] {365.0d, 360.0d, 365.0d, 360.0d});
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromForwardsResult);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping9() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveInterpolation createForwardCurveFromForwardsResult =
        ForwardCurveInterpolation.createForwardCurveFromForwards(
            "6M",
            referenceDate2,
            "6M",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            InterpolationEntityForward.FORWARD,
            "3",
            new AnalyticModelFromCurvesAndVols(),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d},
            new double[] {365.0d, 360.0d, 365.0d, 360.0d});
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromForwardsResult);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping10() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    LocalDate fixing = LocalDate.ofYearDay(1, 1);
    Period period =
        new Period(
            fixing, LocalDate.ofYearDay(1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * VolatilityCubeModel, String, String)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping11() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    LocalDate fixing = LocalDate.ofEpochDay(-1L);
    Period period =
        new Period(
            fixing, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * VolatilityCubeModel, String, String)}.
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping12() {
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

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(0.0d));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Given two.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping_givenTwo() {
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
    when(fixSchedule.getNumberOfPeriods()).thenReturn(2);
    when(fixSchedule.getFixing(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
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
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link Schedule} {@link Schedule#getFixing(int)} return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping_givenZero_whenScheduleGetFixingReturnZero() {
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
    when(fixSchedule.getFixing(anyInt())).thenReturn(0.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getNumberOfPeriods()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
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
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DayCountConvention_30E_360#getDaycountFraction(LocalDate, LocalDate)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenCallsGetDaycountFraction() {
    // Arrange
    DayCountConvention_30E_360 daycountconvention = mock(DayCountConvention_30E_360.class);
    when(daycountconvention.getDaycountFraction(Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(daycountconvention, atLeast(1))
        .getDaycountFraction(isA(LocalDate.class), isA(LocalDate.class));
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link DayCountConvention_30E_360#getDaycountFraction(LocalDate, LocalDate)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenCallsGetDaycountFraction2() {
    // Arrange
    DayCountConvention_30E_360 daycountconvention = mock(DayCountConvention_30E_360.class);
    when(daycountconvention.getDaycountFraction(Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
        .thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate fixing = LocalDate.ofEpochDay(-1L);
    Period period =
        new Period(
            fixing, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(daycountconvention, atLeast(1))
        .getDaycountFraction(isA(LocalDate.class), isA(LocalDate.class));
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link Schedule#getDaycountconvention()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenCallsGetDaycountconvention() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, 10.0d, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
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
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link Schedule#getDaycountconvention()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenCallsGetDaycountconvention2() {
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
    when(fixSchedule.getNumberOfPeriods()).thenReturn(1);
    when(fixSchedule.getFixing(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(0);
    verify(fixSchedule).getPeriod(0);
    verify(fixSchedule, atLeast(1)).getPeriodEnd(0);
    verify(fixSchedule, atLeast(1)).getPeriodLength(0);
    verify(fixSchedule, atLeast(1)).getPeriodStart(0);
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   *       double)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenCallsGetDiscountFactor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    LocalDate fixing = LocalDate.ofYearDay(1, 1);
    Period period =
        new Period(
            fixing, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getPaymentOffset(double)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenCallsGetPaymentOffset() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    LocalDate fixing = LocalDate.ofYearDay(1, 1);
    Period period =
        new Period(
            fixing, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * VolatilityCubeModel, String, String)}.
   *
   * <ul>
   *   <li>Then return FirstDerivative is ten is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping_thenReturnFirstDerivativeIsTenIsNaN() {
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
    TenorFromArray timeDiscretization =
        new TenorFromArray(Double.NaN, Double.NaN, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(0.0d));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then calls {@link Schedule#getDaycountconvention()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String)"
  })
  public void testNewBasicPiterbargAnnuityMapping_whenNaN_thenCallsGetDaycountconvention() {
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
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule, floatSchedule, Double.NaN, model, "3", "Volatility Cube Name");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
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
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule,
   * double, VolatilityCubeModel, String, String, double, double, int)}.
   *
   * <ul>
   *   <li>When {@link Schedule} {@link Schedule#getFixing(int)} return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * BasicPiterbargAnnuityMapping#BasicPiterbargAnnuityMapping(Schedule, Schedule, double,
   * VolatilityCubeModel, String, String, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicPiterbargAnnuityMapping.<init>(Schedule, Schedule, double, VolatilityCubeModel, String, String, double, double, int)"
  })
  public void testNewBasicPiterbargAnnuityMapping_whenScheduleGetFixingReturnOne() {
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
    when(fixSchedule.getNumberOfPeriods()).thenReturn(1);
    when(fixSchedule.getFixing(anyInt())).thenReturn(1.0d);
    when(fixSchedule.getPayment(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodEnd(anyInt())).thenReturn(10.0d);
    when(fixSchedule.getPeriodStart(anyInt())).thenReturn(10.0d);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    BasicPiterbargAnnuityMapping actualBasicPiterbargAnnuityMapping =
        new BasicPiterbargAnnuityMapping(
            fixSchedule,
            floatSchedule,
            10.0d,
            model,
            "3",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(fixSchedule).getDaycountconvention();
    verify(fixSchedule, atLeast(1)).getFixing(0);
    verify(fixSchedule, atLeast(1)).getNumberOfPeriods();
    verify(fixSchedule, atLeast(1)).getPayment(0);
    verify(fixSchedule).getPeriod(0);
    verify(fixSchedule, atLeast(1)).getPeriodEnd(0);
    verify(fixSchedule, atLeast(1)).getPeriodLength(0);
    verify(fixSchedule, atLeast(1)).getPeriodStart(0);
    verify(fixSchedule, atLeast(1)).getReferenceDate();
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBasicPiterbargAnnuityMapping.getValue(10.0d), 0.0);
  }
}
