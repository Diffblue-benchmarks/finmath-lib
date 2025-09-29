package net.finmath.marketdata.products;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SwapAnnuityDiffblueTest {
  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@code 7.022099733341998}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturn7022099733341998() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    AnalyticModel model = mock(AnalyticModel.class);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 10.5d, 10.0d, 10.5d},
            new double[] {10.0d, 10.5d, 10.0d, 10.5d},
            10.0d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    when(model.getCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    double actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    assertEquals(7.022099733341998d, actualValue, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnFive() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    double actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertEquals(5.0d, actualValue, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    SwapAnnuity swapAnnuity = new SwapAnnuity(schedule, "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    double actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve).getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurve, AnalyticModel)} with
   * {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code model}.
   *
   * <ul>
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurve,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurve, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel_thenReturnFive() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    double actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertEquals(5.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurve, AnalyticModel)} with
   * {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code model}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurve,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurve, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    double actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve).getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    assertEquals(0.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurve)} with {@code schedule}, {@code
   * discountCurve}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurve)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve_givenTen_thenReturnFive() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertEquals(5.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurve)} with {@code schedule}, {@code
   * discountCurve}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurve)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve_givenTen_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve).getDiscountFactor(isNull(), eq(0.0d));
    assertEquals(0.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurve)} with {@code schedule}, {@code
   * forwardCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(Schedule, ForwardCurve)"})
  public void testGetSwapAnnuityWithScheduleForwardCurve() {
    // Arrange
    RegularSchedule schedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, forwardCurve);

    // Assert
    assertEquals(0.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurve)} with {@code schedule}, {@code
   * forwardCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(Schedule, ForwardCurve)"})
  public void testGetSwapAnnuityWithScheduleForwardCurve2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, forwardCurve);

    // Assert
    assertEquals(0.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurve)} with {@code schedule}, {@code
   * forwardCurve}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(Schedule, ForwardCurve)"})
  public void testGetSwapAnnuityWithScheduleForwardCurve_thenReturnPositive_infinity() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            1.0d);

    // Act and Assert
    assertEquals(Double.POSITIVE_INFINITY, SwapAnnuity.getSwapAnnuity(schedule, forwardCurve), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurve)} with {@code tenor},
   * {@code discountCurve}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurve)"})
  public void testGetSwapAnnuityWithTenorDiscountCurve_givenTen_thenReturnFive() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertEquals(5.0d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurve)} with {@code tenor},
   * {@code discountCurve}.
   *
   * <ul>
   *   <li>Then return {@code 4.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurve)"})
  public void testGetSwapAnnuityWithTenorDiscountCurve_thenReturn45() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(-0.5d, 10, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertEquals(4.5d, actualSwapAnnuity, 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurve)} with {@code tenor},
   * {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return {@code 6.30386925016055}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(TimeDiscretization, ForwardCurve)"})
  public void testGetSwapAnnuityWithTenorForwardCurve_thenReturn630386925016055() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(-0.5d, 10, 0.5d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            1.0d);

    // Act and Assert
    assertEquals(6.30386925016055d, SwapAnnuity.getSwapAnnuity(tenor, forwardCurve), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurve)} with {@code tenor},
   * {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(TimeDiscretization, ForwardCurve)"})
  public void testGetSwapAnnuityWithTenorForwardCurve_thenReturnPositive_infinity() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            new double[] {1.0d, 10.5d, 1.0d, 10.5d},
            1.0d);

    // Act and Assert
    assertEquals(Double.POSITIVE_INFINITY, SwapAnnuity.getSwapAnnuity(tenor, forwardCurve), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurve)} with {@code tenor},
   * {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapAnnuity.getSwapAnnuity(TimeDiscretization, ForwardCurve)"})
  public void testGetSwapAnnuityWithTenorForwardCurve_thenReturnZero() {
    // Arrange
    TenorFromArray tenor =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    double actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, forwardCurve);

    // Assert
    assertEquals(0.0d, actualSwapAnnuity, 0.0);
  }
}
